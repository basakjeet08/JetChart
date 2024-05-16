package dev.anirban.charts.circular.decoration

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


/**
 * This is the class which contains the circular decoration data
 *
 * @param textColor Color of all the texts in the chart
 * @param colorList Primary Color list or the color list of the canvas arc in order
 */
class CircularDecoration(
    val textColor: Color,
    val colorList: List<Color>
) {

    /**
     * These function are used to make an object of [CircularDecoration]
     */
    companion object {

        private val colorPurple = Color(0xFF7979FE)
        private val colorRed = Color(0xFFFC3B75)
        private val colorBlue = Color(0xFF3AAAFE)
        private val colorGreen = Color(0xFF2AD200)
        private val colorYellow = Color(0xFFE2B93B)

        /**
         * Provides [CircularDecoration] Objects for the circular Donut Charts
         *
         * Needs a Composable function to get the color from the material Theme since its
         * a composable function
         *
         * @param textColor Color of all the texts in the chart
         * @param colorList Primary Color list or the color list of the canvas arc in order
         */
        @Composable
        fun donutChartDecorations(
            textColor: Color = MaterialTheme.colorScheme.onSurface,
            colorList: List<Color> = listOf(
                colorPurple,
                colorRed,
                colorBlue,
                colorGreen,
                colorYellow
            )
        ) = CircularDecoration(
            textColor = textColor,
            colorList = colorList
        )
    }
}