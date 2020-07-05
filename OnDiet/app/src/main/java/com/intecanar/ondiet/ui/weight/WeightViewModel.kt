package com.intecanar.ondiet.ui.weight

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.intecanar.ondiet.data.database.OnDietDataBase
import com.intecanar.ondiet.data.entity.Weight
import com.intecanar.ondiet.domain.WeightRepository
import com.intecanar.ondiet.ui.weight.recycler.chart.PreviewAreaChartAdapter
import com.intecanar.ondiet.ui.weight.recycler.chart.WholeDataAreaChartAdapter
import kotlinx.coroutines.launch
import lecho.lib.hellocharts.model.LineChartData

class WeightViewModel(application: Application) :  AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: WeightRepository
    // LiveData gives us updated Weight when they change.
    val weightList: LiveData<List<Weight>>

    init {
        // Gets reference to weightDAO from OnDietDataBase to construct
        // the correct WeightRepository.
        val weightDAO = OnDietDataBase.getDatabase(application).weightDAO()
        repository = WeightRepository(weightDAO)
        weightList = repository.weightList
    }

    fun delete(weight: Weight) = viewModelScope.launch {
        repository.delete(weight)
    }

    fun getAreaChartList(weightList: List<Weight>): LineChartData
            = WholeDataAreaChartAdapter.configureWholeData(weightList)

    fun getPreviewChartList(weightList: List<Weight>): LineChartData
            = PreviewAreaChartAdapter.configurePreviewChart(weightList)

}