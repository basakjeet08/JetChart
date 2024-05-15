package dev.anirban.charts.linear

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.data.LinearDataStrategy
import dev.anirban.charts.linear.labels.LinearLabelStrategy
import dev.anirban.charts.linear.legends.LinearLegendStrategy
import dev.anirban.charts.linear.plots.LinearPlotterStrategy


/**
 * This is the interface that needs to be implemented by any chart object to work with the Library.
 *
 * Implementations for this interface are :- [BasicLinearStrategy]
 */
interface LinearChartStrategy {


    /**
     * This is the implementation of the [LinearLabelStrategy]. The labels will be drawn
     * in the graph according to the implementation
     *
     * @see LinearLabelStrategy
     */
    val labelDrawer: LinearLabelStrategy


    /**
     * This is the implementation of the [LinearDecoration]. The decoration of different elements
     * will be provided by this object
     *
     * @see LinearDecoration
     */
    val decoration: LinearDecoration


    /**
     * This is the implementation of the [LinearDataStrategy]. The data along with the chart
     * offsets will be calculated according to this Login
     *
     * @see LinearDataStrategy
     */
    val linearData: LinearDataStrategy


    /**
     * This is the implementation of the [LinearPlotterStrategy]. This is responsible for providing
     * the logic to draw plots in the chart.
     *
     * @see LinearPlotterStrategy
     */
    val plot: LinearPlotterStrategy


    /**
     * This is the implementation of [LinearLegendStrategy]. This provides an implementation for
     * drawing the legends of the chart
     */
    val legendDrawer: LinearLegendStrategy


    /**
     * This function draws the various labels and the Axis Lines of the graph according to the
     * [LinearChartStrategy.labelDrawer] implementation.
     */
    fun DrawScope.drawLabels()


    /**
     * This function draws the plots of the graph according to the [LinearChartStrategy.plot]
     * implementation.
     */
    fun DrawScope.drawPlot()


    /**
     * This function draws the legends of the graph according to the [LinearChartStrategy.legendDrawer]
     * implementation
     */
    @Composable
    fun DrawLegends()


    /**
     * This function is called to start building the chart
     *
     * @param modifier This is for default modifications to be passed from the parent Class
     */
    @Composable
    fun Build(modifier: Modifier)
}