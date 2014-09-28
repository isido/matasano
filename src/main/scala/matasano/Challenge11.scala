package matasano

/*
 * An ECB/CBC detection oracle
 * Now that you have ECB and CBC working:
 * 
 * Write a function to generate a random AES key; that's just 16 random bytes.
 * 
 * Write a function that encrypts data under an unknown key --- that
 * is, a function that generates a random key and encrypts under it.
 * 
 * The function should look like:
 * 
 * encryption_oracle(your-input) => [MEANINGLESS JIBBER JABBER] Under
 * the hood, have the function append 5-10 bytes (count chosen
 * randomly) before the plaintext and 5-10 bytes after the plaintext.
 * 
 * Now, have the function choose to encrypt under ECB 1/2 the time,
 * and under CBC the other half (just use random IVs each time for
 * CBC). Use rand(2) to decide which to use.
 * 
 * Detect the block cipher mode the function is using each time. You
 * should end up with a piece of code that, pointed at a block box
 * that might be encrypting ECB or CBC, tells you which one is
 * happening.
 */

object Challenge11 {

  import Block._
  import Hamming._

  /**
    * Probably not quite what was intended, but seems to work,
    * not sure why to add bytes to the plaintext 
    */

  def encryptionOracle(pt: Array[Byte], blocksize: Int, cipher: Block) = {

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
    val before = Array.fill(n1)(0.toByte)
    val after = Array.fill(n2)(0.toByte)
    val tampered = before ++ pt ++ after
    val key = randomBytes(blocksize)
    val iv = randomBytes(blocksize)
    val ct = cipher.encrypt(tampered, key, iv, new AES)
    val blocks = makeBlocks(ct, blocksize)
    val blockSimilarity = blocks.map( e => blocks.foldLeft(0)((acc, cur) => acc + distance(e, cur))).sum / 2

    if (blockSimilarity < (tampered.size * 8)) // TODO: get some real estimate
      'ECB
    else
      'CBC
  }

  def main(args: Array[String]) = {
    val pt = Array.fill(512)(0.toByte)
    val bs = 16
    val ciphers = Array(new ECB, new CBC)
    val cipher = ciphers(new scala.util.Random().nextInt(ciphers.length))

    println("Selected mode ", cipher.mode)
    println("Detected mode ", encryptionOracle(pt, bs, cipher))
  }
}
