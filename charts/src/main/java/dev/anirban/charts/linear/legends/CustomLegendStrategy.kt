package dev.anirban.charts.linear.legends

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.data.LinearDataStrategy


/**
 * This class is the implementation of [LinearLegendStrategy] which provides the
 * functionality of drawing the Legends in the canvas. Here this class lets the user provide an
 * implementation for the Legends building logic.
 *
 * Other implementations of [LinearLegendStrategy] are [NoLegendStrategy] and [GridLegendStrategy]
 *
 * @param drawLegend The implementation of the Legends building logic.
 */
class CustomLegendStrategy(
    private val drawLegend: @Composable (data: List<String>) -> Unit
) : LinearLegendStrategy {


    /**
     * This function implements the logic for drawing the legends.
     *
     * @param linearData The data of the graph [LinearDataStrategy] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    @Composable
    override fun DrawLegends(linearData: LinearDataStrategy, decoration: LinearDecoration) {
        drawLegend(linearData.linearDataSets.map { it.title })
    }
}