module Main (
  main
  ) where

import Test.Framework (defaultMain, testGroup)
import Test.Framework.Providers.QuickCheck2
import Test.Framework.Providers.HUnit

import Test.QuickCheck
import Test.HUnit

import Matasano

main :: IO ()
main = defaultMain tests

--tests :: [Test]
tests = [
  testGroup "Challenge 1" [
     testCase "Hex to Base64" tcHexToBase64,
     testCase "Base64 to Hex" tcBase64ToHex
     ] 
  ]

tcHexToBase64 = assertEqual "x"
   (hexToBase64 "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")
   "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"

tcBase64ToHex = assertEqual "x"
 (base64ToHex "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t")
 "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
