package dev.anirban.charts.circular.legend

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy
import java.text.DecimalFormat

/**
 * This class is the implementation of [CircularLegendStrategy] which provides a strategy to draw
 * the legends in a grid fashion
 *
 * For other implementations see [ListLegendStrategy], [NoLegendStrategy]
 *
 * @param style This is the style of the text to be drawn on the legends
 */
class GridLegendStrategy(
    private val style: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400
    )
) : CircularLegendStrategy {


    /**
     * This function draws the individual chart details or we can say the color codes along with
     * the text.
     *
     * @param text This is the text that would be shown before the value
     * @param value This is the value
     * @param color to be shown for this color convention
     * @param textColor This is the color of the text
     * @param unit This is the SI Unit of the value
     */
    @Composable
    fun ChartDetail(
        text: String,
        value: Float,
        color: Color,
        textColor: Color,
        unit: String
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

            // This is the output to be shown to the users
            val textToShow = "$text - ${DecimalFormat("#.##").format(value)} $unit"

            // Item Value
            Text(
                text = textToShow,

                // Text Features
                textAlign = TextAlign.Center,
                style = style.copy(color = textColor)
            )
        }
    }


    /**
     * This function draws the desired color Convention
     *
     * @param circularData This is the data of the chart
     * @param decoration This is the decoration of the chart
     */
    @Composable
    override fun DrawColorConventions(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) {

        // This contains the Color Conventions in the left side
        Column(
            modifier = Modifier
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {

            // Drawing Left column with the conventions
            for (index in circularData.itemsList.indices step 2) {

                // This function draws one of the color code Item details
                ChartDetail(
                    text = circularData.itemsList[index].first,
                    value = circularData.itemsList[index].second,
                    color = decoration.colorList[index],
                    textColor = decoration.textColor,
                    unit = circularData.unit
                )
            }
        }

        // This contains the Color Conventions in the right side
        Column(
            modifier = Modifier
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {

            // Drawing Right column with the conventions
            for (index in 1 until circularData.itemsList.size step 2) {

                // This function draws one of the color code Item details
                ChartDetail(
                    text = circularData.itemsList[index].first,
                    value = circularData.itemsList[index].second,
                    color = decoration.colorList[index],
                    textColor = decoration.textColor,
                    unit = circularData.unit
                )
            }
        }
    }
}