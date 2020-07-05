package com.intecanar.ondiet.domain

import androidx.lifecycle.LiveData
import com.intecanar.ondiet.data.dao.WaterDAO
import com.intecanar.ondiet.data.entity.Water

//See, if you need to create a repository which combine different sources.
//https://github.com/android/architecture-components-samples/tree/master/BasicSample/app/src/main/java/com/example/android/persistence
class WaterRepository  (private val waterDAO: WaterDAO)  {

    val waterList: LiveData<List<Water>> = waterDAO.getLastWater()

    suspend fun insert(water: Water) {
        waterDAO.insert(water)
    }

    suspend fun delete(water: Water) {
        waterDAO.delete(water)
    }
}