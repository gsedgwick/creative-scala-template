import doodle.core.Image._
import doodle.core.{Color, _}
import doodle.syntax._
import doodle.random._
import doodle.turtle.Instruction.branch
//import cats.syntax.semigroup._
import cats.Monoid
import cats.implicits._
import doodle.turtle._
import doodle.turtle.Instruction._


// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image = circle(10) on circle(20) on circle(500)
  //Test//
  val image2 = ((circle(20) fillColor (Color.red)) beside circle(20) beside circle(20)) on circle(300)
  val image3 = ((circle(200) fillColor (Color.red)) beside circle(20) beside circle(20)) on circle(300)
  val trianglewidget = triangle(40, 40)
  val image3triangles = trianglewidget above trianglewidget beside trianglewidget
  val target = (circle(10) fillColor Color.red) on (circle(20) fillColor Color.white) on (circle(40) fillColor Color.red)

  val roof = triangle(40, 40) fillColor Color.pink

  val door = (rectangle(20, 80) fillColor Color.black) on (rectangle(40, 80) fillColor Color.red)

  val housewithcrazydoor = roof above door

  val street = (rectangle(20, 5) fillColor Color.yellow) beside (rectangle(15, 5) fillColor Color.black) above (rectangle(35, 5) fillColor Color.black)

  val fullstreet = street beside street beside street beside street


  //why is there a little gap between the street segments?

  val tree = (circle(25) fillColor Color.green) above rectangle(20, 20) fillColor Color.brown


  val part1 = "The"
  val part2 = "Knights"
  val part3 = "Who"
  val part4 = "Say"
  val part5 = "Ni"
  //val aaa = println(part1);println(part2);println(part3);println(part4);println(part5)


  //Chapter 6
  val box =
    rectangle(40, 40).lineWidth(5.0).lineColor(Color.royalBlue.spin(30.degrees)).fillColor(Color.royalBlue)

  val boxes = box beside box beside box beside box beside box

  def genericBox(color: Color): Image = {
    val box = rectangle(40, 40).lineWidth(5.0).lineColor(Color.royalBlue.spin(30.degrees)).fillColor(color)
    box beside box beside box beside box beside box
  }

  genericBox(Color.paleGoldenrod)

  def squareme(input: Int) = {
    input * input
  }

  def halfme(input: Double) = {
    input / 2.0
  }

  //**********Chapter 7**************

  def aBox = Image.rectangle(20, 20).fillColor(Color.royalBlue)

  def boxes(count: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => aBox beside boxes(n - 1) above boxes(n - 1) //Funky!//
    }
  }

  //Guess Result
  //abcd = 2 since it is matching on exact string
  // 1 = "one".  It would match both One and Two but = one since it comes first in expression
  //1 = 2 since it matches the wildcard n and resolves to n +1, which here is 1 + 1.
  //1 = 1 since it matches the wildcard a. It would match all of them (a,b,c) but does a since a is first.
  // No match = would get an error when running program.  Could would compile but stop when it gets to that section.

  def cross(count: Int): Image = {
    val unit = Image.circle(44)
    count match {
      case 0 => Image.empty
      //case n => unit beside unit above cross (n-1)  /tall rectangle
      //case n => unit above (unit beside cross(-1))  //this fails
      //case n => unit beside (unit above cross (n-1) above unit)  //cool curvy arrow, why does it do that?
      case n => unit beside (unit above cross(n - 1) above unit) beside unit //the right answer
    }
  }

  def chessboard(count: Int): Image = {
    val redsquare = Image.rectangle(20, 20).fillColor(Color.red)
    val blacksquare = Image.rectangle(20, 20).fillColor(Color.black)
    val square4 = (redsquare beside blacksquare) above (blacksquare beside redsquare)
    count match {
      case 0 => square4
      //case n => ((redsquare beside blacksquare) above (blacksquare beside redsquare)) beside chessboard (n-1) // this gives me one block
      //case n => (((redsquare beside blacksquare) above (blacksquare beside redsquare)) beside chessboard (n-1)) above chessboard(n-1) //cool picture, but not a chessboard...
      case n =>
        val unit = chessboard(n - 1)
        (unit beside unit) above (unit beside unit)
    }
    //Example.chessboard(2).draw


  }

  def sierpinski(count: Int): Image = {
    val pinktriangle = Image.triangle(20, 20).lineColor(Color.pink)
    count match {
      case 0 => pinktriangle above (pinktriangle beside pinktriangle)
      case n =>
        val unit = sierpinski(n - 1)
        unit above (unit beside unit)
    }
  }

  //Example.sierpinski(4).draw
  //Question on this  - when doing x above (x beside x) it automatically centers the top shape between the bottom two shapes, how does it
  //know to do this?  Can you change that behavior?


  //Recursion examples
  //identity works
  //double doesn't work as you're doubling each number between the number you pass in and 0.  So I suppose it would with 0 or 1, but nothing else

  //Gradient Boxes
  def gradientBoxes(count: Int, size: Int, spinStart: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => Image.rectangle(size, size).fillColor(Color.royalBlue.spin(spinStart.degrees)).lineColor(Color.pink.spin(spinStart.degrees)) beside gradientBoxes(n - 1, size, spinStart + 10)
    }
  }


  def concCircles(count: Int, size: Int, spinner: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => Image.circle(size).lineColor(Color.royalBlue.spin(spinner.degrees)) on concCircles(n - 1, size + 10, spinner + 10)
    }
  }

  //Example.concCircles(20,20).draw
  //For changing colors Example.concCircles(20,20,20).draw


  ////Chapter 8

  def dot = Image.circle(5).lineWidth(3).lineColor(Color.crimson)

  def squareDots = dot.at(0, 0).
    on(dot.at(0, 100)).
    on(dot.at(100, 100)).
    on(dot.at(100, 0))

  def paraCircle(angle: Angle): Point = Point.polar(200, angle)

  def rose(angle: Angle) = Point.polar((angle * 12).cos * 200, angle)

  val roseFn = rose _


  def sample(start: Angle, samples: Int): Image = {
    val step = Angle.one / samples
    val dot = triangle(10, 10)

    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n => dot.at(rose(angle).toVec) on loop(n - 1)
      }
    }

    loop(samples)
  }

  //What is the type of function of roseFn?  Takes Angle returns point
  //roseFn as function literal
  val roseFnLit = (angle: Angle) => Point.cartesian((angle * 12).cos * angle.cos, (angle * 12).cos * angle.sin)


  def concentricShapes(count: Int, singleShape: Int => Image): Image =
    count match {
      case 0 => Image.empty
      case n => singleShape(n) on concentricShapes(n - 1, singleShape)
    }

  def colorSpin(n: Int, factor: Int, spinUp: Boolean): Color =
    spinUp match {
      case true => Color.blue spin (n * factor).degrees
      case false => Color.blue spin (n * factor * -1).degrees
    }

  def prettyCircle(n: Int) = {
    //val color = Color.blue spin (n * 10).degrees
    val shape = Image.circle(10 + n * 10)
    shape lineWidth 10 lineColor colorSpin(n, n, true)
  }

  def prettyTriangle(n: Int) = {
    //val color = Color.blue spin (n * 10).degrees
    val shape = Image.triangle(66 + n * 10, 66 + n * 10)
    shape lineWidth 10 lineColor colorSpin(n, n, false)
  }

  def prettySquare(n: Int) = {
    //val color = Color.blue spin (n * 10).degrees
    val shape = Image.rectangle(66 + n * 10, 66 + n * 10)
    shape lineWidth 10 lineColor colorSpin(n, n, true)
  }


  val noWhammies = concentricShapes(10, prettyCircle) beside concentricShapes(10, prettyTriangle) beside concentricShapes(10, prettySquare)
  val noWhammiesTrippy = concentricShapes(10, prettyCircle) on concentricShapes(10, prettyTriangle) on concentricShapes(10, prettySquare)

  /////Combine
  def parametricCircle(angle: Angle): Point =
    Point.cartesian(angle.cos, angle.sin)

  def roseate(angle: Angle): Point = Point.cartesian((angle * 7).cos * angle.cos, (angle * 7).cos * angle.sin)

  def scale(factor: Double): Point => Point =
    (pt: Point) => {
      Point.polar(pt.r * factor, pt.angle)
    }

  def sample(start: Angle, samples: Int, location: Angle => Point): Image = {
    val step = Angle.one / samples
    val dot = triangle(10, 10)

    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n => dot.at(location(angle).toVec) on loop(n - 1)
      }
    }

    loop(samples)
  }

