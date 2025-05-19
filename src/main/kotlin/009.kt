import com.example.shapes.centered.CenteredHexagon
import com.example.shapes.centered.CenteredSquare
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import org.openrndr.color.rgba
import org.openrndr.draw.ShadeStyle
import org.openrndr.draw.grayscale
import org.openrndr.draw.loadImage
import org.openrndr.draw.shadeStyle
import org.openrndr.extra.gui.GUI
import org.openrndr.extra.gui.addTo
import org.openrndr.extra.noclear.NoClear
import org.openrndr.extra.noise.Random
import org.openrndr.extra.noise.random
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.extra.parameters.DoubleParameter
import org.openrndr.math.Polar
import org.openrndr.math.Vector2

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

        val gui = GUI()
        val settings = object {
            @DoubleParameter("pixelSize", 0.0, 25.0)
            var pSize: Double = 3.0

            @DoubleParameter("animation length", 0.0, 10.0)
            var aEnd: Double = 5.0

            @DoubleParameter("hexagon size", 1.0, 100.0)
            var hSize: Double = 20.0
        }.addTo(gui)

        val image = loadImage("data/images/cheeta.jpg")
        

        val zoom = 0.03
        val backgroundColor = ColorRGBa.BLACK





        //extend(gui)
        extend {
            drawer.clear(backgroundColor)
            drawer.image(image)

        }

    }
}

