package matasano

import scala.io.Source

import CharacterHistogram._

object Challenges {

  // assume keys are ASCII letters
  val keyRange = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')

  // Challenge 3: single-character XOR Cipher
  val challenge3cipherText = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"

  def singleCharacterXOR(ciphertext: String) = {
    val candidates = XOR.xorPlaintextCandidates(ciphertext, keyRange.toList)
    val metric = new ChiSquare[Char]
    val distances = candidates map (txt => 
      (txt, metric.distance(CharacterHistogram.makeCharacterHistogramMap(txt),
        CharacterHistogram.english)))

    distances.sortBy(_._2).head
  }
 
  // Challenge 4: Detect single-character XOR

  def detectSingleCharacterXOR = {
    val filename = "single-character-xor.txt"
    val cipherTexts = Source.fromURL(getClass.getResource("/" + filename)).getLines
    val distances = cipherTexts map ( txt => singleCharacterXOR(txt))
    distances.toList.sortBy(_._2).head._1
  }

}
