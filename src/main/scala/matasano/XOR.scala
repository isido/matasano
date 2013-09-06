package matasano

object XOR {

  /**
    * XOR two byte arrrays
    */
  def fixedXOR(a: Array[Byte], b: Array[Byte]): Array[Byte] =
    (a, b).zipped map ( (x, y) => (x ^ y).asInstanceOf[Byte] ) // scala/java bitwise xor result type is always Int

  /**
    * XOR two hex strings
    */
  def fixedXOR(a: String, b: String): String =
    Tools.encodeHex(fixedXOR(Tools.decodeHex(a), Tools.decodeHex(b)))

  /**
    * XOR byte array with single byte repeteadly
    */
  def singleCharacterXOR(a: Array[Byte], c: Byte): Array[Byte] =
    a map ( x => (x ^ c).asInstanceOf[Byte] )

  /**
    * XOR hex string with single character repeteadly
    */
  def singleCharacterXOR(a: String, c: Char): String =
    Tools.encodeHex(singleCharacterXOR(Tools.decodeHex(a), c.toByte))

  /**
    * Find most likely plaintext given ciphertext that is encrypted using
    * single character XOR cipher
    */
  def decryptSingleCharacterXOR(ciphertext: Array[Byte], candidates: List[Char]): Array[Byte] =
    ciphertext

  /**
    *  Repeat key byte array so that it is as long as the ciphertext byte array
    */
  def repeatKey(a: Array[Byte], key: Array[Byte]) =
    Stream.continually(key).flatten.take(a.length).toArray

  /**
    * Repeat key string so that it is as long as a string.
    */
  def repeatKey(a: String, key: String): String =
    (key * (1 + (a.length / key.length))).take(a.length)

  /**
    * XOR plain string with repeated key (also plain string)
    */
  def repeatingKeyXOR(a: String, key: String): Array[Byte] =
    fixedXOR(a.getBytes, repeatKey(a, key).getBytes)

  /**
    * Create plaintext candidates
    * ciphertext is hex string
    */
  def xorPlaintextCandidates(ciphertext: String, keys: List[Char]) = {
    keys map ( k => new String(singleCharacterXOR(Tools.decodeHex(ciphertext), k.toByte), "UTF-8"))
  }

  def breakSingleCharacterXOR(ciphertext: String, keyspace: String, metric: Metric[Char]): (String, Double) = {
    import CharacterHistogram._

    val candidates = keyspace map ( k => new String(XOR.singleCharacterXOR(Tools.decodeHex(ciphertext), k.toByte), "UTF-8"))
    val distances = candidates map ( txt => (txt, metric.distance(makeCharacterHistogramMap(txt), english)))
    distances.sortBy(_._2).head
  }
}
