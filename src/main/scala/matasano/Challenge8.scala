package matasano
/*
 * Detecting ECB
 * 
 * 
 * At the following URL are a bunch of hex-encoded ciphertexts:
 * 
 * https://gist.github.com/3132928
 * 
 * One of them is ECB encrypted. Detect it.
 * 
 * Remember that the problem with ECB is that it is stateless and
 * deterministic; the same 16 byte plaintext block will always produce
 * the same 16 byte ciphertext.
 */

object Challenge8 {

  import scala.io.Source
  import Block._
  import Conversions._
  import Hamming._

  def hammingByBlock(bytes: Array[Byte], window: Int) = {
    val blocks = makeBlocks(bytes, window)
    blocks.map { basis => blocks.map { b => distance(basis, b) }.sum / blocks.length.toDouble }.sum / blocks.length.toDouble
  }

  def main(args: Array[String]) = {

    val cipherTexts = 
      (for {
        line <- Source.fromURL(getClass.getResource("/detect-aes-ecb.txt")).getLines
      } yield decodeHex(line)).toArray

    // blocksize might be a multiple of 8
    val blocksizes = Array(8, 16, 24, 32, 48, 52, 64)

    for (bs <- blocksizes) {

      val differences = for {
        ct <- cipherTexts
      } yield hammingByBlock(ct, bs)

      val sorted = differences.zipWithIndex.sortBy( { case (d, i) => d } )

      println("With blocksize " + bs)
      println(sorted.take(5).mkString("\t"))

      // text 132 seems to be the one
    }
  }
}
