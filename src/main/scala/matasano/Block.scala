package matasano

abstract class Block {

  def encrypt(pt: Array[Byte], iv: Array[Byte], key: Array[Byte], cipher: Cipher);

}

class CBC extends Block {

  import XOR.fixedXOR

  def encrypt(pt: Array[Byte], iv: Array[Byte], key: Array[Byte], cipher: Cipher) = {
    assert(iv.length == key.length)
    val padded = Block.pkcs7(pt, key.length)
    val blocks = padded.sliding(key.length, key.length).toArray
    blocks(0)
//    blocks.foldLeft(iv)(block, rest) => (cipher.encrypt(fixedXOR(block, rest), key))
  }
}


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
  /**
    * PKCS#7 Padding
    */
  def pkcs7(a: Array[Byte], s: Int): Array[Byte] = {
    val diff = s - a.length
    a ++  Array.fill(diff)(diff.toByte)
  }
}
