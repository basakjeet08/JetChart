package dev.anirban.charts.linear.interfaces

import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.labels.*

/**
 * Abstraction over the Linear Labels are made using this interface. Any new implementation for
 * drawing the axis labels needs to implement this interface.
 *
 * Implementations for this interface are :- [LinearStringLabelDrawer], [LinearEmojiLabelDrawer]
 */
interface LinearLabelDrawerInterface {

    /**
     * This function draws the labels in the graph according to the implementation passed
     *
     * @param linearData The data of the graph [LinearDataInterface] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    fun DrawScope.drawLabels(
        linearData: LinearDataInterface,
        decoration: LinearDecoration
    )
}