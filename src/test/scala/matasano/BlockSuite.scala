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

  test("pkcs7") {
    val s = "YELLOW SUBMARINE"
    val e = Array(89, 69, 76, 76, 79, 87, 32, 83, 85, 66, 77, 65, 82, 73, 78, 69, 4, 4, 4, 4)
    assert(pkcs7(s.getBytes, 20) === e)

    val e2 = e.take(16)
    assert(pkcs7(s.getBytes, 16) === e2)
  }
}
