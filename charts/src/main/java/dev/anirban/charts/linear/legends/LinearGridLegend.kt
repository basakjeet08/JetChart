package dev.anirban.charts.linear.legends

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import dev.anirban.charts.linear.interfaces.LinearLegendDrawer
import dev.anirban.charts.linear.interfaces.LinearDataInterface


/**
 * This class is the implementation of [LinearLegendDrawer] which provides the
 * functionality of drawing the Legends in the canvas. Here the legends are drawn in a
 * grid fashion with 2 Items in a Row.
 *
 *
 * @param fontSize This defines the size of the font
 * @param fontWeight This Defines the weight of the font
 */
class LinearGridLegend(
    private val fontSize: TextUnit = 14.sp,
    private val fontWeight: FontWeight = FontWeight.W500
) : LinearLegendDrawer {


    /**
     * This function draws the individual chart details or we can say the color codes along with
     * the text.
     *
     * @param text This is the text that would be shown before the value
     * @param color to be shown for this Legend
     * @param textColor This is the color of the text
     *
     */
    @Composable
    fun RowScope.LegendItem(
        text: String,
        color: Color,
        textColor: Color
    ) {

        Row(
            modifier = Modifier.weight(1f),
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
     * @param data This object contains the data of the graph
     * @param decoration This object contains the decorations of the graph
     */
    @Composable
    override fun DrawLegends(
        data: LinearDataInterface,
        decoration: LinearDecoration
    ) {

        // This contains the Color Conventions in the left side
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // Iterating through the whole data set to draw the required Legends
            for (index in data.linearDataSets.indices step 2) {

                // A Row Containing two legend at a Column
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    // This function draws the Left Column of the Legends
                    LegendItem(
                        text = data.linearDataSets[index].title,
                        color = decoration.plotPrimaryColor[index],
                        textColor = decoration.textColor
                    )

                    // This draws the right column of the legends
                    if (index + 1 < data.linearDataSets.size) {
                        LegendItem(
                            text = data.linearDataSets[index + 1].title,
                            color = decoration.plotPrimaryColor[index + 1],
                            textColor = decoration.textColor
                        )
                    }
                }
            }
        }
    }
}