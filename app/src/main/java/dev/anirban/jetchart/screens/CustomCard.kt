package dev.anirban.jetchart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * This function is the Card view Template used
 *
 * @param modifier To be passed by the Parent Class
 * @param title This is the title for the card
 * @param heightBetweenTitleAndBody This is the extra height between the body and the title
 * @param body The UI which will be drawn inside this card
 */
@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    heightBetweenTitleAndBody: Dp = 0.dp,
    body: @Composable () -> Unit
) {

    // This function draws an elevated Card View
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.Transparent
        )
    ) {

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                if (!title.isNullOrEmpty()) {

                    Text(
                        text = title,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),

                        // Text Features
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    // Giving a space between the title and the body of the card
                    Spacer(modifier = Modifier.height(heightBetweenTitleAndBody))
                }

                // Graph Body Function
                body()
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}