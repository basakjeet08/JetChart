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
import dev.anirban.charts.circular.interfaces.CircularCenterInterface
import dev.anirban.charts.circular.interfaces.CircularDataInterface
import java.text.DecimalFormat

/**
 * This class is the implementation of [CircularCenterInterface] which focuses on providing an
 * implementation to draw something on the center of the Circular Chart
 *
 * This Class in particular is the implementation to draw texts
 *
 * @param fontSize This defines the size of the font
 * @param fontWeight This Defines the weight of the font
 */
class CircularTargetTextCenter(
    private val fontSize: TextUnit = 12.sp,
    private val fontWeight: FontWeight = FontWeight.W500
) : CircularCenterInterface {

    /**
     * This function Draws the % achieved in the Center of the Chart
     *
     * @param circularData This is the data object which contains all the data about the Chart
     * @param decoration This is the decoration which contains all the decorations of the Chart
     */
    @Composable
    override fun DrawCenter(
        circularData: CircularDataInterface,
        decoration: CircularDecoration
    ) {

        // Percentage to be shown
        var percentage = circularData.itemsList[1].second / circularData.itemsList[0].second * 100

        if (percentage.isNaN())
            percentage = 0f

        if (percentage > 100f)
            percentage = 100f

        // Item and Value
        Text(
            text = "${DecimalFormat("#.##").format(percentage)} %",

            modifier = Modifier
                .padding(vertical = 4.dp),

            // Text Features
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = decoration.textColor
        )
    }
}