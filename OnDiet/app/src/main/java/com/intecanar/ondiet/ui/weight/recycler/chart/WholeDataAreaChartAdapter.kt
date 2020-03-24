package com.intecanar.ondiet.ui.weight.recycler.chart

import android.graphics.Color
import com.intecanar.ondiet.data.entity.Weight
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue

object WholeDataAreaChartAdapter {

    fun configureWholeData(listOfWeights: List<Weight>):LineChartData  {
        val values: MutableList<PointValue> = AreaChartAdapter.pointConverter(listOfWeights)
        val line = Line(values)
        val appColor = Color.parseColor("#c2261a")
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