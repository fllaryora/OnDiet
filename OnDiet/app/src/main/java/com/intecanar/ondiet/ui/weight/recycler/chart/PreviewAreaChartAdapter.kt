package com.intecanar.ondiet.ui.weight.recycler.chart

import android.graphics.Color
import com.intecanar.ondiet.data.database.entities.Weight
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue

object PreviewAreaChartAdapter {

    fun configurePreviewChart(listOfWeights: List<Weight>):LineChartData {
        val values: MutableList<PointValue> = AreaChartAdapter.pointConverter(listOfWeights)
        val line = Line(values)
        val accentColor = Color.parseColor("#c25e1a")
        line.color = accentColor
        line.setHasPoints(false)
        line.isFilled = true
        line.strokeWidth = 1

        val lines: List<Line> = listOf(line)
        val previewData = LineChartData(lines)
        val xAxis =  Axis()
        xAxis.setHasLines(true)
        previewData.axisXBottom = xAxis
        val yAxis =  Axis()
        yAxis.setHasLines(true)
        yAxis.textColor = accentColor
        previewData.axisYLeft = yAxis
        return previewData;
    }
}