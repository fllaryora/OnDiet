package com.intecanar.ondiet.data.database.dao

import com.intecanar.ondiet.data.converter.TimeConverter
import com.intecanar.ondiet.data.database.ObjectBox
import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.data.database.entities.Water_
import io.objectbox.query.Query
import io.objectbox.query.QueryBuilder
import java.time.OffsetDateTime

/**
 * This class is absolutely Framework dependent.
 * DAO for objectbox
 */
object WaterDAO {

    private val waterBox = ObjectBox.boxStore.boxFor(Water::class.java)

    private val waterQuery: Query<Water>
            = waterBox.query()
            .startsWith(Water_.date,"").parameterAlias("today")
            .order(Water_.date, QueryBuilder.CASE_SENSITIVE ).build()

    /**
     * Puts the given object in the box (aka persisting it). If this is a new entity (its ID property is 0), a new ID
     * will be assigned to the entity (and returned). If the entity was already put in the box before, it will be
     * overwritten.
     * <p>
     * Performance note: if you want to put several entities, consider {@link #put(Collection)},
     * {@link #put(Object[])}, {@link BoxStore#runInTx(Runnable)}, etc. instead.
     */
    fun insert(water: Water) : Long {
        //return the new key
        return waterBox.put(water)
    }

    /**
     * Removes (deletes) the given Object.
     * @return true if an entity was actually removed (false if no entity exists with the given ID)
     */
    fun delete(water: Water) : Boolean{
        return waterBox.remove(water)
    }

    // in case of asyncronous call
    // Example from https://github.com/timediv/QuickDynalist/blob/master/app/src/main/java/com/louiskirsch/quickdynalist/BaseItemListFragment.kt

    //SQL equivalent  "select * from water_table where date(date) = date('now')")
    fun getWaterIntakes(): MutableList<Water> {
        val today :String = TimeConverter.fromOffsetDate(OffsetDateTime.now())!!
        return waterQuery.setParameter("today",today).find()
    }

}