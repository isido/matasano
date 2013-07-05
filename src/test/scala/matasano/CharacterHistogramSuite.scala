package matasano

import org.scalatest.FunSuite

class CharacterHistogramSuite extends FunSuite {

  val counts = Array(
    ("", Map[Char, Int]()),
    ("aab", Map('a' -> 2, 'b' -> 1))
  )

  test("countCharacters") {
    for ((s, res) <- counts)
      assert((CharacterHistogram characterCount s) === res)
  }


}
