package matasano

import org.scalacheck._
import Prop.forAll

import matasano.Block._

class BlockSpecs extends Properties("Block") {

  property("dummy") = forAll { a: Int => a == a }
  property("dummy2") = forAll { a: Int => a != a }

}
