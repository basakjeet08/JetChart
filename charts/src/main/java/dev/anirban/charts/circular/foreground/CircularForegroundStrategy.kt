package dev.anirban.charts.circular.foreground

import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.circular.data.CircularDataStrategy
import dev.anirban.charts.circular.decoration.CircularDecoration


/**
 * This is the strategy for the creation of the foreground of the circular chart and this class
 * should be extended for the implementation to work with the library.
 *
 * Implementations for this interface are :- [DonutForegroundStrategy],
 * [DonutTargetForegroundStrategy]
 */
interface CircularForegroundStrategy {


    /**
     * This function draws the foreground according to the strategy
     *
     * @param circularData This is the data of the chart
     * @param decoration This is the decoration of the chart
     */
    fun DrawScope.drawForeground(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    )
}