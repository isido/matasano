package matasano

import org.scalatest.FunSuite


class ToolsSuite extends FunSuite {

  val hexAndBytes =
    Array(
      ("", Array[Byte]()),
      ("00", Array[Byte](0)),
      ("FF", Array[Byte](-1)),
      ("ff", Array[Byte](-1))
    )

  val Base64AndBytes =
    Array(
      ("", Array[Byte]()),
      ("TWFu", Array[Byte](77, 97, 110))
    )

  val hexAndBase64 = 
    Array(
      ( "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d",
        "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"),
      ("", "")
    )

  val stringAndBase64 =
    Array(
      ("", ""),
      ("a", "YQ=="),
      ("A ", "QSA="),
      ("any carnal pleasure.", "YW55IGNhcm5hbCBwbGVhc3VyZS4="),
      ("any carnal pleasure", "YW55IGNhcm5hbCBwbGVhc3VyZQ=="),
      ("any carnal pleasur", "YW55IGNhcm5hbCBwbGVhc3Vy"),
      ("any carnal pleasu", "YW55IGNhcm5hbCBwbGVhc3U="),
      ("any carnal pleas", "YW55IGNhcm5hbCBwbGVhcw=="),
      ("pleasure.", "cGxlYXN1cmUu"),
      ("leasure.", "bGVhc3VyZS4="),
      ("easure.", "ZWFzdXJlLg=="),
      ("asure.", "YXN1cmUu"),
      ("sure.", "c3VyZS4="),
      ("Man is distinguished, not only by his reason, but by this singular passion from other animals, which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation of knowledge, exceeds the short vehemence of any carnal pleasure.",
        "TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlzIHNpbmd1bGFyIHBhc3Npb24gZnJvbSBvdGhlciBhbmltYWxzLCB3aGljaCBpcyBhIGx1c3Qgb2YgdGhlIG1pbmQsIHRoYXQgYnkgYSBwZXJzZXZlcmFuY2Ugb2YgZGVsaWdodCBpbiB0aGUgY29udGludWVkIGFuZCBpbmRlZmF0aWdhYmxlIGdlbmVyYXRpb24gb2Yga25vd2xlZGdlLCBleGNlZWRzIHRoZSBzaG9ydCB2ZWhlbWVuY2Ugb2YgYW55IGNhcm5hbCBwbGVhc3VyZS4=")
    )

  test("decoding hex to bytes") {
    for ((hex, bytes) <- hexAndBytes)
      assert(Tools.decodeHex(hex) === bytes)
  }

  test("encoding bytes to hex") {
    for ((hex, bytes) <- hexAndBytes)
      assert(Tools.encodeHex(bytes) === hex.toLowerCase)
  }

  test("decoding and encoding hex") {
    for ((hex, _) <- hexAndBytes)
      assert(Tools.encodeHex(Tools.decodeHex(hex)) === hex.toLowerCase)
  }

  test("encoding and decoding bytes") {
    for ((_, bytes) <- hexAndBytes)
      assert(Tools.decodeHex(Tools.encodeHex(bytes)) === bytes)
  }

  test("decoding hex and encoding it to base64 (Challenge #2)") {
    for ((hex, base64) <- hexAndBase64)
      assert(Tools.encodeBase64(Tools.decodeHex(hex)) === base64)
  }

  test("encoding bytes to base64") {
    for ((base64, bytes) <- Base64AndBytes)
      assert(Tools.encodeBase64(bytes) === base64)
  }

  test("decoding base64 to bytes") {
    for ((base64, bytes) <- Base64AndBytes)
      assert(Tools.decodeBase64(base64) === bytes)
  }

  test("encoding string to base64") {
    for ((str, base64) <- stringAndBase64)
      assert(Tools.encodeBase64(str.getBytes("UTF-8")) === base64)
  }

  test("decoding base64 to string") {
    for ((str, base64) <- stringAndBase64)
      assert(new String(Tools.decodeBase64(base64), "UTF-8") === str)
  }

}
