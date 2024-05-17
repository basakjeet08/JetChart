package dev.anirban.charts.circular.charts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import dev.anirban.charts.circular.BasicCircularStrategy
import dev.anirban.charts.circular.center.NoCenterStrategy
import dev.anirban.charts.circular.legend.GridLegendStrategy
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.foreground.DonutForegroundStrategy
import dev.anirban.charts.circular.center.CircularCenterStrategy
import dev.anirban.charts.circular.legend.CircularLegendStrategy
import dev.anirban.charts.circular.data.CircularDataStrategy
import dev.anirban.charts.circular.foreground.CircularForegroundStrategy


/**
 * This class is the sub - class of [BasicCircularStrategy] class which is the root parent class.
 *
 * This class in general provides an implementation for a donut chart which has its legends
 * under the plot.
 *
 * For other implementations see [BasicCircularStrategy], [DonutRowChartStrategy]
 *
 * @param circularCenter This is the strategy to draw the center of the chart.
 * @param circularData This is the strategy to store and manipulate the circular chart data.
 * @param circularDecoration This contains the details of the decorations for the color and
 * all those color related Stuff
 * @param circularForeground This is the strategy to draw the main plot or the foreground of
 * the chart.
 * @param circularLegend This strategy draws the legends for the Chart
 */
class DonutColumnChartStrategy(
    override val circularCenter: CircularCenterStrategy,
    override val circularData: CircularDataStrategy,
    override val circularDecoration: CircularDecoration,
    override val circularForeground: CircularForegroundStrategy,
    override val circularLegend: CircularLegendStrategy
) : BasicCircularStrategy(
    circularCenter,
    circularData,
    circularDecoration,
    circularForeground,
    circularLegend
) {


    /**
     * This is the Build Function which starts composing the Charts and composes the Charts
     *
     * @param modifier This is for default modifications to be passed from the parent Class
     */
    @Composable
    override fun Build(modifier: Modifier) {

        // Making a row to fit the canvas and the color conventions
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Validating the Inputs
            super.validateAll()

            // Donut Chart
            Box(
                modifier = modifier
                    .size(180.dp)
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

            // Color Conventions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                // Calling all the necessary functions
                super.DrawLegends()
            }
        }
    }


    /**
     * Builder Composable Functions which makes the objects of [DonutColumnChartStrategy] and these are
     * actually called by the users to make charts
     */
    companion object {


        /**
         * This function creates an object of the [DonutColumnChartStrategy] which draws a basic
         * donut chart with its color conventions drawn at bottom
         *
         * @param modifier This is for modifications to be passed from the Parent Function
         * @param circularCenter This is the implementation which draws the center of the circle
         * @param circularData This is the data class implementation which handles the data
         * @param circularDecoration This is the decorations for the Circular Chart
         * @param circularForeground This is the implementation which draws the foreground of the chart
         * @param circularColorConvention This is the color Convention implementation of the chart
         */
        @Composable
        fun DonutChartColumn(
            modifier: Modifier = Modifier,
            circularCenter: CircularCenterStrategy = NoCenterStrategy,
            circularData: CircularDataStrategy,
            circularDecoration: CircularDecoration = CircularDecoration.donutChartDecorations(),
            circularForeground: CircularForegroundStrategy = DonutForegroundStrategy(),
            circularColorConvention: CircularLegendStrategy = GridLegendStrategy()
        ) = DonutColumnChartStrategy(
            circularCenter = circularCenter,
            circularData = circularData,
            circularForeground = circularForeground,
            circularDecoration = circularDecoration,
            circularLegend = circularColorConvention
        ).Build(modifier = modifier)
    }
}