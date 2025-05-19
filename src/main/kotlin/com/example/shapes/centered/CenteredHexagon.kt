package com.example.shapes.centered

import org.openrndr.math.Polar
import org.openrndr.math.Vector2
import org.openrndr.shape.ShapeContour

class CenteredHexagon(center: Vector2, radius: Double, theta: Double = 0.0) {

    companion object{
        val ANGLE_OF_FULL_ROTATION = 60.0
    }

    val points = listOf(
        Polar(theta, radius),
        Polar(theta+ ANGLE_OF_FULL_ROTATION*1, radius),
        Polar(theta+ ANGLE_OF_FULL_ROTATION*2, radius),
        Polar(theta+ ANGLE_OF_FULL_ROTATION*3, radius),
        Polar(theta+ ANGLE_OF_FULL_ROTATION*4, radius),
        Polar(theta+ ANGLE_OF_FULL_ROTATION*5, radius)
    ).map {
        it.cartesian.plus(center)
    }

    val contour = ShapeContour.fromPoints(points, closed = true)
}




