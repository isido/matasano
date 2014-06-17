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
    val keysizes = 2 to maxKeysize

    // b. Write a function to compute the edit distance/Hamming distance
    // between two strings

    // c. For each KEYSIZE, take the FIRST KEYSIZE worth of bytes, and the
    // SECOND KEYSIZE worth of bytes, and find the edit distance between
    // them. Normalize this result by dividing by KEYSIZE.

    val distances = for {
      i <- keysizes
      // this feels a bit hackish
      ed1 = distance(bytes.slice(0, i), bytes.slice(i * 2, i * 3))
      ed2 = distance(bytes.slice(i, i * 2), bytes.slice(i * 3, i * 4))
      ed3 = distance(bytes.slice(i * 4, i * 5), bytes.slice(i * 5, i * 6))
      ed = (ed1 + ed2 + ed3) / 3.0
      normEditDistance = ed / i.toDouble
    } yield (i, normEditDistance)

    // d. The KEYSIZE with the smallest normalized edit distance is probably
    // the key. You could proceed perhaps with the smallest 2-3 KEYSIZE
    // values. Or take 4 KEYSIZE blocks instead of 2 and average the
    // distances.

    val sorted = distances.sortBy( { case (ks, d) => d} )
    val (ks, i) = sorted.head

    // e. Now that you probably know the KEYSIZE: break the ciphertext into
    // blocks of KEYSIZE length.

    val blocks = bytes.sliding(ks, ks).toArray

    // f. Now transpose the blocks: make a block that is the first byte of
    // every block, and a block that is the second byte of every block, and
    // so on.

    // drop last block, if the division is not even

    val transposed = 
      if (blocks.last.length != ks)
        blocks.take(blocks.length - 1).transpose
      else
        blocks.transpose

    // g. Solve each block as if it was single-character XOR. You already
    // have code to do this.

    // try most printable ascii characters (scala typing required some trickery)
    val keyspace: Array[Byte] = for { i <- (32 to 127).toArray } yield i.toByte
    val key = for {
      block <- transposed
      (k, _) = XOR.singleCharacterKeyCandidates(block, keyspace).head
    } yield k

    // e. For each block, the single-byte XOR key that produces the best
    // looking histogram is the repeating-key XOR key byte for that
    // block. Put them together and you have the key.

    println("Key: " + new String(key))
    println("Plaintext: " +  new String(XOR.repeatingKey(bytes, key)))

    val newKey = key.slice(0, key.length - 1) ++ Array('e'.toByte)
    println("Corrected key: " + new String(newKey))
    println("Corrected plaintext: " + new String(XOR.repeatingKey(bytes, newKey)))
 
  }
}

