package com.intecanar.ondiet.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.intecanar.ondiet.data.entity.Water

@Dao
interface WaterDAO {

    //get volume for today
    @Query("select * from water_table where date(date) = date('now')")
    fun getLastWater(): LiveData<List<Water>>

    /**
     * where the value emitted on onSuccess is the list of row ids of the items inserted (long)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(water: Water)

    /**
     * onSuccess is the number of rows affected by update/delete  (int)
     */
    @Delete
    suspend fun delete(water: Water)

}