package matasano

/*
 * 
 *  Implement CBC Mode
 * 
 * In CBC mode, each ciphertext block is added to the next plaintext
 * block before the next call to the cipher core.
 * 
 * The first plaintext block, which has no associated previous ciphertext
 * block, is added to a "fake 0th ciphertext block" called the IV.
 * 
 * Implement CBC mode by hand by taking the ECB function you just wrote,
 * making it encrypt instead of decrypt (verify this by decrypting
 * whatever you encrypt to test), and using your XOR function from
 * previous exercise.
 * 
 * DO NOT CHEAT AND USE OPENSSL TO DO CBC MODE, EVEN TO VERIFY YOUR
 * RESULTS. What's the point of even doing this stuff if you aren't going
 * to learn from it?
 * 
 * The buffer at:
 * 
 *     https://gist.github.com/3132976
 * 
 * is intelligible (somewhat) when CBC decrypted against "YELLOW
 * SUBMARINE" with an IV of all ASCII 0 (\x00\x00\x00 &c)
 */

object Challenge10 {

  import scala.io.Source
  import Cipher._
  import Block._
  import Conversions._

  def main(args: Array[String]) = {

    val filename = "cbc.txt" // contents of url are copied here
    val base64 = Source.fromURL(getClass.getResource("/" + filename)).getLines.mkString
    val ct = decodeBase64(base64)
    val key = "YELLOW SUBMARINE".getBytes
    val iv = Array[Byte](0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
    val cipher = new AES
    val blockmode = new CBC
    val pt = blockmode.decrypt(ct, iv, key, cipher)
    println(new String(pt))
  }

}
