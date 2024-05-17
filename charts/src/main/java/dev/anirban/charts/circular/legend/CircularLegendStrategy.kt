package dev.anirban.charts.circular.legend

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy

/**
 * This implementation shall be implemented by all the classes which are making color convention
 * implementation
 *
 * Implementations for this interface are :- [NoLegendStrategy],
 * [GridLegendStrategy],[ListLegendStrategy]

 *
 * @property DrawColorConventions THis function draws the desired color Convention
 */
interface CircularLegendStrategy {

    @Composable
    fun DrawColorConventions(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    )
}