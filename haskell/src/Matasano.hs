{-# LANGUAGE OverloadedStrings #-}

module Matasano where
--
-- Matasano crypto challenges
--

import Data.ByteString
import Data.ByteString.Char8 ()
import Data.ByteString.Base16 (encode, decode)
import Data.ByteString.Base64

-- Challenge 1 
hexToBase64 :: ByteString -> ByteString
hexToBase64 hStr = Data.ByteString.Base64.encode $ fst (Data.ByteString.Base16.decode hStr)

base64ToHex :: ByteString -> ByteString
base64ToHex b64Str = case Data.ByteString.Base64.decode b64Str of
                         Left msg -> error "str"           
                         Right str -> Data.ByteString.Base16.encode str


