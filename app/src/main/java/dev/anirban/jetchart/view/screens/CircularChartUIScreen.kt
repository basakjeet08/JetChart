package dev.anirban.jetchart.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.BasicCircularStrategy
import dev.anirban.charts.circular.center.TextCenterStrategy
import dev.anirban.charts.circular.charts.DonutColumnChartStrategy
import dev.anirban.charts.circular.charts.DonutRowChartStrategy
import dev.anirban.charts.circular.data.ListDataStrategy
import dev.anirban.charts.circular.data.TargetDataStrategy
import dev.anirban.charts.circular.foreground.DonutTargetForegroundStrategy
import dev.anirban.jetchart.view.components.CustomButton
import dev.anirban.jetchart.view.components.CustomCard

@Composable
fun CircularChartUIScreen() {

    // Column Composable
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            // Design Pattern Same row Donut Chart
            item {
                CustomCard(title = " Row Donut Chart") {

                    DonutRowChartStrategy.DonutChartRow(
                        circularData = ListDataStrategy(
                            itemsList = listOf(
                                Pair("Fruit", 1500.0f),
                                Pair("Junk Food", 300.0f),
                                Pair("Protein", 500.0f)
                            ),
                            unit = "Kcal"
                        )
                    )
                }
            }


            // Design Pattern Different row Donut Chart
            item {
                CustomCard(title = "Column Donut Chart") {

                    DonutColumnChartStrategy.DonutChartColumn(
                        circularData = ListDataStrategy(
                            itemsList = listOf(
                                Pair("Study", 450f),
                                Pair("Sport", 180f),
                                Pair("Social", 30f),
                                Pair("Others", 60f)
                            ),
                            unit = "Min"
                        )
                    )
                }
            }


            // Design Pattern Target Achieved Donut Chart
            item {
                CustomCard(title = "Target Donut Chart") {

                    DonutRowChartStrategy.TargetDonutChart(
                        circularData = TargetDataStrategy(
                            target = 4340f,
                            achieved = 2823f,
                            unit = "m"
                        ),
                        circularCenter = TextCenterStrategy(
                            text = ((2823f / 4340f) * 100f).toString()
                        )
                    )
                }
            }


            // weekly Progress Graph
            item {
                CustomCard(title = "Weekly Progress") {
                    Row {

                        listOf("M", "T", "W", "T", "F", "S", "S").forEach {

                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                BasicCircularStrategy.DonutChartImage(
                                    modifier = Modifier.size(55.dp),
                                    circularData = TargetDataStrategy(
                                        target = 100f,
                                        achieved = 81f,
                                        unit = ""
                                    ),
                                    circularForeground = DonutTargetForegroundStrategy(strokeWidth = 10f)
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

        // Reload Button
        CustomButton(
            modifier = Modifier,
            text = "Reload Data Set",
            onClick = {}
        )
    }
}