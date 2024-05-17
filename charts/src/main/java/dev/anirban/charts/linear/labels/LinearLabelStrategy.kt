package dev.anirban.charts.linear.labels

import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.data.LinearDataStrategy
import dev.anirban.charts.linear.decoration.LinearDecoration


/**
 * Abstraction over the Linear Labels are made using this interface. Any new implementation for
 * drawing the axis labels needs to implement this interface.
 *
 * Implementations for this interface are :- [StringLabelStrategy]
 */
interface LinearLabelStrategy {

    /**
     * This function draws the labels in the graph according to the implementation passed
     *
     * @param linearData The data of the graph [LinearDataStrategy] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    fun DrawScope.drawLabels(
        linearData: LinearDataStrategy,
        decoration: LinearDecoration
    )
}