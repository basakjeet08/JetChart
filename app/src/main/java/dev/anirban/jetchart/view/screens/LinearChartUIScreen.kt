package dev.anirban.jetchart.view.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.anirban.charts.linear.LinearChart
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.data.LinearStringData
import dev.anirban.charts.linear.plots.LinearGradientPlot
import dev.anirban.charts.util.Coordinate
import dev.anirban.jetchart.data.model.LinearMockResponse
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
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Reload Button
        Button(onClick = onReload) {
            Text(text = "Reload Data Set")
        }


        // Design Pattern Single Line Chart
        CustomCard(title = "Single Line Chart") {

            LinearChart.LineChart(
                linearData = LinearStringData(
                    linearDataSets = linearDataSet1,
                    xAxisLabels = xReadingMarker
                )
            )
        }

        // Design Pattern Double Line Chart
        CustomCard(title = "Double Line Chart") {

            LinearChart.LineChart(
                linearData = LinearStringData(
                    linearDataSets = linearDataSet2,
                    xAxisLabels = xReadingMarker
                )
            )
        }

        // Design Pattern Triple Line Chart
        CustomCard(title = "Multiple Line Chart") {

            LinearChart.LineChart(
                linearData = LinearStringData(
                    linearDataSets = linearDataSet3,
                    xAxisLabels = xReadingMarker
                )
            )
        }

        // Design Pattern String Marker Line Chart
        CustomCard(title = "String Marker Chart") {
            LinearChart.LineChart(
                linearData = LinearStringData(
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

        // Design Pattern String Marker Gradient Line Chart using plot object
        CustomCard(title = "Gradient List using Plot Object") {

            LinearChart.GradientChart(
                linearData = LinearStringData(
                    linearDataSets = linearDataSet4,
                    xAxisLabels = xReadingMarker,
                    yAxisLabels = Coordinate.coordinateSetBuilder(
                        "Very High",
                        "High",
                        "Moderate",
                        "Average",
                        "Bad"
                    ).toMutableList()
                ),
                plot = LinearGradientPlot(
                    colorList = listOf(
                        Color(0xFF8F49DF).copy(alpha = .5f),
                        Color(0xFF8F49DF).copy(alpha = .01f),
                    )
                )
            )
        }

        // Design Pattern Bar Chart
        CustomCard(title = "Bar Chart") {

            LinearChart.BarChart(
                linearData = LinearStringData(
                    linearDataSets = linearDataSet4,
                    xAxisLabels = xReadingMarker
                )
            )
        }
    }
}