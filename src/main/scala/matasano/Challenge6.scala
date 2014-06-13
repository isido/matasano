package matasano

/*
 *  Break repeating-key XOR
 * 
 * The buffer at the following location:
 * 
 * https://gist.github.com/3132752
 * is base64-encoded repeating-key XOR. Break it.
 */

object Challenge6 {

  import Tools._
  import Hamming._
  import XOR._

  import scala.io.Source

  def main(args: Array[String]) = {

    //val textBuffer = Source.fromURL("https://gist.github.com/3132752").getLines
    val textBuffer = Source.fromURL(getClass.getResource("/repeating-key-xor.txt")).getLines
    val bytes = decodeBase64(textBuffer.mkString)
    
    //  a. Let KEYSIZE be the guessed length of the key; try values from 2 to
    // (say) 40.

    val maxKeysize = 40
    val keysize = 2 to maxKeysize

    // b. Write a function to compute the edit distance/Hamming distance
    // between two strings

    // c. For each KEYSIZE, take the FIRST KEYSIZE worth of bytes, and the
    // SECOND KEYSIZE worth of bytes, and find the edit distance between
    // them. Normalize this result by dividing by KEYSIZE.

    val distances = for {
      i <- keysize
      editDistance = distance(bytes.slice(0, i), bytes.slice(i, i * 2))
      normEditDistance = editDistance / i.toDouble
    } yield (i, normEditDistance)

    // d. The KEYSIZE with the smallest normalized edit distance is probably
    // the key. You could proceed perhaps with the smallest 2-3 KEYSIZE
    // values. Or take 4 KEYSIZE blocks instead of 2 and average the
    // distances.

    val k = distances.sortBy(_._2).head._1

    // e. Now that you probably know the KEYSIZE: break the ciphertext into
    // blocks of KEYSIZE length.

    val blocks = bytes.sliding(k, k).toArray

    // f. Now transpose the blocks: make a block that is the first byte of
    // every block, and a block that is the second byte of every block, and
    // so on.

    // drop last block, if the division is not even

    val transposed = 
      if (blocks.last.length != k)
        blocks.take(blocks.length - 1).transpose
      else
        blocks.transpose

    // g. Solve each block as if it was single-character XOR. You already
    // have code to do this.

    import CharacterHistogram._
    val keyCandidates = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).mkString

    //val keyChars = transposed map (block => XOR.findSingleCharacterXORkey(block, keyCandidates, new ChiSquare[Char]))


    // e. For each block, the single-byte XOR key that produces the best
    // looking histogram is the repeating-key XOR key byte for that
    // block. Put them together and you have the key.
    
  }
}

