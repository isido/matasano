package matasano

object Hamming {

  /**
    * Count number of set bits
    */
  def bits(a: Int): Int = {
    def bitsAcc(a: Int, acc: Int): Int =
      if (a == 0) acc
      else bitsAcc (a >> 1, (1 & a).toInt + acc)
  bitsAcc(a, 0)
  }

  /**
    * Count number of differing bits in two bytes
    */
  def differingBits(a: Byte, b: Byte): Int = bits (a ^ b)

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
