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
 */
class LinearDecoration(
    val textColor: Color,
    val plotPrimaryColor: List<Color>
) {


    /**
     * These function are used to make an object of [LinearDecoration]
     */
    companion object {

        private val colorBlue = Color(0xFF0088FF)
        private val colorGreen = Color(0xFF2AD200)
        private val colorRed = Color(0xFFEC407A)
        private val colorPurple = Color(0xFF8312D3)
        private val colorYellow = Color(0xFFE2B93B)


        /**
         * Provides [LinearDecoration] Object for the Line Charts
         *
         * Needs a Composable function to get the color from the material Theme since its
         * a composable function
         *
         * @param textColor this is the text color for all the margins and other things
         * @param plotPrimaryColor THis is the plot color for all the plotted Lines in the graph
         */
        @Composable
        fun lineDecorationColors(
            textColor: Color = MaterialTheme.colorScheme.onSurface,
            plotPrimaryColor: List<Color> = listOf(
                colorBlue,
                colorGreen,
                colorRed,
                colorPurple,
                colorYellow
            )
        ) = LinearDecoration(
            textColor = textColor,
            plotPrimaryColor = plotPrimaryColor
        )

        /**
         * Provides Decoration Object for Bar Charts
         *
         * Needs a Composable function to get the color from the material Theme since its
         * a composable function
         *
         * @param textColor this is the text color for all the margins and other things
         * @param plotPrimaryColor THis is the plot color for all the plotted Lines in the graph
         */
        @Composable
        fun barDecorationColors(
            textColor: Color = MaterialTheme.colorScheme.onSurface,
            plotPrimaryColor: List<Color> = listOf(
                colorBlue,
                colorGreen,
                colorRed,
                colorPurple,
                colorYellow
            )
        ) = LinearDecoration(
            textColor = textColor,
            plotPrimaryColor = plotPrimaryColor
        )
    }
}