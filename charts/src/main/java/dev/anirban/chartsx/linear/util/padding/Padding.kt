package dev.anirban.chartsx.linear.util.padding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


/**
 * This class contains the basic padding data and their operations. It also implements the
 * [PaddingValues] class so that means it can work directly with the Modifiers and Compose
 * functions.
 */
class Padding : PaddingValues {


    /**
     * This is the padding of the start or the left side of the Composable
     */
    val start: Dp


    /**
     * This is the top padding of the composable
     */
    val top: Dp


    /**
     * This is the end or the right side padding of the composable
     */
    val end: Dp


    /**
     * This is the bottom padding of the composable
     */
    val bottom: Dp


    constructor(
        start: Dp = 0.dp,
        top: Dp = 0.dp,
        end: Dp = 0.dp,
        bottom: Dp = 0.dp
    ) {
        this.start = start
        this.top = top
        this.end = end
        this.bottom = bottom
    }


    constructor(
        horizontal: Dp = 0.dp,
        vertical: Dp = 0.dp
    ) {
        this.start = horizontal
        this.top = vertical
        this.end = horizontal
        this.bottom = horizontal
    }


    override fun calculateBottomPadding(): Dp = bottom

    override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp = start

    override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp = end

    override fun calculateTopPadding(): Dp = top
}