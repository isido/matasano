package matasano

import org.scalatest.FunSuite

class XORSuite extends FunSuite {

  val XORs = 
    Array(
      ("1c0111001f010100061a024b53535009181c",
        "686974207468652062756c6c277320657965",
        "746865206b696420646f6e277420706c6179")
    )

  test("FixedXOR hex strings, result in bytes") {
    for ((x1, x2, res) <- XORs)
      assert(XOR.FixedXOR(Tools.decodeHex(x1), Tools.decodeHex(x2)) === Tools.decodeHex(res))
  }

  test("FixedXOR hex string, result in string") {
    for ((x1, x2, res) <- XORs)
      assert(XOR.FixedXOR(x1, x2) === res)
  }

  val SingleCharacterXORs =
    Array(
      ("ff00ff", "00", "ff00ff"),
      ("ff00ff", "ff", "00ff00")
    )

  test("SingleCharacterXOR, result in bytes") {
    for ((x, c, res) <- SingleCharacterXORs)
      assert(XOR.SingleCharacterXOR(Tools.decodeHex(x), Tools.decodeHex(c).head) === Tools.decodeHex(res))
  }

  val SingleCharacterCharXORs =
    Array(
      ("ff00ff", 'a', "9e619e")
    )

  test("SingleCharacterXOR, result in string") {
    for ((x, c, res) <- SingleCharacterCharXORs)
      assert(XOR.SingleCharacterXOR(x, c) === res)
  }
}
