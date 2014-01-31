package matasano

/*
 * The Base64-encoded content at the following location:
 * 
 *     https://gist.github.com/3132853
 * 
 * Has been encrypted via AES-128 in ECB mode under the key
 * 
 *     "YELLOW SUBMARINE".
 * 
 * (I like "YELLOW SUBMARINE" because it's exactly 16 bytes long).
 * 
 * Decrypt it.
 * 
 * Easiest way:
 * 
 * Use OpenSSL::Cipher and give it AES-128-ECB as the cipher.
 */

object Challenge7 {

  import Tools._

  import javax.crypto.Cipher
  import javax.crypto.spec.SecretKeySpec

  import scala.io.Source

  def main(args: Array[String]) = {

    //val textBuffer = Source.fromURL("https://gist.github.com/3132853/download").getLines
    val textBuffer = Source.fromURL(getClass.getResource("/aes-ecb.txt")).getLines

    val bytes = decodeBase64(textBuffer.mkString)
    
    val key = new SecretKeySpec("YELLOW SUBMARINE".getBytes, "AES")

    val aes = Cipher.getInstance("AES/ECB/NoPadding")
    aes.init(Cipher.DECRYPT_MODE, key)
    val plain = aes.doFinal(bytes)

    val txt = new String(plain, "UTF-8")
    println(txt)

  }

}
