package dev.anirban.charts.linear

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
import dev.anirban.charts.linear.exceptions.DataMismatchStrategy
import dev.anirban.charts.linear.exceptions.DecorationMismatchStrategy
import dev.anirban.charts.linear.exceptions.LinearExceptionStrategy
import dev.anirban.charts.linear.legends.LinearLegendStrategy
import dev.anirban.charts.linear.data.LinearDataStrategy
import dev.anirban.charts.linear.labels.LinearLabelStrategy
import dev.anirban.charts.linear.plots.LinearPlotterStrategy
import dev.anirban.charts.linear.legends.NoLegendStrategy
import dev.anirban.charts.linear.legends.GridLegendStrategy
import dev.anirban.charts.linear.data.BasicDataStrategy
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.labels.StringLabelStrategy
import dev.anirban.charts.linear.plots.BarPlotStrategy
import dev.anirban.charts.linear.plots.GradientPlotStrategy
import dev.anirban.charts.linear.plots.LinePlotStrategy


/**
 * This is the base class which directly implements the [LinearDataStrategy] interfaces.
 *
 * @param labelDrawer This is the implementation of the [LinearLabelStrategy]. The labels
 * will be drawn in the graph according to the implementation
 * @param decoration This is the implementation of the [LinearDecoration]. The decoration of
 * different elements will be provided by this object
 * @param linearData This is the implementation of the [LinearDataStrategy]. The data along with
 * the chart offsets will be calculated according to this Login
 * @param plot This is the implementation of the [LinearPlotterStrategy]. This is responsible for
 * providing the logic to draw plots in the chart.
 * @param legendDrawer This is the implementation of [LinearLegendStrategy]. This provides an
 * implementation for drawing the legends of the chart
 */
open class BasicLinearStrategy(
    override val labelDrawer: LinearLabelStrategy,
    override val decoration: LinearDecoration,
    override val linearData: LinearDataStrategy,
    override val plot: LinearPlotterStrategy,
    override val legendDrawer: LinearLegendStrategy
) : LinearChartStrategy, LinearExceptionStrategy {


    /**
     * This functions validates the [LinearDataStrategy] is implemented properly and all the
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
            throw DataMismatchStrategy("X - Axis Labels Size is less than Number of observations")
    }

    /**
     * This function validates the [LinearDecoration] is implemented properly and all the data
     * needed for the Linear Decoration are provided properly
     */
    override fun validateDecorationInput() {

        // checking if we have enough Primary Color for the plots
        if (decoration.plotPrimaryColor.size < linearData.linearDataSets.size) {
            if (plot is BarPlotStrategy && decoration.plotPrimaryColor.isEmpty())
                throw Exception(
                    "plotPrimaryColor for the decoration have 0 Colors whereas at least " +
                            "one color needs to be provided"
                )
            else
                throw DecorationMismatchStrategy(
                    "Need to provide ${linearData.linearDataSets.size} number of colors for the " +
                            "plotPrimaryColor"
                )
        }
    }


    /**
     * This function draws the various labels and the Axis Lines of the graph according to the
     * [LinearChartStrategy.labelDrawer] implementation.
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
     * This function draws the plots of the graph according to the [LinearChartStrategy.plot]
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
     * This function draws the legends of the graph according to the [LinearChartStrategy.legendDrawer]
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
            modifier = Modifier.fillMaxWidth(),
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
            if (legendDrawer !is NoLegendStrategy) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = decoration.textColor
                    )

                    // Draws the color conventions for the chart
                    DrawLegends()
                }
            }
        }
    }


    /**
     * Builder Composable Functions which makes the objects of [BasicLinearStrategy] and these are
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
            labelDrawer: StringLabelStrategy = StringLabelStrategy(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: BasicDataStrategy,
            plot: LinePlotStrategy = LinePlotStrategy(),
            legendDrawer: LinearLegendStrategy = GridLegendStrategy()
        ) = BasicLinearStrategy(
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
            labelDrawer: StringLabelStrategy = StringLabelStrategy(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: BasicDataStrategy,
            plot: GradientPlotStrategy = GradientPlotStrategy(),
            legendDrawer: LinearLegendStrategy = NoLegendStrategy
        ) = BasicLinearStrategy(
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
            labelDrawer: StringLabelStrategy = StringLabelStrategy(),
            decoration: LinearDecoration = LinearDecoration.barDecorationColors(),
            linearData: BasicDataStrategy,
            plot: BarPlotStrategy = BarPlotStrategy(),
            legendDrawer: LinearLegendStrategy = GridLegendStrategy()
        ) = BasicLinearStrategy(
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
            labelDrawer: LinearLabelStrategy = StringLabelStrategy(),
            decoration: LinearDecoration = LinearDecoration.lineDecorationColors(),
            linearData: LinearDataStrategy,
            plot: LinearPlotterStrategy = LinePlotStrategy(),
            legendDrawer: LinearLegendStrategy = NoLegendStrategy
        ) = BasicLinearStrategy(
            labelDrawer = labelDrawer,
            decoration = decoration,
            linearData = linearData,
            plot = plot,
            legendDrawer = legendDrawer
        ).Build(modifier = modifier)
    }
}