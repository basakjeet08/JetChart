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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import dev.anirban.charts.circular.center.ImageCenterStrategy
import dev.anirban.charts.circular.charts.DonutColumnChartStrategy
import dev.anirban.charts.circular.charts.DonutRowChartStrategy
import dev.anirban.charts.circular.data.ListDataStrategy
import dev.anirban.charts.circular.data.TargetDataStrategy
import dev.anirban.charts.circular.foreground.DonutTargetForegroundStrategy
import dev.anirban.jetchart.data.model.circular.CircularMockResponse
import dev.anirban.jetchart.view.components.CustomButton
import dev.anirban.jetchart.view.components.CustomCard


@Composable
fun CircularChartUIScreen(
    circularMockData: CircularMockResponse,
    onReloadClick: () -> Unit
) {

    val dataSet1 = ListDataStrategy(
        itemsList = circularMockData.dataSet1.itemList,
        unit = circularMockData.dataSet1.unit
    )

    val dataSet2 = ListDataStrategy(
        itemsList = circularMockData.dataSet2.itemList,
        unit = circularMockData.dataSet2.unit
    )

    val dataSet3 = TargetDataStrategy(
        achieved = circularMockData.dataSet3.achieved,
        target = circularMockData.dataSet3.target,
        unit = circularMockData.dataSet3.unit
    )

    val dataSet4 = circularMockData.dataSet4.map {
        TargetDataStrategy(
            achieved = it.achieved,
            target = it.target,
            unit = it.unit
        )
    }

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
                    DonutRowChartStrategy.DonutChartRow(circularData = dataSet1)
                }
            }


            // Design Pattern Different row Donut Chart
            item {
                CustomCard(title = "Column Donut Chart") {

                    DonutColumnChartStrategy.DonutChartColumn(circularData = dataSet2)
                }
            }


            // Design Pattern Target Achieved Donut Chart
            item {
                CustomCard(title = "Target Donut Chart") {

                    DonutRowChartStrategy.TargetDonutChartRow(circularData = dataSet3)
                }
            }


            // weekly Progress Graph
            item {
                CustomCard(title = "Weekly Progress") {
                    Row {

                        listOf("M", "T", "W", "T", "F", "S", "S").forEachIndexed { index, week ->

                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                if (circularMockData.dataSet4[index].achieved > circularMockData.dataSet4[index].target)
                                    BasicCircularStrategy.BasicDonutTargetChart(
                                        modifier = Modifier.size(55.dp),
                                        circularData = dataSet4[index],
                                        circularForeground = DonutTargetForegroundStrategy(
                                            strokeWidth = 10f
                                        ),
                                        circularCenter = ImageCenterStrategy(image = Icons.Filled.Check)
                                    )
                                else
                                    BasicCircularStrategy.BasicDonutTargetChart(
                                        modifier = Modifier.size(55.dp),
                                        circularData = dataSet4[index],
                                        circularForeground = DonutTargetForegroundStrategy(
                                            strokeWidth = 10f
                                        )
                                    )


                                Text(
                                    text = week,

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
            onClick = onReloadClick
        )
    }
}