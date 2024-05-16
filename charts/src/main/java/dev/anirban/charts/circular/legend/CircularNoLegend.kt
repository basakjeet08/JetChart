package dev.anirban.charts.circular.legend

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataInterface


/**
 * This class is the implementation of [CircularLegendInterface] which provides the
 * implementations for drawing the color conventions in the canvas
 */
class CircularNoLegend : CircularLegendInterface {


    /**
     * This function draws the color conventions in the canvas. However in this particular case
     * there is no color convention to be drawn
     *
     * @param circularData This object contains the data of the graph
     * @param decoration THis object contains the decorations of the graph
     */
    @Composable
    override fun DrawColorConventions(
        circularData: CircularDataInterface,
        decoration: CircularDecoration
    ) = Unit
}