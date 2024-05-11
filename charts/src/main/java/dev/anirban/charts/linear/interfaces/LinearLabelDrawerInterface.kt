package dev.anirban.charts.linear.interfaces

import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.labels.LinearStringLabelDrawer

/**
 * This is the interface which defines that all the Implementation for the Drawing of the labels
 * need to implement this interface.
 *
 * Implementations for this interface are :- [LinearStringLabelDrawer]
 *
 */
interface LinearLabelDrawerInterface {

    /**
     * This function draws the labels in the graph according to the implementation passed
     *
     * @param linearData This is the data for the graph
     * @param decoration This is the decoration for the graph
     */
    fun DrawScope.drawLabels(
        linearData: LinearDataInterface,
        decoration: LinearDecoration
    )
}