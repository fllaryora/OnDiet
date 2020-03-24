package com.intecanar.ondiet.ui.weight

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.intecanar.ondiet.data.database.OnDietDataBase
import com.intecanar.ondiet.data.entity.Weight
import com.intecanar.ondiet.domain.OnDietRepository
import com.intecanar.ondiet.ui.weight.recycler.chart.PreviewAreaChartAdapter
import com.intecanar.ondiet.ui.weight.recycler.chart.WholeDataAreaChartAdapter
import lecho.lib.hellocharts.model.LineChartData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeightViewModel(application: Application) :  AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: OnDietRepository
    // LiveData gives us updated words when they change.
    val weightList: LiveData<List<Weight>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val wordsDao = OnDietDataBase.getDatabase(application).weightDAO()
        repository = OnDietRepository(wordsDao)
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