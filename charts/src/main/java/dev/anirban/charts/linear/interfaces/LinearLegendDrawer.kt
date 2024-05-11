package dev.anirban.charts.linear.interfaces

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.legends.*

/**
 * This implementation shall be implemented by all the classes which are making colour code for
 * legends in the linear chart.
 *
 * Implementations for this interface are :- [LinearNoLegend], [LinearGridLegend],
 * [LinearCustomLegend]
 *
 * @property DrawLegends This function draws the desired color Convention
 */
interface LinearLegendDrawer {


    /**
     * This function draws the desired Legends.
     *
     * @param data This is the data of the chart.
     * @param decoration This is the decoration of the chart.
     */
    @Composable
    fun DrawLegends(
        data: LinearDataInterface,
        decoration: LinearDecoration
    )
}