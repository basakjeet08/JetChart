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
import dev.anirban.charts.linear.interfaces.LinearLabelDrawerInterface
import dev.anirban.charts.linear.interfaces.LinearPlotInterface
import dev.anirban.charts.linear.legends.LinearNoLegend
import dev.anirban.charts.linear.legends.LinearGridLegend
import dev.anirban.charts.linear.data.LinearEmojiData
import dev.anirban.charts.linear.data.LinearStringData
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.labels.LinearEmojiLabelDrawer
import dev.anirban.charts.linear.labels.LinearStringLabelDrawer
import dev.anirban.charts.linear.plots.LinearBarPlot
import dev.anirban.charts.linear.plots.LinearGradientPlot
import dev.anirban.charts.linear.plots.LinearLinePlot

/**
 * This is the base class which directly implements the [LinearDataInterface] interfaces.
 *
 * @param labelDrawer This is the implementation of the [LinearLabelDrawerInterface]. The labels
 * will be drawn in the graph according to the implementation
 * @param decoration This is the implementation of the [LinearDecoration]. The decoration of
 * different elements will be provided by this object
 * @param linearData This is the implementation of the [LinearDataInterface]. The data along with
 * the chart offsets will be calculated according to this Login
 * @param plot This is the implementation of the [LinearPlotInterface]. This is responsible for
 * providing the logic to draw plots in the chart.
 * @param legendDrawer This is the implementation of [LinearLegendDrawer]. This provides an
 * implementation for drawing the legends of the chart
 */
