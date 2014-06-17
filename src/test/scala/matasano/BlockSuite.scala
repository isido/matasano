package matasano

import org.scalatest.FunSuite

import matasano.Block._

class BlockSuite extends FunSuite {

  test("block") {
    val a = Array[Byte](1,1,2,2,3,3)
    val b = Array[Byte](1,1,2,2,3)

    assert(makeBlocks(a,2) === Array(Array(1,1), Array(2,2),Array(3,3)))
    assert(makeBlocks(b,2) === Array(Array(1,1), Array(2,2)))
  }
}
