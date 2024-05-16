package dev.anirban.charts.circular.center

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataInterface

/**
 * This interface implements a function to draw the center of the circular chart
 *
 * Different Implementations for the interface are [CircularNoCenter],
 * [CircularTargetTextCenter]
 */
interface CircularCenterInterface {

    /**
     * This function draws composable function in the center of the chart
     *
     * @param circularData This is the data related to the Circular Chart
     * @param decoration This is the decorations for the chart
     */
    @Composable
    fun DrawCenter(
        circularData: CircularDataInterface,
        decoration: CircularDecoration
    )
}