import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.kdtree.kdTree
import org.openrndr.extra.noise.Random
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector2
import org.openrndr.shape.LineSegment
import org.openrndr.shape.ShapeContour

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */

fun main() = application {
    configure {
        width = 1000
        height = 1000
    }



    oliveProgram {

        //extend(ScreenRecorder()){ maximumDuration = 10.0 }

        val points =
            generateSequence { Random.point(drawer.bounds) }.take(2000).toList()
        val startPoint = Vector2(width / 2.0, 0.0)


        extend {
            drawer.clear(ColorRGBa.YELLOW)
            drawer.fill = ColorRGBa.BLACK
            drawer.stroke = ColorRGBa.BLACK
            drawer.strokeWeight = 2.0


            val tree = points.kdTree()
            var nextPoint: Vector2?
            var hasNext = true
            var previousPoint = startPoint
            val lights = mutableListOf(previousPoint)
            while (hasNext) {

                nextPoint = tree.findAllInRadius(previousPoint, 60.0).filter {
                    it.y > previousPoint.y && it.x in (previousPoint.x - 25.0)..(previousPoint.x + 25.0)
                }.maxByOrNull { it.y }
                if (nextPoint != null) {
                    previousPoint = nextPoint
                    lights.add(previousPoint)
                } else {
                    hasNext = false
                }
            }

            val s = ShapeContour(
                segments = lights.zipWithNext { a, b -> LineSegment(a, b).segment },
                closed = false
            )

            drawer.contour(s)

        }


    }
}