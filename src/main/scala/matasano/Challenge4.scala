package matasano

/*
 * 4. Detect single-character XOR
 * 
 * One of the 60-character strings at:
 * 
 *   https://gist.github.com/3132713
 * 
 * has been encrypted by single-character XOR. Find it. (Your code from
 * #3 should help.)
 */

object Challenge4 {

  import scala.io.Source

  import CharacterHistogram._

  def main(args: Array[String]) = {

    import Conversions.decodeHex

    // assume keys are ASCII letters
    val keyspace = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).mkString.getBytes
    val filename = "single-character-xor.txt" // the contents of url are copied here
    val ctxts = Source.fromURL(getClass.getResource("/" + filename)).getLines

    val candidates = for {
      hexTxt <- ctxts
      txt = decodeHex(hexTxt)
      (k, d) <- XOR.singleCharacterKeyCandidates(txt, keyspace)
    } yield (txt, k, d)

    val sorted = candidates.toList.sortBy( { case (txt, k, d) => d } )
    val (ciphertext, key, _) = sorted.head

    println(new String(XOR.singleCharacterXOR(ciphertext, key)))
  }
}
