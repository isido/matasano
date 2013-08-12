package matasano

import scala.io.Source

import CharacterHistogram._

object Challenges {

  // assume keys are ASCII letters
  val keyRange = ('a' to 'z') ++ ('A' to 'Z')

  // Challenge 3: single-character XOR Cipher

  def singleCharacterXOR = {
    val cipherText = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
    val candidates = keyRange map ( k => new String(XOR.SingleCharacterXOR(Tools.decodeHex(cipherText), k.toByte), "UTF-8"))
    val distances = candidates map ( txt => (txt, new CharacterHistogramChiSquare(txt).dist(CharacterHistogram.english)))
    distances.sortBy(_._2).head
  }

  // Challenge 4: Detect single-character XOR

  def detectSingleCharacterXOR = {
    val filename = "single-character-xor.txt"
    val candidates = Source.fromURL(getClass.getResource("/" + filename)).getLines
  }

}
