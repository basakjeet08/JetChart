package dev.anirban.charts.linear.labels

import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.linear.interfaces.LinearLabelDrawerInterface

/**
 * This is one of the implementations of the [LinearLabelDrawerInterface] and it provides with
 * a implementation of how we should draw the labels on the graph
 */
class LinearStringLabelDrawer : LinearLabelDrawerInterface {


    /**
     * This function draws the labels in the graph according to the implementation passed
     *
     * @param linearData The data of the graph [LinearDataInterface] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    override fun DrawScope.drawLabels(
        linearData: LinearDataInterface,
        decoration: LinearDecoration
    ) {

        linearData.yAxisLabels.forEach { point ->

            val bounds = Rect()
            val paint = Paint()

            paint.color = decoration.textColor.toArgb()
            paint.textSize = 12.sp.toPx()
            paint.textAlign = Paint.Align.LEFT
            paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            paint.getTextBounds(point.value.toString(), 0, point.value.toString().length, bounds)

            val width = bounds.width()

            // This draws the String Marker
            drawContext.canvas.nativeCanvas.drawText(
                point.value.toString(),
                point.x,
                point.y,
                paint
            )

            // This draws the Lines for the readings parallel to X Axis
            drawLine(
                start = Offset(
                    x = width.toFloat(),
                    y = point.y - 12f
                ),
                color = decoration.textColor.copy(alpha = 0.8f),
                end = Offset(
                    x = size.width,
                    y = point.y - 12f
                ),
                strokeWidth = 1f
            )
        }

        // This Draws the Y Markers below the Graph
        linearData.xAxisLabels.forEach { currentMarker ->

            // This draws the String Marker
            drawContext.canvas.nativeCanvas.drawText(
                currentMarker.value.toString(),
                currentMarker.x,
                currentMarker.y,
                Paint().apply {
                    color = decoration.textColor.toArgb()
                    textSize = 12.sp.toPx()
                    textAlign = Paint.Align.CENTER
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                }
            )
        }
    }
}