package dev.anirban.charts.linear.colorconvention

import androidx.compose.runtime.Composable
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearColorConventionInterface

/**
 * This class is the implementation of [LinearColorConventionInterface] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 * @property textList This contains the list of strings which needs to be drawn in the Chart
 */
class LinearDefaultColorConvention : LinearColorConventionInterface {

    override val textList: List<String> = emptyList()

    /**
     * This function draws the color conventions. In this case this draws nothing in the Chart
     */
    @Composable
    override fun DrawColorConventions(
        decoration: LinearDecoration
    ) {

        // Do Nothing
    }
}