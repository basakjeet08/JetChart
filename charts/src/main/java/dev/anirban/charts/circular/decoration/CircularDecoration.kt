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

        private val colorBlue = Color(0xFF0088FF)
        private val colorGreen = Color(0xFF2AD200)
        private val colorYellow = Color(0xFFEEE73B)
        private val colorRed = Color(0xFFFF2E2E)

        private val ringChartColors = listOf(
            Color(0xFFD74719).copy(alpha = .66f),
            Color(0xFFE8661D).copy(alpha = .66f),
            Color(0xFFD8DB31).copy(alpha = .66f),
            Color(0xFF3CC641).copy(alpha = .66f),
            Color(0xFF83D5E0).copy(alpha = .66f),
            Color(0xFFAB51CA).copy(alpha = .66f),
            Color(0xFFEA0EC6).copy(alpha = .66f)
        )

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
                colorBlue,
                colorGreen,
                colorYellow,
                colorRed
            )
        ) = CircularDecoration(
            textColor = textColor,
            colorList = colorList
        )

        /**
         * Provides [CircularDecoration] Objects for the circular Ring Charts
         *
         * Needs a Composable function to get the color from the material Theme since its
         * a composable function
         *
         * @param textColor Color of all the texts in the chart
         * @param colorList Primary Color list or the color list of the canvas arc in order
         */
        @Composable
        fun ringChartDecoration(
            textColor: Color = MaterialTheme.colorScheme.onSurface,
            colorList: List<Color> = ringChartColors
        ) = CircularDecoration(
            textColor = textColor,
            colorList = colorList
        )
    }
}