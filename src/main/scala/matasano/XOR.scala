package matasano

object XOR {

  def FixedXOR(a: Array[Byte], b: Array[Byte]): Array[Byte] =
    (a, b).zipped map ( (x, y) => (x ^ y).asInstanceOf[Byte] ) // scala/java bitwise xor result type is always Int

  def FixedXOR(a: String, b: String): String =
    Tools.encodeHex(FixedXOR(Tools.decodeHex(a), Tools.decodeHex(b)))

  def SingleCharacterXOR(a: Array[Byte], c: Byte): Array[Byte] =
    a map ( x => (x ^ c).asInstanceOf[Byte] )

  def SingleCharacterXOR(a: String, c: Char): String =
    Tools.encodeHex(SingleCharacterXOR(Tools.decodeHex(a), c.toByte))

}
