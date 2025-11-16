package com.marshall.milkshakekmp.ui.milkshake.components

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TopSemicircleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width, size.height)
            lineTo(0f, size.height)
            lineTo(0f, size.height / 2)

            arcTo(
                rect = Rect(
                    left = 0f,
                    top = size.height / 2 - size.width / 2,
                    right = size.width,
                    bottom = size.height / 2 + size.width / 2
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )

            close()
        }
        return Outline.Generic(path)
    }
}
