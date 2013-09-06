package matasano

import org.scalatest.FunSuite

class HammingSuite extends FunSuite {

  val hammings = Array(
    ("this is a test",
      "wokka wokka!!!",
      37)
  )

  test("Hamming distance") {
    for ((x1, x2, res) <- hammings)
      assert(Hamming.distance(x1.getBytes, x2.getBytes) === res)
  }

}
