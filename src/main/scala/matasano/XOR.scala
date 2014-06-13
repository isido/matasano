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
    * XOR byte array with repeated key
    */
  def repeatingKey(a: Array[Byte], key: Array[Byte]) =
    fixedXOR(a, repeatKey(a, key))

  /**
    * TODO: split functionality, also use bytes instead of strings etc.
    */
  def breakSingleCharacterXOR(ciphertext: String, keyspace: String, metric: Metric[Char]): (String, Double) = {
    import CharacterHistogram._

    val candidates = keyspace map ( k => new String(XOR.singleCharacterXOR(Tools.decodeHex(ciphertext), k.toByte), "UTF-8"))
    val distances = candidates map ( txt => (txt, metric.distance(makeCharacterHistogramMap(txt), english)))
    distances.sortBy(_._2).head
  }

  /**
    * Construct all plaintext candidates using single character XOR using given keyspace
    */
  def singleCharacterCandidates(cipher: Array[Byte], keyspace: Array[Byte]) =
    keyspace map ( k => (k, singleCharacterXOR(cipher, k)))

}
