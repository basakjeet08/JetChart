package dev.anirban.charts.circular

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.circular.center.CircularCenterInterface
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.legend.CircularLegendInterface
import dev.anirban.charts.circular.data.CircularDataInterface
import dev.anirban.charts.circular.foreground.CircularForegroundInterface

/**
 * This is the interface which is implemented by the [CircularChart] Class which is the root class
 * of all the circular Chart Classes
 *
 */
interface CircularChartInterface {

    /**
     * This is the implementation to draw the Center of the Circular Charts
     */
    val circularCenter: CircularCenterInterface

    /**
     * This is the implementation of the circular Data to be kept for the chart and the operations
     * to be done on them
     */
    val circularData: CircularDataInterface

    /**
     * This contains the details of the decorations for the color and all those color related Stuff
     */
    val circularDecoration: CircularDecoration

    /**
     * This is the implementation to draw the Foreground of the Chart and draws the main plotting for
     * the chart
     */
    val circularForeground: CircularForegroundInterface

    /**
     * This implementation draws the Color Conventions for the Chart
     */
    val circularColorConvention: CircularLegendInterface

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
     * This function draws the Color Convention of the Chart
     */
    @Composable
    fun DrawColorConventions()

    /**
     * This is the Build Function which starts composing the Charts and composes the Charts
     *
     * @param modifier This is for default modifications to be passed from the parent Class
     */
    @Composable
    fun Build(modifier: Modifier)
}