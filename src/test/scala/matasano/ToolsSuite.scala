package matasano

import org.scalatest.FunSuite

class ToolsSuite extends FunSuite {

  val hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
  val b64 = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"

  test("decoding hex and encoding it to base64") {
    assert(Tools.encodeBase64(Tools.decodeHex(hex)) === b64)
  }
}
