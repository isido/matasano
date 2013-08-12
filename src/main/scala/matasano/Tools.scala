package matasano

import org.apache.commons.codec.binary.{Base64, Hex}

/**
  * Conversions and other utility functions 
  */
object Tools {
  /**
    * Encode byte array to Base64 string
    */
  def encodeBase64(bytes: Array[Byte]): String = 
    new String(Base64.encodeBase64(bytes))
  
  /**
    * Decode Base64 string to byte array
    */
  def decodeBase64(str: String): Array[Byte] = 
    Base64.decodeBase64(str.getBytes("UTF-8"))

  /**
    * Encode byte array to hex string
    */
  def encodeHex(bytes: Array[Byte]): String =
    Hex.encodeHexString(bytes)

  /**
    * Decode hex string to byte array
    */
  def decodeHex(str: String): Array[Byte] =
    Hex.decodeHex(str.toLowerCase.toArray.map(i => i))

}
