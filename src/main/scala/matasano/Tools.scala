package matasano

import org.apache.commons.codec.binary.{Base64, Hex}

/**
  * Conversions and other utility functions 
  */
object Tools {

  def encodeBase64(bytes: Array[Byte]): String = 
    new String(Base64.encodeBase64(bytes))
  
  def decodeBase64(str: String): Array[Byte] = 
    Base64.decodeBase64(str.getBytes)

  def encodeHex(bytes: Array[Byte]): String =
    Hex.encodeHex(bytes).mkString

  def decodeHex(str: String): Array[Byte] =
    str.getBytes //Hex.decodeHex(str.getBytes)

}
