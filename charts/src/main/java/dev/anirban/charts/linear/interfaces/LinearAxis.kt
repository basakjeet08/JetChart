package dev.anirban.charts.linear.interfaces

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.util.Coordinate

interface LinearAxis {

    val labelDrawer: LinearLabelDrawerInterface
    val xTitle: Coordinate<String>
    val yTitle: Coordinate<String>
    val xAxisLabels: List<Coordinate<*>>
    var yAxisLabels: MutableList<Coordinate<*>>

    val numOfXLabels: Int
    val numOfYLabels: Int

    fun DrawScope.calculateYLabelsCoordinates(): Int

    fun DrawScope.drawXLabels(decoration : LinearDecoration)

    fun DrawScope.drawYLabels(decoration : LinearDecoration)

    fun DrawScope.startCalculation(size: Size, dataSet: List<LinearDataSet>)
    fun DrawScope.calculateXLabelsCoordinates(size: Size, yLabelsMaxWidth: Int)
}