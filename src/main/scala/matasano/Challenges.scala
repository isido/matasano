package matasano

object Challenges {

  // Challenge 3: Decrypt Single character XOR

  def singleCharacterXORCandidates = {
    val cipherText = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
    val keyRange = ('a' to 'z') ++ ('A' to 'Z')
    keyRange map ( k => new String(XOR.SingleCharacterXOR(Tools.decodeHex(cipherText), k.toByte), "UTF-8"))
  }

}
