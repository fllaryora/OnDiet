package com.intecanar.ondiet.ui.weight

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intecanar.ondiet.data.model.Weight
import com.intecanar.ondiet.ui.weight.recycler.chart.PreviewAreaChartAdapter
import com.intecanar.ondiet.ui.weight.recycler.chart.WholeDataAreaChartAdapter
import com.intecanar.ondiet.ui.weight.recycler.timeline.TimeLineDataAdapter
import com.intecanar.ondiet.ui.weight.recycler.timeline.TimeLineModel
import lecho.lib.hellocharts.model.LineChartData
import java.time.OffsetDateTime
import kotlin.random.Random

class WeightViewModel : ViewModel() {

    val wholeData : MutableLiveData<LineChartData> = MutableLiveData()
    val previewData : MutableLiveData<LineChartData> = MutableLiveData()
    val timeLineList : MutableLiveData<MutableList<TimeLineModel>> = MutableLiveData()

    init {
        // Generate data for previewed chart and copy of that data for preview chart.
        val data = generateRandomData()

        wholeData.value = WholeDataAreaChartAdapter.configureWholeData(data)
        previewData.value = PreviewAreaChartAdapter.configurePreviewChart(data)
        timeLineList.value = TimeLineDataAdapter.timeConverter(data)
    }

    /**
     * This method must be replaced for a call to Data base
     */
    private fun generateRandomData() :MutableList<Weight>{
        val numValues = 50
        val rightNow = OffsetDateTime.now()
        val randomValues = List(numValues) { 50f + (Random.nextFloat() * 100f) }
        val randomDates = List(numValues) { rightNow.minusDays( Random.nextLong(-50,0)) }

        val values :MutableList<Weight> = mutableListOf()
        randomValues.forEachIndexed { index : Int, randonNumber: Float ->
            values.add(Weight(index, randonNumber,  randomDates[index]))
        }
        return values
    }

}