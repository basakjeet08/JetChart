package dev.anirban.charts.linear.legends

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearLegendDrawer
import dev.anirban.charts.linear.interfaces.LinearDataInterface

/**
 * This class is the implementation of [LinearLegendDrawer] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 */
object LinearNoLegend : LinearLegendDrawer {


    /**
     * This function draws the color conventions. In this case this draws nothing in the Chart
     */
    @Composable
    override fun DrawLegends(
        data: LinearDataInterface,
        decoration: LinearDecoration
    ) = Unit
}