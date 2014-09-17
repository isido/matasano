package matasano

abstract class Block {
  val mode : Symbol
  def encrypt(pt: Array[Byte], iv: Array[Byte], key: Array[Byte], cipher: Cipher): Array[Byte] = pt
  def decrypt(pt: Array[Byte], iv: Array[Byte], key: Array[Byte], cipher: Cipher): Array[Byte] = pt
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

  def pkcs7Pad(a: Array[Byte], blocksize: Int): Array[Byte] = {
    assert(blocksize < 256 && blocksize > 0)
    val padding = 
      if (a.length < blocksize)
        blocksize - a.length
      else
        a.length % blocksize

    if (padding != 0)
      a ++  Array.fill(padding)(padding.toByte)
    else
      a ++ Array.fill(blocksize)(blocksize.toByte)
  }

  def randomBytes(s: Int): Array[Byte] = {
    var b = Array.fill[Byte](s)(0)
    new scala.util.Random().nextBytes(b)
    b
  }

  def encryptionOracle(pt: Array[Byte], blocksize: Int) = {

    def padding = {
      val i = (((pt.size / blocksize + 1) * blocksize) - pt.size)
      val n1 = i / 2
      val n2 = 
        if ((i % 2) == 0)
          n1
        else
          n1 + 1
      (n1, n2)
    }

    assert (blocksize % 2 == 0)
    val (n1, n2) = padding
    val modes = Array(new ECB, new CBC)
    val mode = modes(new scala.util.Random().nextInt(modes.length))
    println(mode.mode)
    val before = Array.fill(n1)(0.toByte)
    val after = Array.fill(n2)(0.toByte)
    val tampered = before ++ pt ++ after
    val key = randomBytes(blocksize)
    val ct = mode.encrypt(tampered, key, key, new AES)
    ct
  }
}

class ECB extends Block {
  override val mode = 'ECB
  override def encrypt(pt: Array[Byte], iv: Array[Byte], key: Array[Byte], cipher: Cipher) = {
    val padded = Block.pkcs7Pad(pt, key.length)
    val blocks = padded.sliding(key.length, key.length).toArray
    val out = blocks.map( block => cipher.encrypt(block, key) )
    out.flatten
  }
  override def decrypt(pt: Array[Byte], iv: Array[Byte], key: Array[Byte], cipher: Cipher) = {
    pt // not implemented
  }
}

class CBC extends Block {

  import XOR.fixedXOR
  import Cipher.CipherOp

  override val mode = 'CBC

  override def encrypt(pt: Array[Byte], iv: Array[Byte], key: Array[Byte], cipher: Cipher) = {
    assert(iv.length == key.length)
    val padded = Block.pkcs7Pad(pt, key.length)
    val blocks = padded.sliding(key.length, key.length).toArray
    val out = blocks.foldLeft(Array(iv))((acc, cur) => acc :+ cipher.encrypt(fixedXOR(acc.last, cur), key))
    out.drop(1).flatten // drop the iv from the result
  }

  override def decrypt(ct: Array[Byte], iv: Array[Byte], key: Array[Byte], cipher: Cipher) = {
    assert(iv.length == key.length)
    assert(ct.length % key.length == 0)
    val blocks = iv +: ct.sliding(key.length, key.length).toArray // add iv as first "ct" block
    // TODO: make this more readable
    // tuple: (plaintext-block, previous-ct-block)
    val out = blocks.foldLeft(Array((Array[Byte](), iv)))((acc, cur) => acc :+ (fixedXOR(cipher.decrypt(cur, key), acc.last._2), cur))
    val res = out.map(_._1)
    res.drop(2).flatten // drop the fold-initial and iv from the result
    // TODO: remove padding
  }
}
