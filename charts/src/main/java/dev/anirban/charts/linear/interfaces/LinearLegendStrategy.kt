package dev.anirban.charts.linear.interfaces

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.legends.*

/**
 * This is the abstraction interface for the legends made in the graph. Every new legend logic
 * implementation should implement this interface..
 *
 * Implementations for this interface are :- [NoLegendStrategy], [GridLegendStrategy],
 * [CustomLegendStrategy]
 */
interface LinearLegendStrategy {


    /**
     * This function implements the logic for drawing the legends.
     *
     * @param linearData The data of the graph [LinearDataStrategy] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    @Composable
    fun DrawLegends(
        linearData: LinearDataStrategy,
        decoration: LinearDecoration
    )
}