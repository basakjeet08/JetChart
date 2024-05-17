package dev.anirban.charts.circular

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.circular.center.CircularCenterStrategy
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.legend.CircularLegendStrategy
import dev.anirban.charts.circular.data.CircularDataStrategy
import dev.anirban.charts.circular.foreground.CircularForegroundStrategy


/**
 * This provides a strategy prototype for the Circular Charts implementation and needs to be
 * implemented by any class which wants to create its own Circular chart strategy.
 *
 * For implementations see [BasicCircularStrategy]
 */
interface CircularChartStrategy {


    /**
     * This is the strategy to draw the center of the chart.
     */
    val circularCenter: CircularCenterStrategy


    /**
     * This is the strategy to store and manipulate the circular chart data.
     */
    val circularData: CircularDataStrategy


    /**
     * This contains the details of the decorations for the color and all those color related Stuff
     */
    val circularDecoration: CircularDecoration


    /**
     * This is the strategy to draw the main plot or the foreground of the chart.
     */
    val circularForeground: CircularForegroundStrategy


    /**
     * This strategy draws the legends for the Chart
     */
    val circularLegend: CircularLegendStrategy


    /**
     * This function draws the center of the Chart
     */
    @Composable
    fun DrawCenter()


    /**
     * This function calculates the data for the Chart
     */
    fun doCalculations()


    /**
     * This function draws the main plots and the foreground of the Chart
     */
    fun DrawScope.drawForeground()


    /**
     * This function draws the legends of the Chart
     */
    @Composable
    fun DrawLegends()


    /**
     * This is the Build Function which starts composing the Charts and composes the Charts
     *
     * @param modifier This is for default modifications to be passed from the parent Class
     */
    @Composable
    fun Build(modifier: Modifier)
}