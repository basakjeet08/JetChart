package dev.anirban.charts.linear.legends

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearLegendDrawer
import dev.anirban.charts.linear.interfaces.LinearDataInterface

/**
 * This class is the implementation of [LinearLegendDrawer] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 * Other implementations are [LinearNoLegend], [LinearCustomLegend]
 */
object LinearNoLegend : LinearLegendDrawer {


    /**
     * This function implements the logic for drawing the legends.
     *
     * @param linearData The data of the graph [LinearDataInterface] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    @Composable
    override fun DrawLegends(
        linearData: LinearDataInterface,
        decoration: LinearDecoration
    ) = Unit
}