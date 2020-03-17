package com.intecanar.ondiet.ui.weight.recycler.timeline

import com.intecanar.ondiet.data.model.Weight

object TimeLineDataAdapter {

    fun timeConverter(listOfWeights: MutableList<Weight>): MutableList<TimeLineModel> {
        val listOfPoints :MutableList<TimeLineModel> = mutableListOf()
        listOfWeights.forEach { weight: Weight ->
            listOfPoints.add( TimeLineModel("${weight.weight} Kg", weight.date) )
        }
        return listOfPoints
    }
}