import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

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
        extend{
            drawer.clear(ColorRGBa.BLACK)

            val horizontalStandardDeviation = Vector2(35.0, 0.0)
            val verticalStandardDeviation = Vector2(0.0, 35.0)
            val square = Rectangle(0.0, 0.0, 15.0, 15.0)

            drawer.fill = null
            drawer.stroke = ColorRGBa.YELLOW
            drawer.strokeWeight = 2.0

            drawer.translate(35.0, 35.0)
            drawer.rectangles {
                repeat(27){v ->
                    repeat(27){h ->
                        val deviation = horizontalStandardDeviation.times(h.toDouble())+verticalStandardDeviation.times(v.toDouble())
                        val sq = square.movedBy(deviation)

                        val c = h+v
                        stroke = if (v.mod(2) != 0) ColorRGBa.BLACK else ColorRGBa.YELLOW
                        rectangle(sq, 360*(seconds*0.1).mod(1.0))
                    }
                }

            }
        }

    }
}