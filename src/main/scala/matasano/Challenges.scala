package matasano

import CharacterHistogram._

object Challenges {

  // Challenge 3: Decrypt Single character XOR

  def singleCharacterXORCandidates = {
    val cipherText = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
    val keyRange = ('a' to 'z') ++ ('A' to 'Z')
    val candidates = keyRange map ( k => new String(XOR.SingleCharacterXOR(Tools.decodeHex(cipherText), k.toByte), "UTF-8"))
//    val distances = candidates map ( txt => new ChiSquare.distance(makeCharacterHistogram(txt), english))
  }

}
