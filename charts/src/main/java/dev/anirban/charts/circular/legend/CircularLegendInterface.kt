package dev.anirban.charts.circular.legend

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataInterface

/**
 * This implementation shall be implemented by all the classes which are making color convention
 * implementation
 *
 * Implementations for this interface are :- [CircularNoLegend],
 * [CircularGridLegend],[CircularListLegend],[CircularTargetLegend]

 *
 * @property DrawColorConventions THis function draws the desired color Convention
 */
interface CircularLegendInterface {

    @Composable
    fun DrawColorConventions(
        circularData: CircularDataInterface,
        decoration: CircularDecoration
    )
}