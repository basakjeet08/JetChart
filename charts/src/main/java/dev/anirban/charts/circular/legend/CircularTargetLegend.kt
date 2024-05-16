package dev.anirban.charts.circular.legend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataInterface
import java.text.DecimalFormat

/**
 * This class is the implementation of [CircularLegendInterface] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 * @param fontSize This defines the size of the font
 * @param fontWeight This Defines the weight of the font
 */
class CircularTargetLegend(
    private val fontSize: TextUnit = 14.sp,
    private val fontWeight: FontWeight = FontWeight.W400
) : CircularLegendInterface {

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
            horizontalAlignment = Alignment.Start
        ) {

            circularData.itemsList.forEach {

                // This is the converted Value which is to be shown for SI Unit
                val convertedValue = circularData.conversionRate(it.second)

                // This is the output to be shown to the user
                val textToShow = "${it.first} - " + if (convertedValue < 1f)
                    "${DecimalFormat("#.##").format(it.second)} ${circularData.cgsUnit}"
                else
                    "${DecimalFormat("#.##").format(convertedValue)} ${circularData.siUnit}"

                // Item and Value
                Text(
                    text = textToShow,

                    modifier = Modifier
                        .padding(vertical = 4.dp),

                    // Text Features
                    textAlign = TextAlign.Start,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    color = decoration.textColor
                )
            }
        }
    }
}