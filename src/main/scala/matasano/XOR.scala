package matasano

object XOR {

  /**
    * XOR two byte arrrays
    */
  def FixedXOR(a: Array[Byte], b: Array[Byte]): Array[Byte] =
    (a, b).zipped map ( (x, y) => (x ^ y).asInstanceOf[Byte] ) // scala/java bitwise xor result type is always Int

  /**
    * XOR two hex strings
    */
  def FixedXOR(a: String, b: String): String =
    Tools.encodeHex(FixedXOR(Tools.decodeHex(a), Tools.decodeHex(b)))

  /**
    * XOR byte array with single byte repeteadly
    */
  def SingleCharacterXOR(a: Array[Byte], c: Byte): Array[Byte] =
    a map ( x => (x ^ c).asInstanceOf[Byte] )

  /**
    * XOR hex string with single character repeteadly
    */
  def SingleCharacterXOR(a: String, c: Char): String =
    Tools.encodeHex(SingleCharacterXOR(Tools.decodeHex(a), c.toByte))

  /**
    * Repeat key string so that it is as long as a string.
    */
  def RepeatKey(a: String, key: String): String =
    (key * (1 + (a.length / key.length))).take(a.length)

  /**
    * XOR plain string with repeated key (also plain string)
    */
  def RepeatingKeyXOR(a: String, key: String): Array[Byte] =
    FixedXOR(a.getBytes, RepeatKey(a, key).getBytes)
}
