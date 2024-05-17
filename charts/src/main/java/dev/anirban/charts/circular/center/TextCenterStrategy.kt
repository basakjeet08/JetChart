package dev.anirban.charts.circular.center

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * This class is the implementation of [CircularCenterStrategy] which focuses on providing a
 * strategy for drawing text in the center of a circular chart.
 *
 * This Class in particular is the implementation to draw texts
 * For other implementations see [CircularCenterStrategy], [ImageCenterStrategy], [NoCenterStrategy]
 *
 * @param text The text to display.
 * @param textStyle The style of the text.
 */
class TextCenterStrategy(
    private val text: String,
    private val textStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W500
    )
) : CircularCenterStrategy {


    /**
     * This function draws composable function in the center of the chart
     *
     * @param circularData Data related to the circular chart.
     * @param decoration Decorations for the chart.
     */
    @Composable
    override fun DrawCenter(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) {

        // Item and Value
        Text(
            text = text,

            modifier = Modifier.padding(vertical = 4.dp),

            // Text Features
            style = textStyle.copy(
                color = decoration.textColor
            )
        )
    }
}