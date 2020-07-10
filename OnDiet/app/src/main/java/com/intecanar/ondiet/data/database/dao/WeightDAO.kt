package com.intecanar.ondiet.data.database.dao

import com.intecanar.ondiet.data.database.ObjectBox
import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.data.database.entities.Weight_
import io.objectbox.query.Query
import io.objectbox.query.QueryBuilder

/**
 * This class is absolutely Framework dependent.
 * DAO for objectbox
 */
object WeightDAO {

    private val weightBox = ObjectBox.boxStore.boxFor(Weight::class.java)

    private lateinit var weightQuery: Query<Weight>

    /**
     * Puts the given object in the box (aka persisting it). If this is a new entity (its ID property is 0), a new ID
     * will be assigned to the entity (and returned). If the entity was already put in the box before, it will be
     * overwritten.
     * <p>
     * Performance note: if you want to put several entities, consider {@link #put(Collection)},
     * {@link #put(Object[])}, {@link BoxStore#runInTx(Runnable)}, etc. instead.
     */
    fun insert(weight: Weight) {
        weightBox.put(weight)
    }

    /**
     * Removes (deletes) the given Object.
     * @return true if an entity was actually removed (false if no entity exists with the given ID)
     */
    fun delete(weight: Weight) {
        weightBox.remove(weight)
    }

    // in case of asyncronous call
    // Example from https://github.com/timediv/QuickDynalist/blob/master/app/src/main/java/com/louiskirsch/quickdynalist/BaseItemListFragment.kt


    //SQL equivalent  "select * from weight_table ORDER BY date ASC")
    fun getWeights():  MutableList<Weight> {
        weightQuery = weightBox.query().order(Weight_.date, QueryBuilder.CASE_SENSITIVE ).build()
       return weightQuery.find()
    }

}