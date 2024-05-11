package dev.anirban.charts.linear.interfaces

import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.plots.*

/**
 * This is the abstraction of graph's plot logic. This class shall be implemented by all the
 * plot mechanisms or plot logic.
 *
 * Implementations for this interface are :- [LinearBarPlot] , [LinearLinePlot],
 * [LinearGradientPlot]
 */
interface LinearPlotInterface {


    /**
     * This function plots the graph data sets on the graph
     *
     * @param linearData The data of the graph [LinearDataInterface] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    fun DrawScope.plotChart(
        linearData: LinearDataInterface,
        decoration: LinearDecoration
    )
}