package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite
//BK read more about  ByNameParameter --https://tpolecat.github.io/2014/06/26/call-by-name.html
//https://alvinalexander.com/source-code/scala/simple-scala-call-name-example

/**
 * AboutByNameParameter
 * Koan to help understand by name parameters in Scala
 * Prerequisites: AboutEither, AboutHigherOrderFunctions, AboutExceptions,
 *                About Pattern Matching, AboutApply
 */
class AboutByNameParameter extends KoanSuite {
  
//  https://alvinalexander.com/source-code/scala/simple-scala-call-name-example
 // call-by-value 
  def time(): Long = {
    println("In time()")
    System.nanoTime
  }
  
  def exec(t: Long): Long = {
    println("Entered exec, calling t ...")
    println("t = " + t)
    println("Calling t again ...")
    t
  }
  
  println(exec(time())) // t is totally the same, before run exec, t has been evaluated. 
//  In time()
//  Entered exec, calling t ...
//  t = 1363909521286596000
//  Calling t again ...
//  1363909521286596000
  
  //call- by - name
  def time2() = {
    println("Entered time() ...")
    System.nanoTime
  }
  
  // uses a by-name parameter here
  def exec2(t: => Long) = {
    println("Entered exec, calling t ...")
    println("t = " + t)
    println("Calling t again ...")
    t
  }
  
  println(exec2(time2())) // t is different ,each time, it call t, t will be evaluated again.
//  Entered exec, calling t ...
//  Entered time() ...
//  t = 1363909593759120000
//  Calling t again ...
//  Entered time() ...
//  1363909593759480000
  
  // https://tpolecat.github.io/2014/06/26/call-by-name.html
  def when[A](test: Boolean, whenTrue: A, whenFalse: A): A =
    test match {
      case true  => whenTrue
      case false => whenFalse
    }
  
  when(1 == 1, println("foo"), println("bar"))
  
  //when is always evaluated,even if we do not use it . it has some side affect.
  
  
  def when2[A](test: Boolean, whenTrue: => A, whenFalse: => A): A =
    test match {
      case true  => whenTrue
      case false => whenFalse
    }
  
  when2(1 == 1, println("foo"), println("bar"))
  
  koan(
    """() => Int is a Function type that takes a Unit type. Unit is known as 'void' to a Java programmer. The function
      | and returns an Int. You can place this as a method parameter so that you can you use it as a block, but still
      | it doesn't look quite right.""") {

    def calc(x: () => Int): Either[Throwable, Int] = {
      try {
        Right(x()) //An explicit call the the x function
      } catch {
        case b: Throwable => Left(b)
      }
    }

    val y = calc {() => //Having explicitly declaring that Unit is a parameter with ()
      14 + 15
    }

    y should be (Right(29))
  }


  koan(
    """A by-name parameter does the same thing as a previous koan but there is no need to explicitly
      | handle Unit or (). This is used extensively in scala to create blocks.""") {

    def calc(x: => Int): Either[Throwable, Int] = {   //x is a call by name parameter
      try {
        Right(x)
      } catch {
        case b: Throwable => Left(b)
      }
    }

    val y = calc {                                    //This looks like a natural block
      println("Here we go!")                          //Some superfluous call
      val z = List(1, 2, 3, 4)                        //Another superfluous call
      49 + 20
    }

    y should be (Right(69))
  }
  
  koan("""By name parameters can also be used with an Object and apply to make interesting block-like calls""") {
    object PigLatinizer {
      def apply(x: => String) = x.tail + x.head + "ay"
    }

    val result = PigLatinizer {
      val x = "pret" // ret
      val z = "zel"  //ze
      x ++ z 
    }

    result should be ("retzelpay")
  }
}
