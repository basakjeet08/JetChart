package dev.anirban.charts.linear.plots

import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.data.LinearDataStrategy
import dev.anirban.charts.linear.decoration.LinearDecoration


/**
 * This is the abstraction of graph's plot logic. This class shall be implemented by all the
 * plot mechanisms or plot logic.
 *
 * Implementations for this interface are :- [BarPlotStrategy] , [LinePlotStrategy],
 * [GradientPlotStrategy]
 */
interface LinearPlotterStrategy {


    /**
     * This function plots the graph data sets on the graph
     *
     * @param linearData The data of the graph [LinearDataStrategy] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    fun DrawScope.plotChart(
        linearData: LinearDataStrategy,
        decoration: LinearDecoration
    )
}