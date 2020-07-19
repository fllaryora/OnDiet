package com.intecanar.ondiet.domain.weight

import com.intecanar.ondiet.data.database.dao.WeightDAO
import com.intecanar.ondiet.data.database.entities.Weight

/**
 * Map dao functions to Use case functions
 * or I think that.
 */
class FetchWeightsUseCaseSync {

    /**
     * Syncronous calls
     */
    fun fetchWeights(): List<Weight> {
        return WeightDAO.getWeights()
    }

    fun deleteWeight(weight: Weight) : Boolean {
        return WeightDAO.delete(weight)
    }

    fun insert(weight: Weight) : Long {
        return WeightDAO.insert(weight)
    }

    /**
     * Asyncronous calls
     */
    //***NONE***
}
