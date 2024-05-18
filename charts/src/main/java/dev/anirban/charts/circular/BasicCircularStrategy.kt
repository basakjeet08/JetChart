package dev.anirban.charts.circular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.legend.NoLegendStrategy
import dev.anirban.charts.circular.exceptions.DecorationMismatch
import dev.anirban.charts.circular.foreground.DonutTargetForegroundStrategy
import dev.anirban.charts.circular.center.CircularCenterStrategy
import dev.anirban.charts.circular.center.ImageCenterStrategy
import dev.anirban.charts.circular.center.NoCenterStrategy
import dev.anirban.charts.circular.legend.CircularLegendStrategy
import dev.anirban.charts.circular.data.CircularDataStrategy
import dev.anirban.charts.circular.data.ListDataStrategy
import dev.anirban.charts.circular.data.TargetDataStrategy
import dev.anirban.charts.circular.exceptions.CircularExceptionStrategy
import dev.anirban.charts.circular.foreground.CircularForegroundStrategy
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.foreground.DonutForegroundStrategy


/**
 * This class extends from the [CircularChartStrategy] which means its the root level class and it
 * also implements [CircularExceptionStrategy] implementation which provides an implementation for
 * handling all the Exceptions
 *
 * @param circularCenter This is the strategy to draw the center of the chart.
 * @param circularData This is the strategy to store and manipulate the circular chart data.
 * @param circularDecoration This contains the details of the decorations for the
 * color and all those color related Stuff
 * @param circularForeground This is the strategy to draw the main plot or the foreground of
 * the chart.
 * @param circularLegend This strategy draws the legends for the Chart
 */
open class BasicCircularStrategy(
    override val circularCenter: CircularCenterStrategy,
    override val circularData: CircularDataStrategy,
    override val circularDecoration: CircularDecoration,
    override val circularForeground: CircularForegroundStrategy,
    override val circularLegend: CircularLegendStrategy
) : CircularChartStrategy, CircularExceptionStrategy {


    /**
     * This validates that the decoration stuffs are given correctly or not
     */
    override fun validateDecoration() {
        if (circularDecoration.colorList.size < circularData.itemsList.size)
            throw DecorationMismatch(
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
     * This function draws the center of the Chart
     */
    @Composable
    override fun DrawCenter() {
        circularCenter.DrawCenter(
            circularData = circularData,
            decoration = circularDecoration
        )
    }


    /**
     * This function calculates the data for the Chart
     */
    override fun doCalculations() {
        circularData.doCalculations()
    }


    /**
     * This function draws the main plots and the foreground of the Chart
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
     * This function draws the legends of the Chart
     */
    @Composable
    override fun DrawLegends() {
        circularLegend.DrawColorConventions(
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
            DrawLegends()
        }

    }

    /**
     * Builder Composable Functions which makes the objects of [BasicCircularStrategy] and these are
     * actually called by the users to make charts
     */
    companion object {


        /**
         * This function creates an object of the [BasicCircularStrategy] which draws a basic
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
        fun BasicDonutPlot(
            modifier: Modifier = Modifier,
            circularCenter: NoCenterStrategy = NoCenterStrategy,
            circularData: ListDataStrategy,
            circularDecoration: CircularDecoration = CircularDecoration.targetChartColor(),
            circularForeground: DonutForegroundStrategy = DonutForegroundStrategy(),
            legend: CircularLegendStrategy = NoLegendStrategy
        ) = BasicCircularStrategy(
            circularCenter = circularCenter,
            circularData = circularData,
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularLegend = legend
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the [BasicCircularStrategy] which draws a basic
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
        fun BasicDonutTargetPlot(
            modifier: Modifier = Modifier,
            circularCenter: CircularCenterStrategy = NoCenterStrategy,
            circularData: CircularDataStrategy,
            circularDecoration: CircularDecoration = CircularDecoration.targetChartColor(),
            circularForeground: DonutTargetForegroundStrategy = DonutTargetForegroundStrategy(),
            legend: NoLegendStrategy = NoLegendStrategy
        ) = BasicCircularStrategy(
            circularCenter = circularCenter,
            circularData = circularData,
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularLegend = legend
        ).Build(modifier = modifier)


        @Composable
        fun WeeklyProgressPlot(
            modifier: Modifier = Modifier,
            weeklyData: List<TargetDataStrategy>,
            circularCenter: CircularCenterStrategy = ImageCenterStrategy(),
        ) {
            Row(modifier = modifier) {

                listOf("M", "T", "W", "T", "F", "S", "S").forEachIndexed { index, week ->

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        if (weeklyData[index].itemsList[0].second > weeklyData[index].itemsList[1].second)
                            BasicDonutTargetPlot(
                                modifier = Modifier.size(55.dp),
                                circularData = weeklyData[index],
                                circularForeground = DonutTargetForegroundStrategy(
                                    strokeWidth = 10f
                                ),
                                circularCenter = circularCenter
                            )
                        else
                            BasicDonutTargetPlot(
                                modifier = Modifier.size(55.dp),
                                circularData = weeklyData[index],
                                circularForeground = DonutTargetForegroundStrategy(
                                    strokeWidth = 10f
                                )
                            )


                        Text(
                            text = week,

                            // Text Features
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
        }


        /**
         * This function creates an object of the [BasicCircularStrategy] which draws a basic
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
        fun CustomBasicDonutPlot(
            modifier: Modifier = Modifier,
            circularCenter: CircularCenterStrategy = NoCenterStrategy,
            circularData: CircularDataStrategy,
            circularDecoration: CircularDecoration = CircularDecoration.targetChartColor(),
            circularForeground: CircularForegroundStrategy = DonutTargetForegroundStrategy(),
            legend: CircularLegendStrategy = NoLegendStrategy
        ) = BasicCircularStrategy(
            circularCenter = circularCenter,
            circularData = circularData,
            circularDecoration = circularDecoration,
            circularForeground = circularForeground,
            circularLegend = legend
        ).Build(modifier = modifier)
    }
}