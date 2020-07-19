package com.intecanar.ondiet.domain.water

import com.intecanar.ondiet.data.database.dao.WaterDAO
import com.intecanar.ondiet.data.database.entities.Water

/**
 * Map dao functions to Use case functions
 * or I think that.
 */
class FetchWaterUseCaseSync {

    /**
     * Syncronous calls
     */
    fun getWaterIntakes(): List<Water> {
        return WaterDAO.getWaterIntakes()
    }

    fun deleteWaterIntake(water: Water) : Boolean {
        return WaterDAO.delete(water)
    }

    fun insert(water: Water) : Long {
        return WaterDAO.insert(water)
    }

    /**
     * Asyncronous calls
     */
    //***NONE***
}
