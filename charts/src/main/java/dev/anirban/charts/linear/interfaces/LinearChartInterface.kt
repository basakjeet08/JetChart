package dev.anirban.charts.linear.interfaces

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.LinearChart

/**
 * This is the interface that needs to be implemented by any chart object to work with the Library.
 *
 * Implementations for this interface are :- [LinearChart]
 */
interface LinearChartInterface {


    /**
     * This is the implementation of the [LinearLabelDrawerInterface]. The labels will be drawn
     * in the graph according to the implementation
     *
     * @see LinearLabelDrawerInterface
     */
    val labelDrawer: LinearLabelDrawerInterface


    /**
     * This is the implementation of the [LinearDecoration]. The decoration of different elements
     * will be provided by this object
     *
     * @see LinearDecoration
     */
    val decoration: LinearDecoration


    /**
     * This is the implementation of the [LinearDataInterface]. The data along with the chart
     * offsets will be calculated according to this Login
     *
     * @see LinearDataInterface
     */
    val linearData: LinearDataInterface


    /**
     * This is the implementation of the [LinearPlotInterface]. This is responsible for providing
     * the logic to draw plots in the chart.
     *
     * @see LinearPlotInterface
     */
    val plot: LinearPlotInterface


    /**
     * This is the implementation of [LinearLegendDrawer]. This provides an implementation for
     * drawing the legends of the chart
     */
    val legendDrawer: LinearLegendDrawer


    /**
     * This function draws the various labels and the Axis Lines of the graph according to the
     * [LinearChartInterface.labelDrawer] implementation.
     */
    fun DrawScope.drawLabels()


    /**
     * This function draws the plots of the graph according to the [LinearChartInterface.plot]
     * implementation.
     */
    fun DrawScope.drawPlot()


    /**
     * This function draws the legends of the graph according to the [LinearChartInterface.legendDrawer]
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