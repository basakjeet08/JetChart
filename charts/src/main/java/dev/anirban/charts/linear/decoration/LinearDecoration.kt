package dev.anirban.charts.linear.decoration

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * This is one of the implementations of the [LinearDecoration] which mostly contains the
 * decorations stuff like colors and all
 *
 * @param textColor this is the text color for all the margins and other things
 * @param plotPrimaryColor THis is the plot color for all the plotted Lines in the graph
 * @param plotSecondaryColor These are color for all the circle points in the graph
 */
class LinearDecoration(
    val textColor: Color,
    val plotPrimaryColor: List<Color>,
    val plotSecondaryColor: List<Color>
) {

    /**
     * These function are used to make an object of [LinearDecoration]
     */
    companion object {

        private val colorYellow = Color(0xFFE2B93B)
        private val colorBlue = Color(0xFF0088FF)
        private val colorRed = Color(0xFFEC407A)
        private val colorGreen = Color(0xFF2AD200)
        private val colorCyan = Color(0xFF00BCD4)

        /**
         * Provides [LinearDecoration] Object for the Line Charts
         *
         * Needs a Composable function to get the color from the material Theme since its
         * a composable function
         */
        @Composable
        fun lineDecorationColors(
            textColor: Color = MaterialTheme.colorScheme.onSurface,
            plotPrimaryColor: List<Color> = listOf(colorBlue, colorGreen, Color.Yellow),
            plotSecondaryColor: List<Color> = listOf(colorYellow, colorRed, Color.Red)
        ) = LinearDecoration(
            textColor = textColor,
            plotPrimaryColor = plotPrimaryColor,
            plotSecondaryColor = plotSecondaryColor
        )

        /**
         * Provides Decoration Object for Bar Charts
         *
         * Needs a Composable function to get the color from the material Theme since its
         * a composable function
         */
        @Composable
        fun barDecorationColors(
            textColor: Color = MaterialTheme.colorScheme.onSurface,
            plotPrimaryColor: List<Color> = listOf(colorBlue, colorCyan),
            plotSecondaryColor: List<Color> = emptyList()
        ) = LinearDecoration(
            textColor = textColor,
            plotPrimaryColor = plotPrimaryColor,
            plotSecondaryColor = plotSecondaryColor
        )
    }
}