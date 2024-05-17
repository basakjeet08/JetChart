package dev.anirban.jetchart.view.screens

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlaceholderVerticalAlign.Companion.TextCenter
import androidx.compose.ui.unit.dp
import dev.anirban.charts.circular.BasicCircularStrategy.Companion.WeeklyProgressChart
import dev.anirban.charts.circular.center.TextCenterStrategy
import dev.anirban.charts.circular.charts.DonutColumnChartStrategy
import dev.anirban.charts.circular.charts.DonutRowChartStrategy
import dev.anirban.charts.circular.data.ListDataStrategy
import dev.anirban.charts.circular.data.TargetDataStrategy
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


                    with(circularMockData.dataSet3) {
                        val percentage = DecimalFormat("#.##").format(achieved / target * 100)
                        DonutRowChartStrategy.TargetDonutChartRow(
                            circularData = dataSet3,
                            circularCenter = TextCenterStrategy(
                                text = "$percentage %"
                            )
                        )
                    }
                }
            }


            // weekly Progress Graph
            item {
                CustomCard(title = "Weekly Progress") {

                    WeeklyProgressChart(weeklyData = dataSet4)
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