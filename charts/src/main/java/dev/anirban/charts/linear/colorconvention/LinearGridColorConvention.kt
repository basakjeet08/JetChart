package dev.anirban.charts.linear.colorconvention

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearColorConventionInterface

/**
 * This class is the implementation of [LinearColorConventionInterface] which provides the
 * implementations for drawing the color conventions in the canvas
 *
 * @param textList This contains the list of strings which needs to be drawn in the Chart
 * @param fontSize This defines the size of the font
 * @param fontWeight This Defines the weight of the font
 */
class LinearGridColorConvention(
    override val textList: List<String>,
    private val fontSize: TextUnit = 14.sp,
    private val fontWeight: FontWeight = FontWeight.W500
) : LinearColorConventionInterface {

    /**
     * This function draws the individual chart details or we can say the color codes along with
     * the text.
     *
     * @param text This is the text that would be shown before the value
     * @param color to be shown for this color convention
     * @param textColor This is the color of the text
     *
     */
    @Composable
    fun ChartDetail(
        text: String,
        color: Color,
        textColor: Color
    ) {

        Row(
            modifier = Modifier
                .padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Drawing the small circles(color codes)
            Canvas(
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
            ) {

                drawRoundRect(
                    color = color,
                    cornerRadius = CornerRadius(12f)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            // Item Value
            Text(
                text = text,

                // Text Features
                textAlign = TextAlign.Center,
                fontSize = fontSize,
                fontWeight = fontWeight,
                color = textColor
            )
        }
    }


    /**
     * This function draws the color conventions in the canvas
     *
     * @param decoration THis object contains the decorations of the graph
     */
    @Composable
    override fun DrawColorConventions(
        decoration: LinearDecoration
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {

            // This contains the Color Conventions in the left side
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {

                // Drawing Left column with the conventions
                for (index in textList.indices step 2) {

                    // This function draws one of the color code Item details
                    ChartDetail(
                        text = textList[index],
                        color = decoration.plotPrimaryColor[index],
                        textColor = decoration.textColor
                    )
                }
            }

            // This contains the Color Conventions in the right side
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {

                // Drawing Right column with the conventions
                for (index in 1 until textList.size step 2) {

                    // This function draws one of the color code Item details
                    ChartDetail(
                        text = textList[index],
                        color = decoration.plotPrimaryColor[index],
                        textColor = decoration.textColor
                    )
                }
            }
        }
    }
}