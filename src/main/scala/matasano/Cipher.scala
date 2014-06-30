package matasano

object Cipher {
  type CipherOp = (Array[Byte], Array[Byte]) => Array[Byte]
}

abstract class Cipher {

  def decrypt(bytes: Array[Byte], key: Array[Byte]): Array[Byte];
  def encrypt(bytes: Array[Byte], key: Array[Byte]): Array[Byte];
}

class AES extends Cipher {

  import javax.crypto.Cipher
  import javax.crypto.spec.SecretKeySpec

  def encrypt(bytes: Array[Byte], key: Array[Byte]): Array[Byte] =
    crypt(bytes, key, javax.crypto.Cipher.ENCRYPT_MODE)

  def decrypt(bytes: Array[Byte], key: Array[Byte]): Array[Byte] =
    crypt(bytes, key, javax.crypto.Cipher.DECRYPT_MODE)

  def crypt(bytes: Array[Byte], key: Array[Byte], mode: Int): Array[Byte] = {
    val engine = javax.crypto.Cipher.getInstance("AES/ECB/NoPadding")
    val k = new SecretKeySpec(key, "AES")
    engine.init(mode, k)
    engine.doFinal(bytes)
  }
}