//  def locate(scale: Point => Point, point: Angle => Point): Angle => Point = (angle: Angle) => scale(point(angle))
//
//  val flower = {
//    sample(0.degrees, 200, locate(scale(200), rose _)) on
//      sample(0.degrees, 40, locate(scale(150), parametricCircle _))
//
//  }

  //class on 4/28

  implicit object pointInstance extends Monoid[Point] {
    def empty = Point.zero

    def combine(x: Point, y: Point): Point =
      Point(x.x + y.x, x.y + y.y)
  }

  val circle44: Double => (Angle => Point) =
    (frequency: Double) => (a: Angle) => Point.polar(1.0, a * frequency)
  val scale44: Double => (Point => Point) =
    (r: Double) => (pt: Point) => Point(pt.x * r, pt.y * r)

  val curve44: Double => Angle => Point =
    (r: Double) =>
      (circle44(1) andThen scale44(100)) |+| (circle44(6) andThen scale44(50)) |+| (circle44(-14) andThen scale44(33))

  val sample2: Int => (Angle => Image) => Image =
    (n: Int) => {
      val step = Angle.one / n
      (parametric: (Angle => Image)) => {
        def loop(count: Int): Image =
          count match {
            case 0 => Image.empty
            case n => parametric(step * n) on loop(n - 1)
          }

        loop(n)
      }
    }

  val style: Point => Image =
    (pt: Point) =>
      Image.circle(3).at(pt.toVec).lineColor(Color.royalBlue).fillColor(Color.hsla(pt.angle, 0.7.normalized, 0.7.normalized, 0.5.normalized))

  val image44 = sample2(200)(curve44(200) andThen style)

  val image88 = sample2(400)(curve44(20) andThen style)


  //Shapes, Sequences and Stars
  def reverse[A](list: List[A]): List[A] = {
    def iter(list: List[A], reversed: List[A]): List[A] =
      list match {
        case Nil => reversed
        case hd :: tl => iter(tl, hd :: reversed)
      }

    iter(list, Nil)
  }

  def ascending(n: Int):List[Int]=
    (0 until n).toList.map(x=>x+1)

  //no time to experiment but the chapter was very helpful.

  //Random Circles
