package dev.anirban.charts.circular.center

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * Interface for defining strategies to draw the center of a circular chart.
 *
 * Different implementations for the interface are [NoCenterStrategy],
 * [TextCenterStrategy], [CustomCenterStrategy], [ImageCenterStrategy]
 */
interface CircularCenterStrategy {

    /**
     * This function draws composable function in the center of the chart
     *
     * @param circularData Data related to the circular chart.
     * @param decoration Decorations for the chart.
     */
    @Composable
    fun DrawCenter(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    )
}