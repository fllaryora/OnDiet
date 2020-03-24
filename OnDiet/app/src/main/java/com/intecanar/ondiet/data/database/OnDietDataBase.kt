package com.intecanar.ondiet.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.intecanar.ondiet.data.converter.TimeConverter
import com.intecanar.ondiet.data.dao.WeightDAO
import com.intecanar.ondiet.data.entity.Weight


//https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/
//https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
@Database(entities = [Weight::class], version = 1, exportSchema = false)
@TypeConverters(TimeConverter::class)
abstract class OnDietDataBase : RoomDatabase() {
    abstract fun weightDAO() : WeightDAO
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: OnDietDataBase? = null

        fun getDatabase(context: Context): OnDietDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OnDietDataBase::class.java,
                    "ondiet_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}