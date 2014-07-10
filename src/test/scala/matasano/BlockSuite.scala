package matasano

import org.scalatest.FunSuite

import org.scalacheck._
import Prop.forAll

import matasano.Block._

class BlockSuite extends FunSuite {

  test("block") {
    val a = Array[Byte](1,1,2,2,3,3)
    val b = Array[Byte](1,1,2,2,3)

    assert(makeBlocks(a,2) === Array(Array(1,1), Array(2,2),Array(3,3)))
    assert(makeBlocks(b,2) === Array(Array(1,1), Array(2,2)))
  }

  test("pkcs7 padding") {
    val s = "YELLOW SUBMARINE"
    val e20 = Array(89, 69, 76, 76, 79, 87, 32, 83, 85, 66, 77, 65, 82, 73, 78, 69, 4, 4, 4, 4)
    assert(pkcs7Pad(s.getBytes, 20) === e20)
    val e8 = Array(89, 69, 76, 76, 79, 87, 32, 83, 85, 66, 77, 65, 82, 73, 78, 69, 8, 8, 8, 8, 8, 8, 8, 8)
    assert(pkcs7Pad(s.getBytes, 8) === e8)
  }
}

