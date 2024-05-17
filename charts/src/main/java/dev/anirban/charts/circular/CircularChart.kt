package dev.anirban.charts.circular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import dev.anirban.charts.circular.center.CircularImageCenter
import dev.anirban.charts.circular.legend.CircularNoLegend
import dev.anirban.charts.circular.exceptions.CircularDecorationMismatch
import dev.anirban.charts.circular.foreground.CircularDonutTargetForeground
import dev.anirban.charts.circular.center.CircularCenterInterface
import dev.anirban.charts.circular.legend.CircularLegendInterface
import dev.anirban.charts.circular.data.CircularDataInterface
import dev.anirban.charts.circular.data.CircularDonutTargetData
import dev.anirban.charts.circular.exceptions.CircularExceptionHandler
import dev.anirban.charts.circular.foreground.CircularForegroundInterface
import dev.anirban.charts.circular.decoration.CircularDecoration

/**
 * This class extends from the [CircularChartInterface] which means its the root level class and it
 * also implements [CircularExceptionHandler] implementation which provides an implementation for
 * handling all the Exceptions
 *
 * @property circularCenter Implementation for the center of the chart
 * @property circularData Implementation for the data of the chart
 * @property circularDecoration Implementation for the decoration of the chart
 * @property circularForeground Implementation for the foreground of the chart
 * @property circularColorConvention Implementation for the color convention of the chart
 *
 * @property DrawCenter This function draws the center of the chart
 * @property doCalculations This function does the calculation of the chart
 * @property drawForeground This function draws the foreground of the chart
 * @property DrawColorConventions This function draws the Color Convention of the chart
 * @property Build This function starts building the circular Chart
 */
open class CircularChart(
    override val circularCenter: CircularCenterInterface,
    override val circularData: CircularDataInterface,
    override val circularDecoration: CircularDecoration,
    override val circularForeground: CircularForegroundInterface,
    override val circularColorConvention: CircularLegendInterface
) : CircularChartInterface, CircularExceptionHandler {

    /**
     * This validates that the decoration stuffs are given correctly or not
     */
    override fun validateDecoration() {
        if (circularDecoration.colorList.size < circularData.itemsList.size)
            throw CircularDecorationMismatch(
                "Need at least ${circularData.itemsList.size} amount" +
                        " of Colors for ${circularData.itemsList.size} number of Items in List" +
                        " where only ${circularDecoration.colorList.size} is passed"
            )
    }


    /**
     * This checks and validates all the Exceptions and see if all of them are okay before letting
     * the user run further for decreasing unnecessary confusion in finding the Exceptions
     */
    override fun validateAll() {
        validateDecoration()
    }

    /**
     * Function to draw something on the center
     */
    @Composable
    override fun DrawCenter() {
        circularCenter.DrawCenter(
            circularData = circularData,
            decoration = circularDecoration
        )
    }

    /**
     * This function does mostly handle business and calculation related
     */
    override fun doCalculations() {
        circularData.doCalculations()
    }

    /**
     * This function draws the foreground
     */
    override fun DrawScope.drawForeground() {
        circularForeground.apply {
            drawForeground(
                circularData = circularData,
                decoration = circularDecoration
            )
        }
    }

    /**
     * This function draws the Color Convention in the chart
     */
    @Composable
    override fun DrawColorConventions() {
        circularColorConvention.DrawColorConventions(
            circularData = circularData,
            decoration = circularDecoration
        )
    }

    /**
     * This is the Build Function which starts composing the Charts and composes the Charts
     *
     * @param modifier This is for default modifications to be passed from the parent Class
     */
    @Composable
    override fun Build(modifier: Modifier) {

        // Validating the Inputs
        validateAll()

        // Donut Chart
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = modifier
                    .size(300.dp)
                    .drawBehind {

                        // Calling all the necessary functions
                        doCalculations()
                        drawForeground()
                    },
                contentAlignment = Alignment.Center
            ) {

                // Draws the Center of the chart
                DrawCenter()
            }

            // This function draws the color convention
            DrawColorConventions()
        }

    }

    /**
     * Builder Composable Functions which makes the objects of [CircularChart] and these are
     * actually called by the users to make charts
     */
    companion object {


        /**
         * This function creates an object of the [CircularChart] which draws a basic
         * donut chart with its color conventions drawn at side but the data is in the form of
         * Target and Achieved
         *
         * @param modifier THis is made so that modifications can be passed from the parent function
         * @param circularCenter This is the implementation which draws the center of the circle
         * @param circularData This is the data class implementation which handles the data
         * @param circularDecoration This is the decorations for the Circular Chart
         * @param circularForeground This is the implementation which draws the foreground of the chart
         * @param circularColorConvention This is the color Convention implementation of the chart
         */
        @Composable
        fun DonutChartImage(
            modifier: Modifier = Modifier,
            circularCenter: CircularCenterInterface = CircularImageCenter(),
            circularData: CircularDonutTargetData,
            circularDecoration: CircularDecoration = CircularDecoration.targetChartColor(),
            circularForeground: CircularForegroundInterface = CircularDonutTargetForeground(),
            circularColorConvention: CircularLegendInterface = CircularNoLegend()
        ) = CircularChart(
            circularCenter = circularCenter,
            circularData = circularData,
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularColorConvention = circularColorConvention
        ).Build(modifier = modifier)
    }
}