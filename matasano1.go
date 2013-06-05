// Matasano crypto challenge
// Functions needed for the first part

package matasano

import (
	"encoding/base64"
	"encoding/hex"
	"fmt"
)

// Convert hex string to base64 encoding
func HexToBase64(s string) string {
	bytes, err := hex.DecodeString(s)
	if err != nil {
		fmt.Println("error: ", err)
		return ""
	}
	str := base64.StdEncoding.EncodeToString(bytes)
	return str
}


// Convert base64 encoded string to hex representation
func Base64ToHex(s string) string {
	return s
}



func base64ToBytes(s string) []byte {
	bytes, err := base64.StdEncoding.DecodeString(s)
	if err != nil {
		fmt.Println("error: ", err)
		return nil
	}
	return bytes
}

func hexToBytes(hex string) []byte {
	b := make([]byte, len(hex) / 2)
	return b
}
