package com.dev.anirban.charts.circular.charts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import com.dev.anirban.charts.circular.CircularChart
import com.dev.anirban.charts.circular.center.CircularRingTextCenter
import com.dev.anirban.charts.circular.colorconvention.CircularDefaultColorConvention
import dev.anirban.charts.circular.data.CircularTargetDataBuilder
import com.dev.anirban.charts.circular.decoration.CircularDecoration
import com.dev.anirban.charts.circular.foreground.CircularRingForeground
import com.dev.anirban.charts.circular.interfaces.CircularCenterInterface
import com.dev.anirban.charts.circular.interfaces.CircularColorConventionInterface
import com.dev.anirban.charts.circular.interfaces.CircularDataInterface
import com.dev.anirban.charts.circular.interfaces.CircularForegroundInterface

/**
 * This class is the sub - class of [CircularChart] class which is the root parent class of the
 * circular charts.
 *
 * This class in general provides an implementation for a ring chart.
 *
 * @param circularCenter This is the implementation which draws the center of the circle
 * @param circularData This is the data class implementation which handles the data
 * @param circularDecoration This is the decorations for the Circular Chart
 * @param circularForeground This is the implementation which draws the foreground of the chart
 * @param circularColorConvention This is the color Convention implementation of the chart
 */
class CircularRingChart(
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
     * Builder Composable Functions which makes the objects of [CircularDonutChartRow] and these are
     * actually called by the users to make charts
     */
    companion object {


        /**
         * This function creates an object of the [CircularRingChart] which draws a basic single ring chart
         *
         * @param modifier modifier to be passed down from the parent function
         * @param circularCenter This is the implementation which draws the center of the circle
         * @param circularData This is the data class implementation which handles the data
         * @param circularDecoration This is the decorations for the Circular Chart
         * @param circularForeground This is the implementation which draws the foreground of the chart
         * @param circularColorConvention This is the color Convention implementation of the chart
         */
        @Composable
        fun SingleRingChart(
            modifier: Modifier = Modifier,
            circularCenter: CircularCenterInterface = CircularRingTextCenter("", "", ""),
            circularData: CircularTargetDataBuilder,
            circularDecoration: CircularDecoration = CircularDecoration.ringChartDecoration(),
            circularForeground: CircularForegroundInterface = CircularRingForeground(),
            circularColorConvention: CircularColorConventionInterface = CircularDefaultColorConvention()
        ) = CircularRingChart(
            circularCenter,
            circularData.toCircularRingTargetData(),
            circularDecoration,
            circularForeground,
            circularColorConvention
        ).Build(modifier = modifier)


        /**
         * This function creates an object of the [CircularRingChart] which draws a basic multiple ring chart
         *
         * @param modifier modifier to be passed down from the parent function
         * @param circularCenter This is the implementation which draws the center of the circle
         * @param circularData This is the data class implementation which handles the data
         * @param circularDecoration This is the decorations for the Circular Chart
         * @param circularForeground This is the implementation which draws the foreground of the chart
         * @param circularColorConvention This is the color Convention implementation of the chart
         */
        @Composable
        fun MultipleRingChart(
            modifier: List<Modifier> = listOf(Modifier, Modifier),
            circularCenter: List<CircularCenterInterface> = listOf(
                CircularRingTextCenter("", "", ""),
                CircularRingTextCenter("", "", "")
            ),
            circularData: List<CircularTargetDataBuilder>,
            circularDecoration: List<CircularDecoration> = listOf(
                CircularDecoration.ringChartDecoration(),
                CircularDecoration.ringChartDecoration()
            ),
            circularForeground: List<CircularForegroundInterface> = listOf(
                CircularRingForeground(),
                CircularRingForeground()
            ),
            circularColorConvention: List<CircularColorConventionInterface> = listOf(
                CircularDefaultColorConvention(),
                CircularDefaultColorConvention()
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                circularData.forEachIndexed { index, circularRingData ->
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {

                        CircularRingChart(
                            circularCenter[index],
                            circularRingData.toCircularRingTargetData(),
                            circularDecoration[index],
                            circularForeground[index],
                            circularColorConvention[index]
                        ).Build(modifier = modifier[index].size(250.dp))
                    }
                }
            }
        }
    }
}