package com.intecanar.ondiet.ui.water

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intecanar.ondiet.data.database.OnDietDataBase
import com.intecanar.ondiet.data.entity.Water
import com.intecanar.ondiet.domain.WaterRepository
import com.intecanar.ondiet.ui.water.recycler.chart.WaterAreaChartAdapter
import kotlinx.coroutines.launch
import lecho.lib.hellocharts.model.LineChartData

class WaterIntakeViewModel (application: Application) :  AndroidViewModel(application)  {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: WaterRepository
    // LiveData gives us updated Weight when they change.
    val waterList: LiveData<List<Water>>

    init {
        // Gets reference to waterDAO from OnDietDataBase to construct
        // the correct WaterRepository.
        val waterDAO = OnDietDataBase.getDatabase(application).waterDAO()
        repository = WaterRepository(waterDAO)
        waterList = repository.waterList
    }

    fun delete(water: Water) = viewModelScope.launch {
        repository.delete(water)
    }

    fun getAreaChartList(waterList: List<Water>): LineChartData
            = WaterAreaChartAdapter.configureWholeData(waterList)

}