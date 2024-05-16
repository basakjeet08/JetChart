package dev.anirban.jetchart.view.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.anirban.charts.linear.BasicLinearStrategy
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.data.BasicDataStrategy
import dev.anirban.charts.util.Coordinate
import dev.anirban.jetchart.data.model.LinearMockResponse
import dev.anirban.jetchart.view.components.CustomButton
import dev.anirban.jetchart.view.components.CustomCard


@Composable
fun LinearChartUIScreen(
    mockLinearData: LinearMockResponse,
    onReload: () -> Unit
) {

    // Example Data Sets
    val xReadingMarker by remember {
        mutableStateOf(Coordinate.coordinateSetBuilder("A", "B", "C", "D", "E", "F", "G"))
    }

    // Data Set Samples and various ways to create them
    val linearDataSet1 = mockLinearData.dataSet1.map {
        LinearDataSet.createDataSet(
            title = it.title,
            markers = it.dataSet
        )
    }
    val linearDataSet2 = mockLinearData.dataSet2.map {
        LinearDataSet.createDataSet(
            title = it.title,
            markers = it.dataSet
        )
    }
    val linearDataSet3 = mockLinearData.dataSet3.map {
        LinearDataSet.createDataSet(
            title = it.title,
            markers = it.dataSet
        )
    }
    val linearDataSet4 = mockLinearData.dataSet4.map {
        it.toLinearDataSet()
    }

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

            // Design Pattern Single Line Chart
            item {
                CustomCard(title = "Single Line Chart") {

                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet1,
                            xAxisLabels = xReadingMarker
                        )
                    )
                }
            }

            // Design Pattern Double Line Chart
            item {
                CustomCard(title = "Double Line Chart") {

                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet2,
                            xAxisLabels = xReadingMarker
                        )
                    )
                }
            }

            // Design Pattern Triple Line Chart
            item {
                CustomCard(title = "Multiple Line Chart") {

                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet3,
                            xAxisLabels = xReadingMarker
                        )
                    )
                }
            }

            // Design Pattern String Marker Line Chart
            item {
                CustomCard(title = "String Marker Chart") {
                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet4,
                            xAxisLabels = xReadingMarker,
                            yAxisLabels = Coordinate.coordinateSetBuilder(
                                "Very High",
                                "High",
                                "Moderate",
                                "Average",
                                "Bad"
                            ).toMutableList()
                        )
                    )
                }
            }

            // Design Pattern String Marker Gradient Line Chart using plot object
            item {
                CustomCard(title = "Gradient List using Plot Object") {

                    BasicLinearStrategy.GradientChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet4,
                            xAxisLabels = xReadingMarker,
                            yAxisLabels = Coordinate.coordinateSetBuilder(
                                "Very High",
                                "High",
                                "Moderate",
                                "Average",
                                "Bad"
                            ).toMutableList()
                        )
                    )
                }
            }


            // Design Pattern Bar Chart
            item {
                CustomCard(title = "Bar Chart") {

                    BasicLinearStrategy.BarChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet4,
                            xAxisLabels = xReadingMarker
                        )
                    )
                }
            }
        }

        // Reload Button
        CustomButton(
            modifier = Modifier,
            text = "Reload Data Set",
            onClick = onReload
        )
    }
}