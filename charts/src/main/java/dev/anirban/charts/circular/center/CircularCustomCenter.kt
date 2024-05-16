package dev.anirban.charts.circular.center

import androidx.compose.runtime.Composable
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataInterface


/**
 * This class is the implementation of [CircularCenterInterface] which focuses on providing an
 * opportunity to the developer to provide his own custom implementation for the Center
 *
 * This Class in particular is the implementation to draw texts
 *
 * @param body This contains the Composable function passed by the Dev to draw his custom
 * implementation for the Circle Chart Center
 */
class CircularCustomCenter(
    private val body: @Composable () -> Unit
) : CircularCenterInterface {


    /**
     * This function uses the implementation of UI given by developer and draws the Center
     *
     * @param circularData This object contains the data of the graph
     * @param decoration THis object contains the decorations of the graph
     */
    @Composable
    override fun DrawCenter(
        circularData: CircularDataInterface,
        decoration: CircularDecoration
    ) = body()
}