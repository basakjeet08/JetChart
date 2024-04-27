package dev.anirban.chartsx.linear.util.padding


import androidx.compose.ui.unit.dp


/**
 * This data class contains the Different Elements of the Axes for which the padding can be
 * provided. It also contains the different padding values for each element.
 *
 * @param titlePadding This keeps the [Padding] for the Title of the Axes
 * @param labelPadding This keeps the [Padding] for the Labels of the Axes
 */
data class AxisPadding(
    val titlePadding: Padding = Padding(),
    val labelPadding: Padding = Padding()
)


/**
 * This class keeps the Chart Axes Elements Padding Values.
 *
 * Note :- The class contains the paddings for X & Y Axis Elements (title and label)
 *
 * @param chartTitlePadding This contains the chart main title padding values ([Padding])
 * @param xPadding This variable keeps the x Axis Elements Padding Values ([AxisPadding])
 * @param yPadding This variable keeps the y Axis Elements Padding Values ([AxisPadding])
 */
data class ChartPadding(
    val chartTitlePadding: Padding = Padding(vertical = 4.dp),
    val xPadding: AxisPadding = AxisPadding(
        titlePadding = Padding(vertical = 4.dp),
        labelPadding = Padding(top = 4.dp)
    ),
    val yPadding: AxisPadding = AxisPadding(
        titlePadding = Padding(horizontal = 4.dp),
        labelPadding = Padding(end = 4.dp)
    )
)