{-# LANGUAGE OverloadedStrings #-}

module Matasano where
--
-- Matasano crypto challenges
--

import Data.Bits (xor)
import qualified Data.ByteString as B
import Data.ByteString.Char8 ()
import Data.ByteString.Base16 (encode, decode)
import Data.ByteString.Base64

-- Challenge 1 
hexToBase64 :: B.ByteString -> B.ByteString
hexToBase64 hStr = Data.ByteString.Base64.encode $ fst $ Data.ByteString.Base16.decode hStr

base64ToHex :: B.ByteString -> B.ByteString
base64ToHex b64Str = case Data.ByteString.Base64.decode b64Str of
                         Left msg -> error msg           
                         Right str -> Data.ByteString.Base16.encode str


-- Challenge 2
fixedXOR :: B.ByteString -> B.ByteString -> B.ByteString
fixedXOR a b = B.pack $ B.zipWith xor a b
