import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.Random
import org.openrndr.extra.noise.linear
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.extra.shapes.hobbyCurve

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

        val points = listOf(
            generateSequence{Random.point(drawer.bounds)}.take(100).toList(),
            generateSequence{Random.point(drawer.bounds)}.take(100).toList(),
            generateSequence{Random.point(drawer.bounds)}.take(100).toList(),
            generateSequence{Random.point(drawer.bounds)}.take(100).toList(),
            generateSequence{Random.point(drawer.bounds)}.take(100).toList(),
            generateSequence{Random.point(drawer.bounds)}.take(100).toList()
        )
        extend {
            drawer.clear(ColorRGBa.YELLOW)
            drawer.fill = ColorRGBa.BLACK

            points.forEach {points ->
                val curve = hobbyCurve(points = points, closed = true)

                val variation = linear(seconds*0.02)
                val subCurve = curve.sub(variation, variation+0.003)
                val subElements = subCurve.equidistantPositions(100)


                subElements.forEachIndexed { index, point ->
                    drawer.circle(point, index*0.1)
                }
            }
        }


    }
}