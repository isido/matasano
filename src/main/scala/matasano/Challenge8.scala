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
    val basis = blocks(0) // TODO: fix this
    val distances = blocks.map { b => 0 }.sum // figure out why this isn't working
    distances / blocks.length.toDouble
  }

  def main(args: Array[String]) = {

    val cipherTexts = 
      for {
        line <- Source.fromURL(getClass.getResource("/detect-aes-ecb.txt")).getLines
      } yield decodeHex(line)

    val cipherTexts2: Array[Array[Byte]] = cipherTexts.toArray
    
    val txt1 = cipherTexts2(0)
    println("t" + txt1.toSeq)
    val bl1 = txt1.slice(0,16)
    val bl2 = txt1.slice(16,32)

    println("b1" + bl1.toSeq)
    println("b2" + bl2.toSeq)
    println("b1l" + bl1.length)
    println("b2l" + bl2.length)
    val d = distance(bl1, bl2)

    println(d)
     
    val index = for {
      ct <- cipherTexts
    } yield hammingByBlock(ct, 16)

    println(index.toArray.mkString)
    
  }
}
