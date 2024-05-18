package dev.anirban.jetchart.view.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.anirban.charts.linear.BasicLinearStrategy
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.data.BasicDataStrategy
import dev.anirban.charts.util.Coordinate.Companion.toCoordinateSet
import dev.anirban.jetchart.data.model.linear.LinearMockResponse
import dev.anirban.jetchart.view.components.CustomButton
import dev.anirban.jetchart.view.components.CustomCard


@Composable
fun LinearChartUIScreen(
    mockLinearData: LinearMockResponse,
    onReload: () -> Unit
) {

    // Example Data Sets
    val xReadingMarker = mockLinearData.xAxisLabels.toCoordinateSet()
    val yReadingMarker = mockLinearData.yAxisLabels.toCoordinateSet()

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

    val linearDataSet5 = mockLinearData.dataSet5.map {
        it.toLinearDataSet()
    }

    // Data Set Samples and various ways to create them
    val linearDataSet6 = mockLinearData.dataSet6.map {
        it.toLinearDataSet()
    }

    val linearDataSet7 = mockLinearData.dataSet7.map {
        it.toLinearDataSet()
    }
    val linearDataSet8 = mockLinearData.dataSet8.map {
        it.toLinearDataSet()
    }
    val linearDataSet9 = mockLinearData.dataSet9.map {
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
                CustomCard(title = "Normal Line Plot with 1 Dataset") {

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
                CustomCard(title = "Normal Line Plot with 2 Dataset") {

                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet2,
                            xAxisLabels = xReadingMarker
                        )
                    )
                }
            }

            // Design Pattern Multiple Line Chart
            item {
                CustomCard(title = "Normal Line Plot with 3 Dataset") {

                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet3,
                            xAxisLabels = xReadingMarker
                        )
                    )
                }
            }


            // Design Pattern Multiple Line Chart
            item {
                CustomCard(title = "Normal Line Plot with 4 Dataset") {

                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet4,
                            xAxisLabels = xReadingMarker
                        )
                    )
                }
            }


            // Design Pattern String Marker Gradient Line Chart
            item {
                CustomCard(title = "Gradient Plot") {

                    BasicLinearStrategy.GradientChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet5,
                            xAxisLabels = xReadingMarker,
                            yAxisLabels = yReadingMarker.toMutableList()
                        )
                    )
                }
            }


            // Design Pattern String Marker Line Chart
            item {
                CustomCard(title = "String Label Line Plot with 1 Dataset") {
                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet6,
                            xAxisLabels = xReadingMarker,
                            yAxisLabels = yReadingMarker.toMutableList()
                        )
                    )
                }
            }

            // Design Pattern String Marker Line Chart
            item {
                CustomCard(title = "String Label Line Plot with 2 Dataset") {
                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet7,
                            xAxisLabels = xReadingMarker,
                            yAxisLabels = yReadingMarker.toMutableList()
                        )
                    )
                }
            }

            // Design Pattern String Marker Line Chart
            item {
                CustomCard(title = "String Label Line Plot with 3 Dataset") {
                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet8,
                            xAxisLabels = xReadingMarker,
                            yAxisLabels = yReadingMarker.toMutableList()
                        )
                    )
                }
            }

            // Design Pattern String Marker Line Chart
            item {
                CustomCard(title = "String Label Line Plot with 4 Dataset") {
                    BasicLinearStrategy.LineChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet9,
                            xAxisLabels = xReadingMarker,
                            yAxisLabels = yReadingMarker.toMutableList()
                        )
                    )
                }
            }


            // Design Pattern Bar Chart
            item {
                CustomCard(title = "Bar Plot") {

                    BasicLinearStrategy.BarChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet1,
                            xAxisLabels = xReadingMarker
                        )
                    )
                }
            }

            // Design Pattern Bar Chart
            item {
                CustomCard(title = "String Bar Plot") {

                    BasicLinearStrategy.BarChart(
                        linearData = BasicDataStrategy(
                            linearDataSets = linearDataSet6,
                            xAxisLabels = xReadingMarker,
                            yAxisLabels = yReadingMarker.toMutableList()
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