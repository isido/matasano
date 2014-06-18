package matasano

import org.scalatest.FunSuite

class HammingSuite extends FunSuite {

  val hammings = Array(
    ("this is a test",
      "wokka wokka!!!",
      37),
    ("the same string",
      "the same string",
      0)
  )

  val hammings2 = Array(
    (Array[Byte](0,0,0,0), Array[Byte](1,1,1,1), 4),
    (Array[Byte](-1,-1,-1,-1), Array[Byte](1,1,1,1), 28)
    //(Array[Byte](-118, 16, 36, 127, -112, -48, -96, 85, 56, -120, -118, -42, 32, 88, -126, 25),
    //  Array[Byte](111, 95, 109, 5, -62, 30, -56, -36, -96, -53, 11, -32, 44, 63, -117, 9), 0)
  )

  test("Hamming distance (from strings)") {
    for ((x1, x2, res) <- hammings)
      assert(Hamming.distance(x1.getBytes, x2.getBytes) === res)
  }

  test("Hamming distance (from arrays)") {
    for ((x1, x2, res) <- hammings2)
      assert(Hamming.distance(x1, x2) === res)
  }

  test("Bits") {
    val bits = Array(
      (0,0),
      (2,1),
      (1,1),
      (255,8)
    )
    for ((n, res) <- bits)
      assert(Hamming.bits(n) === res)
  }

}
