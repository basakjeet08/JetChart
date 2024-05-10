package dev.anirban.charts.linear.colorconvention

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearLegends
import dev.anirban.charts.linear.interfaces.LinearDataInterface

/**
 * This class is the implementation of [LinearLegends] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 */
class NoLegend : LinearLegends {

    /**
     * This function draws the color conventions. In this case this draws nothing in the Chart
     */
    @Composable
    override fun DrawLegends(
        data: LinearDataInterface,
        decoration: LinearDecoration
    ) {

        // Do Nothing
    }
}