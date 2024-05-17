package dev.anirban.charts.circular.legend

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * This is a strategy prototype for the color convention implementation logic and must be
 * implemented by all the classes providing strategy to draw the legend.
 *
 * Strategies of this interface are :- [NoLegendStrategy],
 * [GridLegendStrategy],[ListLegendStrategy]
 */
interface CircularLegendStrategy {


    /**
     * This function draws the desired color Convention
     *
     * @param circularData This is the data of the chart
     * @param decoration This is the decoration of the chart
     */
    @Composable
    fun DrawColorConventions(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    )
}