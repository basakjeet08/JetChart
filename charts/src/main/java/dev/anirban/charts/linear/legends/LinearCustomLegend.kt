package dev.anirban.charts.linear.legends

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.linear.interfaces.LinearLegendDrawer


/**
 * This class is the implementation of [LinearLegendDrawer] which provides the
 * functionality of drawing the Legends in the canvas. Here this class lets the user provide an
 * implementation for the Legends building logic.
 *
 * Other implementations of [LinearLegendDrawer] are [LinearNoLegend] and [LinearGridLegend]
 *
 * @param drawLegend The implementation of the Legends building logic.
 */
class LinearCustomLegend(
    private val drawLegend: @Composable (data: List<String>) -> Unit
) : LinearLegendDrawer {


    /**
     * This function implements the logic for drawing the legends.
     *
     * @param linearData The data of the graph [LinearDataInterface] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    @Composable
    override fun DrawLegends(linearData: LinearDataInterface, decoration: LinearDecoration) {
        drawLegend(linearData.linearDataSets.map { it.title })
    }
}