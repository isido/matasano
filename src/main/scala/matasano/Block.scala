package matasano

/**
  * Different operations for byte blocks
  */
object Block {

  /**
    * Make blocks of size s, discard the rest
    */
  def makeBlocks(a: Array[Byte], s: Int) = {
    val b = a.sliding(s, s).toArray
    if (b.last.length != s)
      b.take(b.length - 1)
    else
      b
  }
}
