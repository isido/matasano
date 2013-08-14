package matasano

object CharacterHistogram {

  val english = Map[Char, Double] (
    'e' -> 12.02,
    't' -> 9.10,
    'a' -> 8.12,
    'o' -> 7.68,
    'i' -> 7.31,
    'n' -> 6.95,
    's' -> 6.28,
    'r' -> 6.02,
    'h' -> 5.92,
    'd' -> 4.32,
    'l' -> 3.98,
    'u' -> 2.88,
    'c' -> 2.71,
    'm' -> 2.61,
    'f' -> 2.30,
    'y' -> 2.11,
    'w' -> 2.09,
    'g' -> 2.03,
    'p' -> 1.82,
    'b' -> 1.49,
    'v' -> 1.11,
    'k' -> 0.69,
    'x' -> 0.17,
    'q' -> 0.11,
    'j' -> 0.10,
    'z' -> 0.07
  )

  /**
    * Count characters in given string, results are stored in a map
    */
  def characterCount(s: String): Map[Char, Int] = 
    s.foldLeft(Map[Char, Int]() withDefaultValue 0)((hist, ch) => hist.updated(ch, hist(ch) + 1))

  /**
    * Normalize character count between 0-100
    */
  def normalizeCount(m: Map[Char, Int], c: Int): Map[Char, Double] =
    m map { case(ch, v) => (ch -> v * 100.0 / c) }

  /**
    * Make normalized character histogram map
    */
  def makeCharacterHistogramMap(s: String): Map[Char, Double] =
    normalizeCount(characterCount(s), s.length)

}
