package com.intecanar.ondiet.domain

import androidx.lifecycle.LiveData
import com.intecanar.ondiet.data.dao.WeightDAO
import com.intecanar.ondiet.data.entity.Weight

//See, if you need to create a repository which combine different sources.
//https://github.com/android/architecture-components-samples/tree/master/BasicSample/app/src/main/java/com/example/android/persistence
class WeightRepository  (private val weightDAO: WeightDAO)  {

    val weightList: LiveData<List<Weight>> = weightDAO.getAll()

    suspend fun insert(weight: Weight) {
        weightDAO.insert(weight)
    }

    suspend fun delete(weight: Weight) {
        weightDAO.delete(weight)
    }
}