/*  Random.int()

  We going to create random circles inside a circle. (This is called "generative art". If you own a beret now is a good time to put it on.)

  We're going to use `map` and a new tool, `flatMap`, in a different context. The idea is to broaden how we think about these tools.

    Step 1.
  - How could we create a point that is randomly located within a circle? There are many ways to do this. Think of one.
    - Use the following methods to create random numbers (you'll need to `import doodle.random._`)
  `Random.double`
  `Random.natural`
  `Random.int`

  also `Random[A] map (A => B): Random[B]` and
  `Random[A] flatMap (A => Random[B]): Random[B]`
  to compose functions with `Random` and thus transform them
  - Now implement your method to a create point.
  - Finally, call the `run` method and you should get a `Point`.

  Step 2.
  - Given a random point, transform it into a random circle (choose size and color at random)
  - Don't use `run`---you're only allowed to call `run` at the very end!
    - Draw an `Image` (you're only allowed to call `run` immediately before you call `draw`)

  Step 3.
  - Create lots of random circles to create a picture of circles arranged in a circle shape.
  - Don't use `run`---you're only allowed to call `run` at the very end!

  */

  val angle: Random[Angle] = Random.int(0,360).map(_.degrees)

  val scale: Double => (Point => Point) =
    (r: Double) => (pt: Point) => Point(pt.x * r, pt.y * r)

  //github / sharedsource/Tiles.scala
  
//Week 6
  val instructions = List(forward(10), turn(90.degrees),
    forward(10), turn(90.degrees),
    forward(10), turn(90.degrees),
    forward(10)
  )

  val path = Turtle.draw(instructions)

  def spiral(steps: Int, distance: Double, angle: Angle, increment: Double): Image = {
    def iter(n: Int, distance: Double): List[Instruction] = {
      n match {
        case 0 => Nil
        case n => forward(distance) :: turn(angle) :: iter(steps - 1, distance + increment)
      }
    }
    Turtle.draw(iter(steps, distance))
  }

  val fork = Turtle.draw(List(forward(100),  //turned into a square on a stick
    branch(turn(45.degrees),forward(100),branch(turn(-90.degrees), forward(100))),
    branch(turn(-45.degrees),forward(100),branch(turn(90.degrees), forward(100)))))


}