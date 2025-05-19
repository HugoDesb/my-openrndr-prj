import com.example.shapes.centered.CenteredHexagon
import com.example.shapes.centered.CenteredSquare
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.gui.GUI
import org.openrndr.extra.gui.addTo
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.extra.parameters.DoubleParameter
import org.openrndr.math.Vector2

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

        val gui = GUI()
        val settings = object {
            @DoubleParameter("animation delay", 0.0, 10.0)
            var aDelay: Double = 3.0

            @DoubleParameter("animation length", 0.0, 10.0)
            var aEnd: Double = 5.0

            @DoubleParameter("hexagon size", 1.0, 100.0)
            var hSize: Double = 20.0
        }.addTo(gui)


        val gridCount = 18

        val margin = 40.0
        val step = (drawer.bounds.height - margin * 2) * (1.0 / gridCount)

        val mapPoints = mutableMapOf<Int, MutableList<Vector2>>()
        (0..gridCount).forEach { i ->
            (0..gridCount).forEach { j ->
                if (mapPoints[i + j] == null) {
                    mapPoints[i + j] = mutableListOf()
                }
                mapPoints[i + j]?.add(Vector2(margin + i * step, margin + j * step))
            }
        }

        val animatedValue = LinearAnimatedValue(
            0.0, CenteredHexagon.ANGLE_OF_FULL_ROTATION, settings.aDelay, settings.aEnd, 10.0)

        extend {
            drawer.clear(ColorRGBa.BLACK)
            drawer.strokeWeight = 2.0
            drawer.stroke = ColorRGBa.YELLOW
            drawer.fill = ColorRGBa.TRANSPARENT

            val time = seconds % 10.0 //loop length
            mapPoints.forEach {
                val index = it.key
                val ajustedTime = time - index * 0.03 // time translation
                val animationTime = index * 0.29
                it.value.forEach { point ->
                    val ratioTheta = if (ajustedTime >= settings.aDelay && ajustedTime < settings.aEnd) {
                        (ajustedTime - settings.aDelay) / (settings.aEnd - settings.aDelay)
                    } else {
                        0.0
                    }
                    drawer.contour(CenteredHexagon(point, settings.hSize, ratioTheta).contour)
                }
            }
        }

    }
}

class LinearAnimatedValue(
    val min: Double = 0.0,
    val max: Double = 1.0,
    val delayTime: Double = 0.0,
    val endTime: Double = 1.0,
    val loopLength: Double = 1.0
){

    fun at(seconds: Double): Double{
        val safeTime = seconds % loopLength
        return if (safeTime >= delayTime && safeTime < endTime) {
            ((safeTime - delayTime) / (endTime - delayTime))*max
        } else {
            min
        }
    }
}
