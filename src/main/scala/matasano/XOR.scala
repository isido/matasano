package matasano

import Conversions._

object XOR {

  /**
    * XOR two byte arrrays
    */
  def fixedXOR(a: Array[Byte], b: Array[Byte]): Array[Byte] =
    (a, b).zipped map ( (x, y) => (x ^ y).asInstanceOf[Byte] ) // scala/java bitwise xor result type is always Int

  /**
    * XOR byte array with single byte repeteadly
    */
  def singleCharacterXOR(a: Array[Byte], c: Byte): Array[Byte] =
    a map ( x => (x ^ c).asInstanceOf[Byte] )

  /**
    * Find most likely plaintext given ciphertext that is encrypted using
    * single character XOR cipher
    * TODO: finish
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
    * TODO: bytefy this!
    */
  def repeatKey(a: String, key: String): String =
    (key * (1 + (a.length / key.length))).take(a.length)

  /**
    * XOR plain string with repeated key (also plain string)
    * TODO: bytefy this!
    */
  def repeatingKeyXOR(a: String, key: String): Array[Byte] =
    fixedXOR(a.getBytes, repeatKey(a, key).getBytes)

  /**
    * TODO: split functionality further so that these two don't repeat themselves so much,
    * also use bytes instead of strings etc.
    */
  def findSingleCharacterXORkey(ciphertext: String, keyspace: String, metric: Metric[Char]): Char = {
    import CharacterHistogram._

    val candidates = keyspace map ( k => (k, new String(XOR.singleCharacterXOR(Tools.decodeHex(ciphertext), k.toByte), "UTF-8")))
    val distances = candidates map ( e => (e._1, metric.distance(makeCharacterHistogramMap(e._2), english)))
    distances.sortBy(_._2).head._1
  }

  /**
    * TODO: split functionality further so that these two don't repeat themselves so much,
    *  also use bytes instead of strings etc.
    */
  def breakSingleCharacterXOR(ciphertext: String, keyspace: String, metric: Metric[Char]): (String, Double) = {
    import CharacterHistogram._

    val candidates = keyspace map ( k => new String(XOR.singleCharacterXOR(Tools.decodeHex(ciphertext), k.toByte), "UTF-8"))
    val distances = candidates map ( txt => (txt, metric.distance(makeCharacterHistogramMap(txt), english)))
    distances.sortBy(_._2).head
  }
}
