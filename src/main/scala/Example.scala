import doodle.core.Color
import doodle.core.Image._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image = circle(10) on circle(20) on circle(100)
  //Test//
val image2 = ((circle(20) fillColor (Color.red)) beside circle (20) beside circle (20)) on circle (300)
val image3 =  ((circle(200) fillColor (Color.red)) beside circle (20) beside circle (20)) on circle (300)
}
