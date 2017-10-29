package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

class AboutLiteralBooleans extends KoanSuite with ShouldMatchers {

  koan("""Boolean literals are either true or false, using the true or false keyword""") {
    val a = true
    val b = false
    val c: Boolean = 1 > 2
    val d: Boolean = 1 < 2
    val e: Boolean = a == c // t=f
    val f: Boolean = b == d //f ==t
    a should be(true)
    b should be(false)
    c should be(false)
    d should be(true)
    e should be(false)
    f should be(false)
  }

}
