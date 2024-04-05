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
import dev.anirban.charts.circular.colorconvention.CircularTargetColorConvention
import dev.anirban.charts.circular.CircularChart
import dev.anirban.charts.circular.center.CircularDefaultCenter
import dev.anirban.charts.circular.center.CircularTargetTextCenter
import dev.anirban.charts.circular.colorconvention.CircularListColorConvention
import dev.anirban.charts.circular.data.CircularTargetDataBuilder
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.foreground.CircularDonutForeground
import dev.anirban.charts.circular.foreground.CircularDonutTargetForeground
import dev.anirban.charts.circular.interfaces.CircularCenterInterface
import dev.anirban.charts.circular.interfaces.CircularColorConventionInterface
import dev.anirban.charts.circular.interfaces.CircularDataInterface
import dev.anirban.charts.circular.interfaces.CircularForegroundInterface


/**
 * This class is the sub - class of [CircularChart] class which is the root parent class of the
 * circular charts.
 *
 * This class in general provides an implementation for a donut chart which has its color conventions
 * in the same row as itself.
 *
 * @param circularCenter This is the implementation which draws the center of the circle
 * @param circularData This is the data class implementation which handles the data
 * @param circularDecoration This is the decorations for the Circular Chart
 * @param circularForeground This is the implementation which draws the foreground of the chart
 * @param circularColorConvention This is the color Convention implementation of the chart
 */
open class CircularDonutChartRow(
    override val circularCenter: CircularCenterInterface,
    override val circularData: CircularDataInterface,
    override val circularDecoration: CircularDecoration,
    override val circularForeground: CircularForegroundInterface,
    override val circularColorConvention: CircularColorConventionInterface
) : CircularChart(
    circularCenter,
    circularData,
    circularDecoration,
    circularForeground,
    circularColorConvention
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
                    super.DrawColorConventions()
                }
            }
        }
    }

    /**
     * Builder Composable Functions which makes the objects of [CircularDonutChartRow] and these are
     * actually called by the users to make charts
     */
    companion object {

        /**
         * This function creates an object of the [CircularDonutChartRow] which draws a basic
         * donut chart with its color conventions drawn at side
         *
         * @param modifier THis is made so that modifications can be passed from the parent function
         * @param circularCenter This is the implementation which draws the center of the circle
         * @param circularData This is the data class implementation which handles the data
         * @param circularDecoration This is the decorations for the Circular Chart
         * @param circularForeground This is the implementation which draws the foreground of the chart
         * @param circularColorConvention This is the color Convention implementation of the chart
         */
        @Composable
        fun DonutChartRow(
            modifier: Modifier = Modifier,
            circularCenter: CircularCenterInterface = CircularDefaultCenter(),
            circularData: CircularDataInterface,
            circularDecoration: CircularDecoration = CircularDecoration.donutChartDecorations(),
            circularForeground: CircularForegroundInterface = CircularDonutForeground(),
            circularColorConvention: CircularColorConventionInterface = CircularListColorConvention()
        ) = CircularDonutChartRow(
            circularCenter = circularCenter,
            circularData = circularData,
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularColorConvention = circularColorConvention
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the [CircularDonutChartRow] which draws a basic
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
        fun TargetDonutChart(
            modifier: Modifier = Modifier,
            circularCenter: CircularCenterInterface = CircularTargetTextCenter(),
            circularData: CircularTargetDataBuilder,
            circularDecoration: CircularDecoration = CircularDecoration.donutChartDecorations(),
            circularForeground: CircularForegroundInterface = CircularDonutTargetForeground(),
            circularColorConvention: CircularColorConventionInterface = CircularTargetColorConvention()
        ) = CircularDonutChartRow(
            circularCenter = circularCenter,
            circularData = circularData.toCircularDonutTargetData(),
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularColorConvention = circularColorConvention
        ).Build(modifier = modifier)
    }
}