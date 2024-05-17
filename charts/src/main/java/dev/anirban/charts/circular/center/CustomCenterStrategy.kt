package dev.anirban.charts.circular.center

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * This class is the implementation of [CircularCenterStrategy] which focuses on providing an
 * opportunity to the developer to provide his own custom implementation for drawing the
 * center of a circular chart.
 *
 * For other implementations see [ImageCenterStrategy], [TextCenterStrategy], [NoCenterStrategy]
 *
 * @param body The composable content to display in the center.
 */
class CustomCenterStrategy(
    private val body: @Composable () -> Unit
) : CircularCenterStrategy {


    /**
     * This function draws composable function in the center of the chart
     *
     * @param circularData Data related to the circular chart.
     * @param decoration Decorations for the chart.
     */
    @Composable
    override fun DrawCenter(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) = body()
}