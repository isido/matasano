package matasano

import org.scalacheck._
import Prop.BooleanOperators // not working for some reason :|
import Prop._

import matasano.Block._
 
object BlockSpecs extends Properties("Block") {

  val arrayGen = Gen.containerOf[Array, Byte](0.toByte)
  val intGen = Gen.choose(1, 255)

  property("pkcs7Pad") = Prop.forAllNoShrink(arrayGen, intGen) { (a, b) => 
    val padded = pkcs7Pad(a, b)
    (a.length != padded.length) :| "Padding is not added" && 
    ((a.length >= b) && (padded.length % b == 0))
  }
}
