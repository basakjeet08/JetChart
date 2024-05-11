package dev.anirban.charts.linear.margins

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.linear.interfaces.LinearMarginInterface
import dev.anirban.charts.linear.data.LinearEmojiData

/**
 * This is one of the implementations of the [LinearMarginInterface] and it provides with a implementation
 * of how we should draw the Margin
 */
class LinearEmojiMargin : LinearMarginInterface {

    /**
     * This is the function which contains the actual margin implementation
     *
     * @param linearData This is the data of the Line Chart
     * @param decoration THis is the decoration of the function
     */
    override fun DrawScope.drawMargin(
        linearData: LinearDataInterface,
        decoration: LinearDecoration
    ) {

        val dimension = (linearData as LinearEmojiData).dimension
        linearData.yAxisLabels.forEach { point ->

            point.value as BitmapDrawable

            val resizedBitmap =
                Bitmap.createScaledBitmap(point.value.bitmap, dimension, dimension, true)
            val width = resizedBitmap.width

            translate(point.x, point.y) {
                drawImage(image = resizedBitmap.asImageBitmap())
            }

            // This draws the Lines for the readings parallel to X Axis
            drawLine(
                start = Offset(
                    x = width.toFloat(),
                    y = point.y + (dimension.toFloat() / 2f)
                ),
                color = decoration.textColor.copy(alpha = 0.8f),
                end = Offset(
                    x = size.width,
                    y = point.y + (dimension.toFloat() / 2f)
                ),
                strokeWidth = 1f
            )
        }

        // This Draws the Y Markers below the Graph
        linearData.xAxisLabels.forEach { currentMarker ->

            // This draws the String Marker
            drawContext.canvas.nativeCanvas.drawText(
                currentMarker.value,
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