package matasano

import scala.language.implicitConversions

import org.apache.commons.codec.binary.{Base64, Hex}

/**
  * Conversions and other utility functions 
  */
object Conversions {
  /**
    * Encode byte array to Base64 string
    */
  def encodeBase64(bytes: Array[Byte]): String = 
    new String(Base64.encodeBase64(bytes))
  
  /**
    * Decode Base64 string to byte array
    */
  def decodeBase64(s: String): Array[Byte] = 
    Base64.decodeBase64(s.getBytes("UTF-8"))

  /**
    * Encode byte array to hex string
    */
  def encodeHex(bytes: Array[Byte]): String =
    Hex.encodeHexString(bytes)

  /**
    * Decode hex string to byte array
    */
  def decodeHex(s: String): Array[Byte] =
    Hex.decodeHex(s.toLowerCase.toArray.map(i => i))

  /**
    * Conversion to bytes
    * TODO: is this really necessary, or should we be explicit everywhere
    */
  implicit def CharToBytes(c: Char) : Byte = c.toByte

}

