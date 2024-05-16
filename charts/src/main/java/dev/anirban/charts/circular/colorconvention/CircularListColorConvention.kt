package dev.anirban.charts.circular.colorconvention

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
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataInterface
import java.text.DecimalFormat

/**
 * This class is the implementation of [CircularColorConventionInterface] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 * @param fontSize This defines the size of the font
 * @param fontWeight This Defines the weight of the font
 */
class CircularListColorConvention(
    private val fontSize: TextUnit = 14.sp,
    private val fontWeight: FontWeight = FontWeight.W500
) : CircularColorConventionInterface {

    /**
     * This function draws the color conventions in the canvas
     *
     * @param circularData This object contains the data of the graph
     * @param decoration THis object contains the decorations of the graph
     */
    @Composable
    override fun DrawColorConventions(
        circularData: CircularDataInterface,
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
                    siUnit = circularData.siUnit,
                    cgsUnit = circularData.cgsUnit,
                    conversionRate = circularData.conversionRate
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
     * @param siUnit This is the SI Unit to be shown for SI Value
     * @param cgsUnit This is the CGS unit to be shown for CGS Value
     * @param conversionRate THis is the conversion rate according to which the values can
     * be changed from CGS to SI unit
     */
    @Composable
    private fun DrawConvention(
        pair: Pair<String, Float>,
        colorConvention: Color,
        textColor: Color,
        siUnit: String,
        cgsUnit: String,
        conversionRate: (Float) -> Float
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
                drawCircle(
                    colorConvention,
                    radius = 20f,
                    center = size.center
                )
            }

            // This is the value in SI Unit
            val convertedValue = conversionRate(pair.second)

            // Determining the text to be shown. Would it be in SI or CGS
            val textToBeShown = "${pair.first} - " + if (convertedValue < 1f)
                "${DecimalFormat("#.##").format(pair.second)} $cgsUnit"
            else
                "${DecimalFormat("#.##").format(convertedValue)} $siUnit"

            // Item Value
            Text(
                text = textToBeShown,

                // Text Features
                textAlign = TextAlign.Center,
                fontSize = fontSize,
                fontWeight = fontWeight,
                color = textColor
            )
        }
    }
}