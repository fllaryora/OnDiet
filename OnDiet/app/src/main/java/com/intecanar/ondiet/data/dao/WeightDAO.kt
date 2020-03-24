package com.intecanar.ondiet.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.intecanar.ondiet.data.entity.Weight

@Dao
interface WeightDAO {

    @Query("select * from weight_table ORDER BY date ASC")
    fun getAll(): LiveData<List<Weight>>

    //@Query("select id, weight, max(date) as date from weight_table")
    //fun getLast(): LiveData<Weight>

    /**
     * where the value emitted on onSuccess is the list of row ids of the items inserted (long)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weight: Weight)

    /**
     * onSuccess is the number of rows affected by update/delete  (int)
     */
    @Delete
    suspend fun delete(weight: Weight)

}