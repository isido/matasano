package matasano

object Hamming {

  /**
    * Count number of set bits
    * This method expects "unsigned" int
    */
  def bits(a: Int): Int = {
    def bitsAcc(a: Int, acc: Int): Int =
      if (a == 0) acc
      else bitsAcc (a >> 1, (1 & a).toInt + acc)
  bitsAcc(a, 0)
  }

  /**
    * Count number of differing bits in two bytes
    * (a & 0xff) trickery is needed because JVM has no unsigned byte type,
    * it converts signed bytes into unsigned ints equivalent of unsigned bytes (argh)
    */
  def differingBits(a: Byte, b: Byte): Int = bits ((a & 0xff) ^ (b & 0xff))

  /**
    * Count hamming distance (number of different bits) in two byte arrays
    */
  def distance(a: Array[Byte], b: Array[Byte]): Int = {
    assert (a.length == b.length)
    val differing = 
      for {
        (x,y) <- a.zip(b)
      } yield differingBits(x, y)
    differing.sum
  }

}
