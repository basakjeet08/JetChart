package dev.anirban.charts.circular.center

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * This class is the implementation of [CircularCenterStrategy] which focuses on providing an
 * implementation to draw something on the center of the Circular Chart
 *
 * This Class in particular is the implementation to draw texts
 *
 * @param fontSize This defines the size of the font
 * @param fontWeight This Defines the weight of the font
 */
class TextCenterStrategy(
    private val textToShow: String,
    private val fontSize: TextUnit = 12.sp,
    private val fontWeight: FontWeight = FontWeight.W500
) : CircularCenterStrategy {


    /**
     * This function Draws the % achieved in the Center of the Chart
     *
     * @param circularData This is the data object which contains all the data about the Chart
     * @param decoration This is the decoration which contains all the decorations of the Chart
     */
    @Composable
    override fun DrawCenter(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) {

        // Item and Value
        Text(
            text = textToShow,

            modifier = Modifier.padding(vertical = 4.dp),

            // Text Features
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = decoration.textColor
        )
    }
}