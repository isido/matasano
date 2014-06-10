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

    // assume keys are ASCII letters
    val keyCandidates = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).mkString


    val filename = "single-character-xor.txt" // the contents of url are copied here
    val cipherTexts = Source.fromURL(getClass.getResource("/" + filename)).getLines

    val distances = cipherTexts map ( txt => XOR.breakSingleCharacterXOR(txt, 
      keyCandidates, new ChiSquare[Char]))

    val first = distances.toList.sortBy(_._2).head._1

    println(first)

  }
}
