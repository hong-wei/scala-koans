package org.functionalkoans.forscala

import support.KoanSuite

import scala.collection.immutable.Seq

class AboutForExpressions extends KoanSuite {

  koan("For loops can be simple") {
    val someNumbers = Range(0, 10)
    var sum = 0
    for (i <- someNumbers)
      sum += i

    sum should equal(45)
  }

  koan("For loops can contain additional logic") {
    val someNumbers = Range(0, 10)
    var sum = 0
    // sum only the even numbers
    for (i <- someNumbers)
      if (i % 2 == 0) sum += i

    sum should equal(2+4+6+8)
  }

  koan("For expressions can nest, with later generators varying more rapidly than earlier ones") {
    val xValues = Range(1, 5)
    val yValues = Range(1, 3)
    val coordinates: Seq[(Int, Int)] = for {
      x <- xValues //1 
      y <- yValues} yield (x, y) // 1,1  1,2  2,1 2,2
    coordinates(4) should be(3, 1)
  }
}
