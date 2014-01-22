{-# LANGUAGE OverloadedStrings #-}

module Main (
  main
  ) where

import Test.Framework (defaultMain, testGroup)
import Test.Framework.Providers.QuickCheck2
import Test.Framework.Providers.HUnit

import Test.QuickCheck
import Test.HUnit

import Data.ByteString.Base16 (encode, decode)

import Matasano

main :: IO ()
main = defaultMain tests

--tests :: [Test]
tests = [
  testGroup "Challenge 1" [
     testCase "Hex to Base64" tcHexToBase64,
     testCase "Base64 to Hex" tcBase64ToHex
     ],
  testGroup "Challenge 2" [  
     testCase "Fixed XOR" tcFixedXOR
     ] 
  ]

tcHexToBase64 = assertEqual "Should get correct encoding from hex to base64"
   (hexToBase64 "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")
   "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"

tcBase64ToHex = assertEqual "Should get correct encoding from base64 to hex"
 (base64ToHex "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t")
 "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"

tcFixedXOR = assertEqual 
           "Should get correct XOR between two hex encoded strings"
           (fixedXOR (fst $ decode "1c0111001f010100061a024b53535009181c") (fst $ decode "686974207468652062756c6c277320657965"))
           (fst $ decode "746865206b696420646f6e277420706c6179")