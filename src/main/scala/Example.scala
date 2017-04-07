import doodle.core.Color
import doodle.core.Image._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image = circle(10) on circle(20) on circle(500)
  //Test//
val image2 = ((circle(20) fillColor (Color.red)) beside circle (20) beside circle (20)) on circle (300)
val image3 =  ((circle(200) fillColor (Color.red)) beside circle (20) beside circle (20)) on circle (300)
  val trianglewidget = triangle(40,40)
  val image3triangles = trianglewidget above trianglewidget beside trianglewidget
  val target = (circle(10) fillColor Color.red) on (circle (20) fillColor Color.white) on (circle(40) fillColor Color.red)

val roof = triangle(40,40) fillColor Color.pink

val door = (rectangle(20,80) fillColor Color.black) on (rectangle(40,80) fillColor Color.red)

val housewithcrazydoor = roof above door

 val street = (rectangle(20,5) fillColor Color.yellow) beside (rectangle(15,5) fillColor Color.black) above (rectangle(35,5) fillColor Color.black)

  val fullstreet = street beside street beside street beside street
//why is there a little gap between the street segments?

  val part1 = "The"
  val part2 = "Knights"
  val aaa = println("Hello");println(part2)
}
