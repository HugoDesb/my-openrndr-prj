import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector2
import org.openrndr.shape.*

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */

fun main() = application {
    configure {
        width = 600
        height = 600
    }

    oliveProgram {

        val yTop = drawer.bounds.height * 0.3
        val xLeft = drawer.bounds.width * 0.2
        val sizeBig = drawer.bounds.width * 0.4
        val sizeSmall = drawer.bounds.width * 0.2

        val size = 100.0
        val center = drawer.bounds.center
        val direction = Direction.BOTTOM_LEFT
        val fib = FibSquare(center, size, direction)

        val rBig = Rectangle(xLeft, yTop, sizeBig, sizeBig)
        val rSmallOne = Rectangle(xLeft+sizeBig, yTop, sizeSmall, sizeSmall)
        val rSmallTwo = Rectangle(xLeft+sizeBig, yTop+sizeSmall, sizeSmall, sizeSmall)

        extend {
            drawer.clear(ColorRGBa.BLACK)
            drawer.strokeWeight = 2.0
            drawer.stroke = ColorRGBa.YELLOW
            drawer.fill = ColorRGBa.TRANSPARENT

            drawer.rectangles(listOf(rBig, rSmallOne, rSmallTwo))
            //fib.draw(drawer)
        }

    }
}


enum class Direction(val vertical: Vector2, val horizontal: Vector2) {
    TOP_RIGHT(Vector2(0.0, -1.0), Vector2(1.0, 0.0)),
    TOP_LEFT(Vector2(0.0, -1.0), Vector2(-1.0, 0.0)),
    BOTTOM_LEFT(Vector2(0.0, 1.0), Vector2(-1.0, 0.0)),
    BOTTOM_RIGHT(Vector2(0.0, 1.0), Vector2(1.0, 0.0))
}

class FibSquare(
    val center: Vector2,
    val size: Double,
    val direction: Direction
){
    private val rectangle = Rectangle(center, direction.horizontal.x*size, direction.vertical.y*size)
    private val arc = contour {
        moveTo(center + direction.horizontal*size)
        curveTo(center + direction.horizontal*size + direction.vertical*size, center + direction.vertical*size)
    }

    private val contours: List<ShapeContour> =
        Shape.compound(listOf(rectangle.shape, arc.shape)).contours

    fun draw(drawer: Drawer){
        drawer.contours(contours)
    }
}