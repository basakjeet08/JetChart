package dev.anirban.charts.linear.interfaces

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.colorconvention.*

/**
 * This implementation shall be implemented by all the classes which are making color convention
 * implementation
 *
 * Implementations for this interface are :- [NoLegend],
 * [LinearGridLegend]
 *
 * @property DrawLegends THis function draws the desired color Convention
 */
interface LinearLegends {


    /**
     * This is the function which draws all the color convention
     */
    @Composable
    fun DrawLegends(
        data: LinearDataInterface,
        decoration: LinearDecoration
    )
}