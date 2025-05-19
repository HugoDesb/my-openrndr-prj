package com.example.shapes.centered

import org.openrndr.math.Polar
import org.openrndr.math.Vector2
import org.openrndr.shape.Shape
import org.openrndr.shape.ShapeContour
import kotlin.math.pow
import kotlin.math.sqrt

// a = size 2
// h² = (size/2)² + (size/2)²  <=> h² = 2(size/2)² <=> h = sqrt(2*(size/2)²)
class CenteredSquare(center: Vector2, size: Double, theta: Double = 0.0) {
    companion object{
        val ANGLE_OF_FULL_ROTATION = 90.0
    }
    val initialAngle = 45.0 + (theta % ANGLE_OF_FULL_ROTATION)
    val radius = sqrt(2* (size / 2).pow(2.0))
    val points = listOf(
        Polar(initialAngle, radius),
        Polar(initialAngle+90.0*1, radius),
        Polar(initialAngle+90.0*2, radius),
        Polar(initialAngle+90.0*3, radius)
    ).map {
        it.cartesian.plus(center)
    }

    val contour = ShapeContour.fromPoints(points, closed = true)
}




