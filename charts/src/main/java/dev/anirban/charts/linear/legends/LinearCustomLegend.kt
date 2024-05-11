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
 * @param drawLegend The implementation of the Legends building logic.
 */
class LinearCustomLegend(
    private val drawLegend: @Composable (data: List<String>) -> Unit
) : LinearLegendDrawer {


    /**
     * This function draws the color conventions in the canvas
     *
     * @param data This object contains the data of the graph
     * @param decoration This object contains the decorations of the graph
     */
    @Composable
    override fun DrawLegends(data: LinearDataInterface, decoration: LinearDecoration) {
        drawLegend(data.linearDataSets.map { it.title })
    }
}