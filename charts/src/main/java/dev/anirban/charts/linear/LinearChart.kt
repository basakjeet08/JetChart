package dev.anirban.charts.linear

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import dev.anirban.charts.linear.exceptions.LinearChartTypeMismatch
import dev.anirban.charts.linear.exceptions.LinearDataMismatch
import dev.anirban.charts.linear.exceptions.LinearDecorationMismatch
import dev.anirban.charts.linear.interfaces.LinearChartExceptionHandler
import dev.anirban.charts.linear.interfaces.LinearChartInterface
import dev.anirban.charts.linear.interfaces.LinearLegendDrawer
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.linear.interfaces.LinearMarginInterface
import dev.anirban.charts.linear.interfaces.LinearPlotInterface
import dev.anirban.charts.linear.legends.LinearNoLegend
import dev.anirban.charts.linear.legends.LinearGridLegend
import dev.anirban.charts.linear.data.LinearEmojiData
import dev.anirban.charts.linear.data.LinearStringData
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.margins.LinearEmojiMargin
import dev.anirban.charts.linear.margins.LinearStringMargin
import dev.anirban.charts.linear.plots.LinearBarPlot
import dev.anirban.charts.linear.plots.LinearGradientLinePlot
import dev.anirban.charts.linear.plots.LinearLinePlot

/**
 * This is the base class which directly implements the [LinearDataInterface] interfaces.
 *
 * @param margin This is the implementation for drawing the Margins
 * @param decoration This is the implementation for drawing the Decorations
 * @param linearData This is the implementation for keeping the Linear Chart data and calculations
 * @param plot This is the implementation for how shall the plotting be drawn on the graph
 * @param legendDrawer This is the implementation for how we are  going to draw all the color
 * conventions in the graph
 */