open class LinearChart(
    override val labelDrawer: LinearLabelDrawerInterface,
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
        linearData.linearDataSets.forEach {
            if (it.size > maxSize)
                maxSize = it.size
        }

        // Comparing the num of max Y - Axis Readings to X - Axis Readings/Markers
        if (linearData.xAxisLabels.size < maxSize)
            throw LinearDataMismatch("X - Axis Labels Size is less than Number of observations")
    }

    /**
     * This function validates the [LinearDecoration] is implemented properly and all the data
     * needed for the Linear Decoration are provided properly
     */
    override fun validateDecorationInput() {

        // checking if we have enough Primary Color for the plots
        if (decoration.plotPrimaryColor.size < linearData.linearDataSets.size) {
            if (plot is LinearBarPlot && decoration.plotPrimaryColor.isEmpty())
                throw Exception(
                    "plotPrimaryColor for the decoration have 0 Colors whereas at least " +
                            "one color needs to be provided"
                )
            else
                throw LinearDecorationMismatch(
                    "Need to provide ${linearData.linearDataSets.size} number of colors for the " +
                            "plotPrimaryColor"
                )
        }

        // checking if we have enough Secondary Color for the plots
        if (decoration.plotSecondaryColor.size < linearData.linearDataSets.size && plot !is LinearBarPlot)
            throw LinearDecorationMismatch(
                "Secondary Color of Decoration Class needs " +
                        "${linearData.linearDataSets.size} colors but it has " +
                        "${decoration.plotSecondaryColor.size} colors"
            )

    }

    /**
     * This function validates if the label and the data passed are supported or not so it can
     * give a meaningful result to the developer
     */
    override fun validateTypeMismatch() {
        if (linearData is LinearEmojiData && labelDrawer !is LinearEmojiLabelDrawer)
            throw LinearChartTypeMismatch(
                "Need to pass a Label of Type LinearEmojiLabelDrawer for a " +
                        "data of type LinearEmojiData"
            )

        if (labelDrawer is LinearEmojiLabelDrawer && linearData !is LinearEmojiData)
            throw LinearChartTypeMismatch(
                "Need to pass a Data of Type LinearEmojiData for a " +
                        "margin of type LinearEmojiLabelDrawer"
            )
    }

    /**
     * This function draws the various labels and the Axis Lines of the graph according to the
     * [LinearChartInterface.labelDrawer] implementation.
     */
    override fun DrawScope.drawLabels() {
        labelDrawer.apply {
            drawLabels(
                linearData = linearData,
                decoration = decoration
            )
        }
    }

    /**
     * This function draws the plots of the graph according to the [LinearChartInterface.plot]
     * implementation.
     */
    override fun DrawScope.drawPlot() {
        plot.apply {
            plotChart(
                linearData = linearData,
                decoration = decoration
            )
        }
    }

    /**
     * This function draws the legends of the graph according to the [LinearChartInterface.legendDrawer]
     * implementation
     */
    @Composable
    override fun DrawLegends() {
        legendDrawer.DrawLegends(
            linearData = linearData,
            decoration = decoration
        )
    }

    /**
     * This function is called to start building the chart
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
                        drawLabels()
                        drawPlot()

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
         * It can draw Single Line Charts as well as multiple Line Charts with String Labels
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param labelDrawer This is the implementation for drawing the Margins
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param legendDrawer This is the implementation for how we are going to draw all the
         * legend in the graph
         */
        @Composable
        fun LineChart(
            modifier: Modifier = Modifier,
            labelDrawer: LinearStringLabelDrawer = LinearStringLabelDrawer(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearStringData,
            plot: LinearLinePlot = LinearLinePlot(),
            legendDrawer: LinearLegendDrawer = LinearGridLegend()
        ) = LinearChart(
            labelDrawer = labelDrawer,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = legendDrawer
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
         * @param labelDrawer This is the implementation for drawing the Labels
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param legendDrawer This is the implementation for how we are going to draw all
         * the legend in the graph
         */
        @Composable
        fun EmojiLineChart(
            modifier: Modifier = Modifier,
            labelDrawer: LinearEmojiLabelDrawer = LinearEmojiLabelDrawer(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearEmojiData,
            plot: LinearLinePlot = LinearLinePlot(),
            legendDrawer: LinearLegendDrawer = LinearNoLegend
        ) = LinearChart(
            labelDrawer = labelDrawer,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = legendDrawer
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Line chart
         * It can draw Single Line Charts as well as multiple Line Charts with String Markers and
         * a gradient Plotting
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param labelDrawer This is the implementation for drawing the Labels
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param legendDrawer This is the implementation for how we are going to draw all
         * the legend in the graph
         */
        @Composable
        fun GradientChart(
            modifier: Modifier = Modifier,
            labelDrawer: LinearStringLabelDrawer = LinearStringLabelDrawer(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearStringData,
            plot: LinearGradientPlot,
            legendDrawer: LinearLegendDrawer = LinearNoLegend
        ) = LinearChart(
            labelDrawer = labelDrawer,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = legendDrawer
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Bar chart
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param labelDrawer This is the implementation for drawing the Labels
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param legendDrawer This is the implementation for how we are going to draw all
         * the legend in the graph
         */
        @Composable
        fun BarChart(
            modifier: Modifier = Modifier,
            labelDrawer: LinearStringLabelDrawer = LinearStringLabelDrawer(),
            decoration: LinearDecoration = LinearDecoration.barDecorationColors(),
            linearData: LinearStringData,
            plot: LinearBarPlot = LinearBarPlot(),
            legendDrawer: LinearLegendDrawer = LinearGridLegend()
        ) = LinearChart(
            labelDrawer = labelDrawer,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = legendDrawer
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Bar chart
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param labelDrawer This is the implementation for drawing the Labels
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param legendDrawer This is the implementation for how we are going to draw all
         * the legend in the graph
         */
        @Composable
        fun EmojiBarChart(
            modifier: Modifier = Modifier,
            labelDrawer: LinearEmojiLabelDrawer = LinearEmojiLabelDrawer(),
            decoration: LinearDecoration = LinearDecoration.barDecorationColors(),
            linearData: LinearEmojiData,
            plot: LinearBarPlot = LinearBarPlot(),
            legendDrawer: LinearLegendDrawer = LinearNoLegend
        ) = LinearChart(
            labelDrawer = labelDrawer,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = legendDrawer
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the LinearChart which draws a basic Line chart
         * It can draw Single Line Charts as well as multiple Line Charts with custom objects passed
         * by the developer
         *
         * @param modifier This is to be passed from the Parent Class for the modifications
         * @param labelDrawer This is the implementation for drawing the Labels
         * @param decoration This is the implementation for drawing the Decorations
         * @param linearData This is the implementation for keeping the Linear Chart data and calculations
         * @param plot This is the implementation for how shall the plotting be drawn on the graph
         * @param legendDrawer This is the implementation for how we are going to draw all
         * the legend in the graph
         */
        @Composable
        fun CustomLinearChart(
            modifier: Modifier = Modifier,
            labelDrawer: LinearLabelDrawerInterface = LinearEmojiLabelDrawer(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearDataInterface,
            plot: LinearPlotInterface = LinearLinePlot(),
            legendDrawer: LinearLegendDrawer = LinearNoLegend
        ) = LinearChart(
            labelDrawer = labelDrawer,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = legendDrawer
        ).Build(modifier = modifier)
    }
}