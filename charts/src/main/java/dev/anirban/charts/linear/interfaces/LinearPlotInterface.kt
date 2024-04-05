package dev.anirban.charts.linear.interfaces

import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.plots.*

/**
 * This is the interface which needs to be every graph plot logic to work in the Library
 *
 * Implementations for this interface are :- [LinearBarPlot] , [LinearLinePlot]
 */
interface LinearPlotInterface {

    /**
     * This function plots the graph points
     */
    fun DrawScope.plotChart(
        linearData: LinearDataInterface,
        decoration: LinearDecoration
    )
}