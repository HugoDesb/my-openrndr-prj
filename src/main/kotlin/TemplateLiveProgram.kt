import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector2
import org.openrndr.shape.Circle
import org.openrndr.shape.contour
import kotlin.math.cos

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */

fun main() = application {
    configure {
        width = 800
        height = 800
    }
    oliveProgram {
        extend {
            drawer.clear(ColorRGBa.YELLOW)

            val decalDown = Vector2(0.0, 50.0)

            drawer.stroke = ColorRGBa.TRANSPARENT

            drawer.fill = ColorRGBa.BLACK
            repeat(10){
                val startPoint = Vector2(100.0, 100.0).plus(decalDown.times(it.toDouble()))
                drawer.arrow(startPoint = startPoint, distance = 100.0, seconds = seconds, offset = it*2.0)
            }

            drawer.fill = ColorRGBa.WHITE
            repeat(10){
                val startPoint = Vector2(100.0, 110.0).plus(decalDown.times(it.toDouble()))
                drawer.arrow(startPoint = startPoint, distance = 100.0, seconds = seconds, offset = it*2.0)
            }

            drawer.circle(Circle(drawer.bounds.center, 20.0))
        }
    }
}

fun Drawer.arrow(
    startPoint: Vector2,
    distance: Double = 60.0,
    seconds: Double,
    size: Double = 20.0,
    offset: Double = 0.0
){
    val length = distance * (cos((seconds+offset)*3)+1)

    val c = contour {
        moveTo(startPoint)
        lineTo(cursor + Vector2(size+length, -size))
        lineTo(cursor + Vector2(size, size))
        lineTo(cursor + Vector2(-size, size))
        lineTo(anchor)
        close()
    }
    contour(c)

}