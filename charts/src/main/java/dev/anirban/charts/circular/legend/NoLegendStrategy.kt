package dev.anirban.charts.circular.legend

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * This class is the implementation of [CircularLegendStrategy] which provides a strategy to draw
 * no legends for the circular chart. This also serves as a default implementation.
 *
 * For other implementation see [GridLegendStrategy], [ListLegendStrategy]
 */
object NoLegendStrategy : CircularLegendStrategy {


    /**
     * This function draws the desired color Convention
     *
     * @param circularData This is the data of the chart
     * @param decoration This is the decoration of the chart
     */
    @Composable
    override fun DrawColorConventions(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) = Unit
}