open class LinearChart(
    override val margin: LinearMarginInterface,
    override val decoration: LinearDecoration,
    override val linearData: LinearDataInterface,
    override val plot: LinearPlotInterface,
    override val legendDrawer: LinearLegendDrawer
) : LinearChartInterface, LinearChartExceptionHandler {

    /**
     * This functions validates the [LinearDataInterface] is implemented properly and all the
     * data is given properly over there
     */
    override fun validateDataInput() {

        var maxSize = -1

        // calculating the number of max Y - Axis Readings in a particular Coordinate set
        linearData.dataSets.forEach {
            if (it.size > maxSize)
                maxSize = it.size
        }

        // Comparing the num of max Y - Axis Readings to X - Axis Readings/Markers
        if (linearData.xAxisLabels.size < maxSize)
            throw LinearDataMismatch("X - Axis Markers Size is less than Number of Y - Axis Reading")
    }

    /**
     * This function validates the [LinearDecoration] is implemented properly and all the data
     * needed for the Linear Decoration are provided properly
     */
    override fun validateDecorationInput() {

        // checking if we have enough Primary Color for the plots
        if (decoration.plotPrimaryColor.size < linearData.dataSets.size) {
            if (plot is LinearBarPlot && decoration.plotPrimaryColor.isEmpty())
                throw Exception(
                    "plotPrimaryColor for the decoration have 0 Colors whereas at least " +
                            "one color needs to be provided"
                )
            else
                throw LinearDecorationMismatch(
                    "Need to provide ${linearData.dataSets.size} number of colors for the " +
                            "plotPrimaryColor"
                )
        }

        // checking if we have enough Secondary Color for the plots
        if (decoration.plotSecondaryColor.size < linearData.dataSets.size && plot !is LinearBarPlot)
            throw LinearDecorationMismatch(
                "Secondary Color of Decoration Class needs " +
                        "${linearData.dataSets.size} colors but it has " +
                        "${decoration.plotSecondaryColor.size} colors"
            )

    }

    /**
     * This function validates if the margin and the data passed are supported or not so it can give
     * a meaningful result to the developer
     */
    override fun validateTypeMismatch() {
        if (linearData is LinearEmojiData && margin !is LinearEmojiMargin)
            throw LinearChartTypeMismatch(
                "Need to pass a Margin of Type LinearEmojiMargin for a " +
                        "data of type LinearEmojiData"
            )

        if (margin is LinearEmojiMargin && linearData !is LinearEmojiData)
            throw LinearChartTypeMismatch(
                "Need to pass a Data of Type LinearEmojiData for a " +
                        "margin of type LinearEmojiMargin"
            )
    }

    /**
     * This function draws the margins according to the margin implementation passed to it
     */
    override fun DrawScope.drawMargin() {
        margin.apply {
            drawMargin(
                linearData = linearData,
                decoration = decoration
            )
        }
    }

    /**
     * This function draws the plotting of the chart
     */
    override fun DrawScope.plotChart() {
        plot.apply {
            plotChart(
                linearData = linearData,
                decoration = decoration
            )
        }
    }

    /**
     * This function draws the Color Conventions of the Chart
     */
    @Composable
    override fun DrawLegends() {
        legendDrawer.DrawLegends(
            data = linearData,
            decoration = decoration
        )
    }

    /**
     * This is the Build Function which starts composing the Charts and composes the Charts
     *
     * @param modifier This is for default modifications to be passed from the parent Class
     */
    @Composable
    override fun Build(modifier: Modifier) {

        // Validating all the Data if there is any exceptions
        validateDataInput()
        validateDecorationInput()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 24.dp, bottom = 18.dp, end = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .drawBehind {

                        // Calling all the necessary functions
                        linearData.apply {
                            doCalculations(size = size)
                        }
                        drawMargin()
                        plotChart()

                    }
            )

            // Checking if the implementation is the default one
            if (legendDrawer !is LinearNoLegend) {
                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    thickness = 2.dp,
                    color = decoration.plotPrimaryColor.first()
                )

                // Draws the color conventions for the chart
                DrawLegends()
            }
        }
    }


    /**
     * Builder Composable Functions which makes the objects of [LinearChart] and these are
     * actually called by the users to make charts
     */
    companion object {

        /**
         * This function creates an object of the LinearChart which draws a basic Line chart
         * It can draw Single Line Charts as well as multiple Line Charts with String Markers
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param margin This is the implementation for drawing the Margins
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param colorConvention This is the implementation for how we are going to draw all the
         * color conventions in the graph
         */
        @Composable
        fun LineChart(
            modifier: Modifier = Modifier,
            margin: LinearStringMargin = LinearStringMargin(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearStringData,
            plot: LinearLinePlot = LinearLinePlot(),
            colorConvention: LinearLegendDrawer = LinearGridLegend()
        ) = LinearChart(
            margin = margin,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = colorConvention
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Line chart
         * It can draw Single Line Charts as well as multiple Line Charts with Drawable Markers or
         * Emoji Markers but drawables should be converted into [BitmapDrawable] first for this to
         * work
         *
         *              Note :-
         *                  1. ContextCompat.getDrawable(
         *                          LocalContext.current,
         *                          R.drawable.emoji_furious
         *                     ) as BitmapDrawable
         *                  It is the code to convert a drawable into a Bitmap Drawable
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param margin This is the implementation for drawing the Margins
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param colorConvention This is the implementation for how we are going to draw all
         * the color conventions in the graph
         */
        @Composable
        fun EmojiLineChart(
            modifier: Modifier = Modifier,
            margin: LinearEmojiMargin = LinearEmojiMargin(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearEmojiData,
            plot: LinearLinePlot = LinearLinePlot(),
            colorConvention: LinearLegendDrawer = LinearNoLegend
        ) = LinearChart(
            margin = margin,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = colorConvention
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Line chart
         * It can draw Single Line Charts as well as multiple Line Charts with String Markers and
         * a gradient Plotting
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param margin This is the implementation for drawing the Margins
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param colorConvention This is the implementation for how we are going to draw all
         * the color conventions in the graph
         */
        @Composable
        fun GradientChart(
            modifier: Modifier = Modifier,
            margin: LinearStringMargin = LinearStringMargin(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearStringData,
            plot: LinearGradientLinePlot,
            colorConvention: LinearLegendDrawer = LinearNoLegend
        ) = LinearChart(
            margin = margin,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = colorConvention
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Bar chart
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param margin This is the implementation for drawing the Margins
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param colorConvention This is the implementation for how we are going to draw all
         * the color conventions in the graph
         */
        @Composable
        fun BarChart(
            modifier: Modifier = Modifier,
            margin: LinearStringMargin = LinearStringMargin(),
            decoration: LinearDecoration = LinearDecoration.barDecorationColors(),
            linearData: LinearStringData,
            plot: LinearBarPlot = LinearBarPlot(),
            colorConvention: LinearLegendDrawer = LinearGridLegend()
        ) = LinearChart(
            margin = margin,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = colorConvention
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Bar chart
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param margin This is the implementation for drawing the Margins
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param colorConvention This is the implementation for how we are going to draw all
         * the color conventions in the graph
         */
        @Composable
        fun EmojiBarChart(
            modifier: Modifier = Modifier,
            margin: LinearEmojiMargin = LinearEmojiMargin(),
            decoration: LinearDecoration = LinearDecoration.barDecorationColors(),
            linearData: LinearEmojiData,
            plot: LinearBarPlot = LinearBarPlot(),
            colorConvention: LinearLegendDrawer = LinearNoLegend
        ) = LinearChart(
            margin = margin,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = colorConvention
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Line chart
         * It can draw Single Line Charts as well as multiple Line Charts with custom objects passed
         * by the developer
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param margin This is the implementation for drawing the Margins
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param colorConvention This is the implementation for how we are going to draw all
         * the color conventions in the graph
         */
        @Composable
        fun CustomLinearChart(
            modifier: Modifier = Modifier,
            margin: LinearMarginInterface = LinearEmojiMargin(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearDataInterface,
            plot: LinearPlotInterface = LinearLinePlot(),
            colorConvention: LinearLegendDrawer = LinearNoLegend
        ) = LinearChart(
            margin = margin,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = colorConvention
        ).Build(modifier = modifier)
    }
}