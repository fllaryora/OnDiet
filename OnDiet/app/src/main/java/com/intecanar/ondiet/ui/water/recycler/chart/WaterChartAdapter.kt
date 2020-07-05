package com.intecanar.ondiet.ui.water.recycler.chart

import com.intecanar.ondiet.data.entity.Water
import lecho.lib.hellocharts.model.PointValue
import java.time.Duration
import java.time.OffsetDateTime

object WaterChartAdapter {
    fun pointConverter(listOfWater : List<Water>) :MutableList<PointValue>{
        val listOfPoints :MutableList<PointValue> = mutableListOf()

        if(listOfWater.isEmpty()) {
            return listOfPoints
        }
        var minEpoch : OffsetDateTime = listOfWater[0].date

        minEpoch = listOfWater.fold(minEpoch)  { min: OffsetDateTime, water: Water ->
            if( water.date.isBefore(min)){
                water.date
            }
            else min
        }

        var currentVolume = 0.0f
        listOfWater.sortedWith( compareBy{it.date.toEpochSecond()})
            .forEach { water: Water ->
                currentVolume += water.volume
                listOfPoints.add( PointValue(
                    dateConverter(
                        minEpoch,
                        water.date
                    ), currentVolume) )
            }
        return listOfPoints
    }

    private fun dateConverter(minEpoch : OffsetDateTime, date: OffsetDateTime): Float {
        return  Duration.between(minEpoch, date).toDays().toFloat()
    }
}