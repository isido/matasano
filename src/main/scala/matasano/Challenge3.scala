package matasano


/*
 * Single-character XOR Cipher
 * 
 * The hex encoded string:
 * 
 *   1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736
 * 
 * ... has been XOR'd against a single character. Find the key, decrypt
 * the message.
 * 
 * Write code to do this for you. How? Devise some method for "scoring" a
 * piece of English plaintext. (Character frequency is a good metric.)
 * Evaluate each output and choose the one with the best score.
 * 
 * Tune your algorithm until this works.
 */

object Challenge3 {

  import CharacterHistogram._

  def main(args: Array[String]) = {

    // assume keys are ASCII letters
    val keyRange = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
    val cipher = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"

//    val plain = XOR.breakSingleCharacterXOR(cipher, keyRange, new ChiSquare[Char]) 


  }
}
