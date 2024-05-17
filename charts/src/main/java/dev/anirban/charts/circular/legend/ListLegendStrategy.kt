package dev.anirban.charts.circular.legend

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy
import java.text.DecimalFormat

/**
 * This class is the implementation of [CircularLegendStrategy] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 * @param fontSize This defines the size of the font
 * @param fontWeight This Defines the weight of the font
 */
class ListLegendStrategy(
    private val fontSize: TextUnit = 14.sp,
    private val fontWeight: FontWeight = FontWeight.W400
) : CircularLegendStrategy {

    /**
     * This function draws the color conventions in the canvas
     *
     * @param circularData This object contains the data of the graph
     * @param decoration THis object contains the decorations of the graph
     */
    @Composable
    override fun DrawColorConventions(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) {

        // This contains the Color Conventions
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Drawing the Color Code and writing the Text of the UI
            circularData.itemsList.forEachIndexed { index, pair ->
                DrawConvention(
                    pair = pair,
                    colorConvention = decoration.colorList[index],
                    textColor = decoration.textColor,
                    unit = circularData.unit
                )
            }
        }
    }

    /**
     * This function draws individual Color Convention
     *
     * @param pair This contains the data of the String and the value to be shown to the user
     * @param colorConvention This is the color which denotes this value in the graph
     * @param textColor This is the Color of the text
     * @param unit This is the SI Unit to be shown for SI Value
     */
    @Composable
    private fun DrawConvention(
        pair: Pair<String, Float>,
        colorConvention: Color,
        textColor: Color,
        unit: String
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Drawing the small circles(color codes)
            Canvas(
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
            ) {

                // This function draws the Color codes circles
                drawRoundRect(
                    color = colorConvention,
                    cornerRadius = CornerRadius(12f)
                )
            }

            // Determining the text to be shown. Would it be in SI or CGS
            val textToShow = "${pair.first} - " +
                    "${DecimalFormat("#.##").format(pair.second)} $unit"

            // Item Value
            Text(
                text = textToShow,

                // Text Features
                fontSize = fontSize,
                fontWeight = fontWeight,
                color = textColor
            )
        }
    }
}