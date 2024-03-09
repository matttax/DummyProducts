package com.matttax.dummyproducts.ui.common

import android.graphics.PointF
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class StarShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawStarPath(size)
        )
    }

    private fun drawStarPath(size: Size): Path {
        return Path().apply {
            val center = PointF(size.width / 2f, size.height / 2f)
            val outerRadius = minOf(size.width, size.height) / 2f
            val innerRadius = outerRadius / INNER_TO_OUTER_RADIUS
            var angle = -PI / 2f
            moveTo(
                x = (center.x + outerRadius * cos(angle)).toFloat(),
                y = (center.y + outerRadius * sin(angle)).toFloat()
            )

            // Отрисовка лучей звезды по порядку
            for (i in 1..POINT_COUNT) {
                // Внутренний угол
                angle += ANGLE_INCREMENT / 2
                lineTo(
                    x = (center.x + innerRadius * cos(angle)).toFloat(),
                    y = (center.y + innerRadius * sin(angle)).toFloat()
                )
                // Внешний угол
                angle += ANGLE_INCREMENT / 2
                lineTo(
                    x = (center.x + outerRadius * cos(angle)).toFloat(),
                    y = (center.y + outerRadius * sin(angle)).toFloat()
                )
            }
            close()
        }
    }

    companion object {
        const val POINT_COUNT = 5
        const val ANGLE_INCREMENT = 2 * PI / POINT_COUNT
        const val INNER_TO_OUTER_RADIUS = 2.5f
    }
}