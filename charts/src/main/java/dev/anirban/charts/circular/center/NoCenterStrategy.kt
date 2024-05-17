package dev.anirban.charts.circular.center

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * This class is the implementation of [CircularCenterStrategy] which focuses on providing an
 * implementation to draw something on the center of the Circular Chart
 *
 * This Class in particular is the implementation to draw nothing and it is served as a Default
 * implementation
 */
class NoCenterStrategy : CircularCenterStrategy {

    /**
     * This function does nothing which is fine since we want the default Circle Center to be nothing
     */
    @Composable
    override fun DrawCenter(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) = Unit
}