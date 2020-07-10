package com.intecanar.ondiet.ui.water.recycler.chart

import android.graphics.Color
import com.intecanar.ondiet.data.database.entities.Water
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue

object WaterAreaChartAdapter {

    fun configureWholeData(listOfWater: List<Water>):LineChartData  {
        val values: MutableList<PointValue> = WaterChartAdapter.pointConverter(listOfWater)
        val line = Line(values)
        val appColor = Color.parseColor("#1a62c2")
        line.color = appColor
        line.setHasPoints(false)
        line.isFilled = true
        line.strokeWidth = 1
        val lines: List<Line> = listOf(line)
        val wholeData = LineChartData(lines)

        val xAxis =  Axis()
        xAxis.setHasLines(true)
        wholeData.axisXBottom = xAxis
        val yAxis =  Axis()
        yAxis.setHasLines(true)
        yAxis.maxLabelChars = 4
        yAxis.textColor = appColor
        wholeData.axisYLeft = yAxis

        return wholeData
    }
}