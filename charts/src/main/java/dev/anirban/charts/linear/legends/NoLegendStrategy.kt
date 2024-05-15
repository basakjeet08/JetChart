package dev.anirban.charts.linear.legends

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.data.LinearDataStrategy

/**
 * This class is the implementation of [LinearLegendStrategy] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 * Other implementations are [NoLegendStrategy], [CustomLegendStrategy]
 */
object NoLegendStrategy : LinearLegendStrategy {


    /**
     * This function implements the logic for drawing the legends.
     *
     * @param linearData The data of the graph [LinearDataStrategy] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    @Composable
    override fun DrawLegends(
        linearData: LinearDataStrategy,
        decoration: LinearDecoration
    ) = Unit
}