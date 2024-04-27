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
 */
class ChartPadding {

    /**
     * This variable keeps the x Axis Elements Padding Values
     */
    val xPadding: AxisPadding


    /**
     * This variable keeps the y Axis Elements Padding Values
     */
    val yPadding: AxisPadding


    /**
     * Constructor to take the Axes Padding Values
     *
     * @param xPadding X Axis Elements Paddings should be given here
     * @param yPadding Y Axis Elements Paddings should be given here
     */
    constructor(
        xPadding: AxisPadding = AxisPadding(),
        yPadding: AxisPadding = AxisPadding()
    ) {

        this.xPadding = xPadding
        this.yPadding = yPadding
    }


    /**
     * Default Constructor to initialize the Axes Elements Padding if nothing is passed.
     */
    constructor() {

        this.xPadding = AxisPadding(
            titlePadding = Padding(vertical = 4.dp),
            labelPadding = Padding(top = 4.dp)
        )

        this.yPadding = AxisPadding(
            titlePadding = Padding(horizontal = 4.dp),
            labelPadding = Padding(end = 4.dp)
        )
    }
}