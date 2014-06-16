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
    * Construct all plaintext candidates using single character XOR using given keyspace
    */
  def singleCharacterCandidates(cipher: Array[Byte], keyspace: Array[Byte]) =
    keyspace map ( k => (k, singleCharacterXOR(cipher, k)))

  /**
    * Convenience function: 
    * Find single character XOR key using "reasonable" defaults 
    *  (character frequency: english, keyspace: ascii + numbers)
    */
  def singleCharacterKeyCandidates(cipher: Array[Byte], keyspace: Array[Byte]) = {
    import CharacterHistogram.scoreEnglish

    val candidates = XOR.singleCharacterCandidates(cipher, keyspace)
    val scores = candidates map ( { case (k, c) => (k, scoreEnglish(new String(c))) } )
    scores.sortBy( { case (k, d) => d } )
  }
}
