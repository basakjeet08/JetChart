package dev.anirban.charts.linear.labels

import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.data.LinearDataStrategy


/**
 * This is one of the implementations of the [LinearLabelStrategy] and it provides with
 * a implementation of how we should draw the labels on the graph
 */
class StringLabelStrategy : LinearLabelStrategy {


    /**
     * This function draws the labels in the graph according to the implementation passed
     *
     * @param linearData The data of the graph [LinearDataStrategy] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    override fun DrawScope.drawLabels(
        linearData: LinearDataStrategy,
        decoration: LinearDecoration
    ) {

        // Padding from the Y Axis Labels to the Dashed Line
        val yLabelPadding = 24f

        val paint = Paint().apply {
            color = decoration.textColor.copy(alpha = 0.7f).toArgb()
            textSize = 12.sp.toPx()
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        }

        linearData.yAxisLabels.forEach { label ->

            val bounds = Rect()
            paint.getTextBounds(label.value.toString(), 0, label.value.toString().length, bounds)

            val width = bounds.width()

            // This draws the String Labels on the Graph
            drawContext.canvas.nativeCanvas.drawText(
                label.value.toString(),
                label.x,
                label.y,
                paint.apply { textAlign = Paint.Align.LEFT }
            )

            // This draws the Lines for the readings parallel to X Axis
            drawLine(
                start = Offset(
                    x = width.toFloat() + yLabelPadding,
                    y = label.y - bounds.height() / 2f
                ),
                color = decoration.textColor.copy(alpha = 0.8f),
                end = Offset(
                    x = size.width,
                    y = label.y - bounds.height() / 2f
                ),
                strokeWidth = .7f,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(10f, 10f),
                    phase = 0f
                )
            )
        }

        // This Draws the X Labels below the Graph
        linearData.xAxisLabels.forEach { label ->

            // This draws the String Marker
            drawContext.canvas.nativeCanvas.drawText(
                label.value.toString(),
                label.x,
                label.y,
                paint.apply { textAlign = Paint.Align.CENTER }
            )
        }
    }
}