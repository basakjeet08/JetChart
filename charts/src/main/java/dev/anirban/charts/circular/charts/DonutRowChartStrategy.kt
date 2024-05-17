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
import dev.anirban.charts.circular.legend.ListLegendStrategy
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.foreground.DonutForegroundStrategy
import dev.anirban.charts.circular.foreground.DonutTargetForegroundStrategy
import dev.anirban.charts.circular.center.CircularCenterStrategy
import dev.anirban.charts.circular.legend.CircularLegendStrategy
import dev.anirban.charts.circular.data.CircularDataStrategy
import dev.anirban.charts.circular.data.TargetDataStrategy
import dev.anirban.charts.circular.foreground.CircularForegroundStrategy


/**
 * This class is the sub - class of [BasicCircularStrategy] class which is the root parent class.
 *
 * This class in general provides an implementation for a donut chart which has its legends
 * in the same row as itself.
 *
 * For other implementations see [BasicCircularStrategy], [DonutColumnChartStrategy]
 *
 * @param circularCenter This is the strategy to draw the center of the chart
 * @param circularData This is the strategy to store and manipulate the circular chart data.
 * @param circularDecoration This contains the details of the decorations for the color and
 * all those color related Stuff
 * @param circularForeground This is the strategy to draw the main plot or the foreground
 * of the chart.
 * @param circularLegend This strategy draws the legends for the Chart
 */
open class DonutRowChartStrategy(
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

        // Validating the Inputs
        super.validateAll()

        // Making a row to fit the canvas and the color conventions
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                // Donut Chart
                Box(
                    modifier = modifier
                        .weight(1f)
                        .size(180.dp)
                        .drawBehind {

                            // Calling all the necessary functions
                            doCalculations()
                            drawForeground()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    // Draws the center of the chart
                    DrawCenter()
                }

                // Color Conventions
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f),
                    contentAlignment = Alignment.TopCenter
                ) {

                    // Calling all the necessary functions
                    super.DrawLegends()
                }
            }
        }
    }

    /**
     * Builder Composable Functions which makes the objects of [DonutRowChartStrategy] and these are
     * actually called by the users to make charts
     */
    companion object {


        /**
         * This function creates an object of the [DonutRowChartStrategy] which draws a basic
         * donut chart with its color conventions drawn at side
         *
         * @param modifier THis is made so that modifications can be passed from the parent function
         * @param circularCenter This is the implementation which draws the center of the circle
         * @param circularData This is the data class implementation which handles the data
         * @param circularDecoration This is the decorations for the Circular Chart
         * @param circularForeground This is the implementation which draws the foreground of the chart
         * @param legend This is the color Convention implementation of the chart
         */
        @Composable
        fun DonutChartRow(
            modifier: Modifier = Modifier,
            circularCenter: NoCenterStrategy = NoCenterStrategy,
            circularData: CircularDataStrategy,
            circularDecoration: CircularDecoration = CircularDecoration.donutChartDecorations(),
            circularForeground: DonutForegroundStrategy = DonutForegroundStrategy(),
            legend: ListLegendStrategy = ListLegendStrategy()
        ) = DonutRowChartStrategy(
            circularCenter = circularCenter,
            circularData = circularData,
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularLegend = legend
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the [DonutRowChartStrategy] which draws a basic
         * donut chart with its color conventions drawn at side but the data is in the form of
         * Target and Achieved
         *
         * @param modifier This is made so that modifications can be passed from the parent function
         * @param circularCenter This is the implementation which draws the center of the circle
         * @param circularData This is the data class implementation which handles the data
         * @param circularDecoration This is the decorations for the Circular Chart
         * @param circularForeground This is the implementation which draws the foreground of the chart
         * @param legend This is the color Convention implementation of the chart
         */
        @Composable
        fun TargetDonutChartRow(
            modifier: Modifier = Modifier,
            circularCenter: NoCenterStrategy = NoCenterStrategy,
            circularData: TargetDataStrategy,
            circularDecoration: CircularDecoration = CircularDecoration.targetChartColor(),
            circularForeground: DonutTargetForegroundStrategy = DonutTargetForegroundStrategy(),
            legend: ListLegendStrategy = ListLegendStrategy()
        ) = DonutRowChartStrategy(
            circularCenter = circularCenter,
            circularData = circularData,
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularLegend = legend
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the [DonutRowChartStrategy] which draws a basic
         * donut chart with its color conventions drawn at side but the data is in the form of
         * Target and Achieved
         *
         * @param modifier THis is made so that modifications can be passed from the parent function
         * @param circularCenter This is the implementation which draws the center of the circle
         * @param circularData This is the data class implementation which handles the data
         * @param circularDecoration This is the decorations for the Circular Chart
         * @param circularForeground This is the implementation which draws the foreground of the chart
         * @param legend This is the color Convention implementation of the chart
         */
        @Composable
        fun CustomDonutRowChart(
            modifier: Modifier = Modifier,
            circularCenter: CircularCenterStrategy = NoCenterStrategy,
            circularData: CircularDataStrategy,
            circularDecoration: CircularDecoration = CircularDecoration.targetChartColor(),
            circularForeground: CircularForegroundStrategy = DonutTargetForegroundStrategy(),
            legend: CircularLegendStrategy = ListLegendStrategy()
        ) = DonutRowChartStrategy(
            circularCenter = circularCenter,
            circularData = circularData,
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularLegend = legend
        ).Build(modifier = modifier)
    }
}