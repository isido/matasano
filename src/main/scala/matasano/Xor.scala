package matasano

object XOR {

  def FixedXOR(a: Array[Int], b: Array[Int]): Array[Int] =
    (a, b).zipped map (_ ^ _) // why does this not work with Bytes?

}
