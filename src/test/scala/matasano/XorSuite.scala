package matasano

import org.scalatest.FunSuite

class XORSuite extends FunSuite {

  val XORs = 
    Array(
      ("1c0111001f010100061a024b53535009181c",
        "686974207468652062756c6c277320657965",
        "746865206b696420646f6e277420706c6179")
    )

  test("XOR hex strings") {
    for ((x1, x2, res) <- XORs)
      assert( XOR.FixedXOR(Tools.decodeHex(x1), Tools.decodeHex(x2)) === Tools.decodeHex(res))
  }

}
