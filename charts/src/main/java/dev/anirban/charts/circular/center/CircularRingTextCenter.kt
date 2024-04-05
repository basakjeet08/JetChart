package dev.anirban.charts.circular.center

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.interfaces.CircularCenterInterface
import dev.anirban.charts.circular.interfaces.CircularDataInterface

/**
 * This class is the implementation of [CircularCenterInterface] which focuses on providing an
 * implementation to draw 3 texts in the center of the Ring Chart
 *
 * This Class in particular is the implementation to draw texts
 *
 * @param title This is the first Text and the title of the Text shown in the Center
 * @param centerValue This is the text which contains the value and shown in the middle
 * @param status This contains the status message fpr the user
 */
class CircularRingTextCenter(
    private val title: String,
    private val centerValue: String,
    private val status: String
) : CircularCenterInterface {


    /**
     * This function draws 3 Text Composable functions as an implementation for the
     * Center of the Chart
     *
     * @param circularData This object contains the data of the graph
     * @param decoration THis object contains the decorations of the graph
     */
    @Composable
    override fun DrawCenter(
        circularData: CircularDataInterface,
        decoration: CircularDecoration
    ) {

        // Holds all the texts composable
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title
            Text(
                text = title,

                modifier = Modifier
                    .padding(vertical = 2.dp),

                // Text Features
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = decoration.textColor
            )

            // Item and Value
            Text(
                text = centerValue,

                modifier = Modifier
                    .padding(vertical = 2.dp),

                // Text Features
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.W800,
                color = decoration.textColor
            )

            // Item and Value
            Text(
                text = status,

                modifier = Modifier
                    .padding(vertical = 2.dp),

                // Text Features
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = decoration.textColor
            )
        }
    }
}