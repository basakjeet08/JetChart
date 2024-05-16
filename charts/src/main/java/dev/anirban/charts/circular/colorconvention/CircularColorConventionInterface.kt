package dev.anirban.charts.circular.colorconvention

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataInterface

/**
 * This implementation shall be implemented by all the classes which are making color convention
 * implementation
 *
 * Implementations for this interface are :- [CircularNoColorConvention],
 * [CircularGridColorConvention],[CircularListColorConvention],[CircularTargetColorConvention]

 *
 * @property DrawColorConventions THis function draws the desired color Convention
 */
interface CircularColorConventionInterface {

    @Composable
    fun DrawColorConventions(
        circularData: CircularDataInterface,
        decoration: CircularDecoration
    )
}