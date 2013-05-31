// Matasano crypto challenge
// Functions needed for the first part

package matasano

import (
	"encoding/base64"
	"fmt"
)

func hexToBase64(hex string) string {
	return hex
//	return base64.EncodeToString(hex)
}


func base64ToHex(s string) []byte {
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
