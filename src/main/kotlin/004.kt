import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.LineCap
import org.openrndr.extra.noise.Random
import org.openrndr.extra.noise.linear
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.extra.shapes.hobbyCurve
import org.openrndr.math.Vector2
import kotlin.math.cos


fun main() = application {
    configure {
        width = 1000
        height = 1000
    }


    oliveProgram {

        val mousePoints = generateSequence{Random.point(drawer.bounds)}.take(100).toList()
        val points = generateSequence{Random.point(drawer.bounds)}.take(100).toList()

        extend{

            drawer.clear(ColorRGBa.BLACK)
            drawer.stroke = ColorRGBa.YELLOW
            drawer.strokeWeight = 2.0

            val variation = linear(seconds*0.02)

            val curve = hobbyCurve(points = mousePoints, closed = true)
            val mouse = curve.position(variation)


            val closest = points.minByOrNull { (mouse - it).squaredLength }

            points.forEach {
                drawer.fill = if (it == closest) ColorRGBa.YELLOW else ColorRGBa.BLACK
                drawer.circle(it, 10.0)
            }

        }
    }
}