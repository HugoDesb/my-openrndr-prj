import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.LineJoin
import org.openrndr.extra.noise.perlinLinear
import org.openrndr.extra.parameters.DoubleParameter
import org.openrndr.extra.parameters.IntParameter
import org.openrndr.shape.LineSegment

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */

fun main() = application {
    configure {
        width = 1200
        height = 1200
    }


    program {

        //val gui = GUI()
        val settings = object {
            @DoubleParameter("magnifier", 0.0, 80.0)
            var magnifier: Double = 30.0
            @IntParameter("Seed degrees", 1, 100)
            var seed: Int= 333
            @DoubleParameter("speed", 10.0, 1000.0)
            var speed : Double = 100.0
        }//.addTo(gui)


        //extend(ScreenRecorder()) { maximumDuration = 10.0 }

        extend {
            drawer.clear(ColorRGBa.YELLOW)
            drawer.stroke = ColorRGBa.BLACK
            drawer.lineJoin = LineJoin.ROUND

            val scale = 0.003
            drawer.fill = ColorRGBa.BLACK


            for (y in 0..height step 20){
                for (x in 0..width step 20){
                    val perlin = perlinLinear(settings.seed, (x+settings.speed*seconds) * scale, (y+settings.speed*seconds) * scale)
                    val degrees = perlin * 360
                    val line = LineSegment(x.toDouble(), y.toDouble(), x + settings.magnifier*perlin, y.toDouble())

                    drawer.lineSegment(line.rotate(degrees))

                }
            }
        }
        //extend(gui)

    }
}