package dev.anirban.jetchart.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.CircularChart
import dev.anirban.charts.circular.center.CircularImageCenter
import dev.anirban.charts.circular.center.CircularRingTextCenter
import dev.anirban.charts.circular.center.CircularTargetTextCenter
import dev.anirban.charts.circular.charts.CircularDonutChartColumn
import dev.anirban.charts.circular.charts.CircularDonutChartRow
import dev.anirban.charts.circular.charts.CircularRingChart
import dev.anirban.charts.circular.data.CircularDonutListData
import dev.anirban.charts.circular.data.CircularTargetDataBuilder
import dev.anirban.charts.circular.foreground.CircularDonutForeground
import dev.anirban.charts.circular.foreground.CircularDonutTargetForeground
import dev.anirban.jetchart.components.LinearChartUI
import dev.anirban.jetchart.ui.theme.JetChartTheme


// Preview Composable Function
@Preview(
    "Light",
    heightDp = 2000,
    showBackground = true
)
@Preview(
    name = "Dark",
    heightDp = 2000,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun DefaultPreview() {
    JetChartTheme {
        LibraryUIExample()
    }
}

@Composable
fun LibraryUIExample() {

    // Main UI
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LinearChartUI {
            TODO("Not yet implemented")
        }

        // Design Pattern Same row Donut Chart
        CustomCard(title = " Row Donut Chart") {

            CircularDonutChartRow.DonutChartRow(
                circularData = CircularDonutListData(
                    itemsList = listOf(
                        Pair("Fruit", 1500.0f),
                        Pair("Junk Food", 300.0f),
                        Pair("Protein", 500.0f)
                    ),
                    siUnit = "Kcal",
                    cgsUnit = "cal",
                    conversionRate = { it / 1000f }
                ),
                circularForeground = CircularDonutForeground(
                    radiusMultiplier = 1.7f,
                    strokeWidth = 15f,
                    startAngle = 30f
                )
            )
        }

        // Design Pattern Different row Donut Chart
        CustomCard(title = "Column Donut Chart") {

            CircularDonutChartColumn.DonutChartColumn(
                circularData = CircularDonutListData(
                    itemsList = listOf(
                        Pair("Study", 450f),
                        Pair("Sport", 180f),
                        Pair("Social", 30f),
                        Pair("Others", 60f)
                    ),
                    siUnit = "Hrs",
                    cgsUnit = "Min",
                    conversionRate = { it / 60f }
                )
            )
        }

        // Design Pattern Target Achieved Donut Chart
        CustomCard(title = "Target Donut Chart") {

            CircularDonutChartRow.TargetDonutChart(
                circularData = CircularTargetDataBuilder(
                    target = 4340f,
                    achieved = 2823f,
                    siUnit = "Km",
                    cgsUnit = "m",
                    conversionRate = { it / 1000f }
                ),
                circularCenter = CircularTargetTextCenter(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            )
        }

        // Design Pattern Single Ring Chart
        CustomCard(title = "Single Ring Chart") {
            CircularRingChart.SingleRingChart(
                circularData = CircularTargetDataBuilder(
                    target = 500f,
                    achieved = 489f,
                    siUnit = "",
                    cgsUnit = "",
                    conversionRate = { it }
                ),
                circularCenter = CircularRingTextCenter(
                    title = "Title 1",
                    centerValue = "value",
                    status = "status Value"
                )
            )
        }


        // Design Pattern Double Ring Chart
        CustomCard(title = "Double Ring Chart") {
            CircularRingChart.MultipleRingChart(
                circularData = listOf(
                    CircularTargetDataBuilder(
                        target = 100f,
                        achieved = 81f,
                        siUnit = "bpm",
                        cgsUnit = "bpm",
                        conversionRate = { it }
                    ),
                    CircularTargetDataBuilder(
                        target = 160f,
                        achieved = 112f,
                        siUnit = "mm",
                        cgsUnit = "mm",
                        conversionRate = { it }
                    )
                ),
                circularCenter = listOf(
                    CircularRingTextCenter(
                        title = "Title 1",
                        centerValue = "center value",
                        status = "Not Good"
                    ),
                    CircularRingTextCenter(
                        title = "Title 2",
                        centerValue = "center value",
                        status = "Great"
                    )
                )
            )
        }

        CustomCard(title = "Weekly Progress") {
            Row {

                listOf("M", "T", "W", "T", "F", "S", "S").forEach {

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CircularChart.DonutChartImage(
                            modifier = Modifier
                                .size(55.dp),
                            circularData = CircularTargetDataBuilder(
                                target = 100f,
                                achieved = 81f,
                                siUnit = "",
                                cgsUnit = "",
                                conversionRate = { it }
                            ),
                            circularCenter = CircularImageCenter(
                                image = Icons.Default.Check,
                                contentDescription = "Achieved"
                            ),
                            circularForeground = CircularDonutTargetForeground(strokeWidth = 10f)
                        )

                        Text(
                            text = it,

                            // Text Features
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W700,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}