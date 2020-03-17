package com.intecanar.ondiet.ui.weight.recycler.chart

import com.intecanar.ondiet.data.model.Weight
import lecho.lib.hellocharts.model.PointValue
import java.time.Duration
import java.time.OffsetDateTime

object AreaChartAdapter {
    fun pointConverter(listOfWeights : MutableList<Weight>) :MutableList<PointValue>{
        val listOfPoints :MutableList<PointValue> = mutableListOf()

        if(listOfWeights.isEmpty()) {
            return listOfPoints
        }
        var minEpoch : OffsetDateTime = listOfWeights[0].date

        minEpoch = listOfWeights.fold(minEpoch)  { min: OffsetDateTime, weight: Weight ->
            if( weight.date.isBefore(min)){
                weight.date
            }
            else min
        }

        listOfWeights.sortedWith( compareBy{it.date.toEpochSecond()})
            .forEach { weight: Weight ->
                listOfPoints.add( PointValue(
                    dateConverter(
                        minEpoch,
                        weight.date
                    ), weight.weight) )
            }

        return listOfPoints
    }

    private fun dateConverter(minEpoch : OffsetDateTime, date: OffsetDateTime): Float {
        return  Duration.between(minEpoch, date).toDays().toFloat()
    }
}