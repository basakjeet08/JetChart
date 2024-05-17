package dev.anirban.charts.circular.center

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * This class is the implementation of [CircularCenterStrategy] which focuses on providing a
 * strategy for not drawing anything in the center of a circular chart. This also serves as a
 * default center strategy.
 *
 * For other implementations see [CustomCenterStrategy], [ImageCenterStrategy], [TextCenterStrategy]
 *
 */
object NoCenterStrategy : CircularCenterStrategy {

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
    ) = Unit
}