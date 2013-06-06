// Matasano crypto challenge
// Functions needed for the first part

package matasano

import (
	"encoding/base64"
	"encoding/hex"
	"fmt"
	"unicode/utf8"
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
	bytes, err := base64.StdEncoding.DecodeString(s)
	if err != nil {
		fmt.Println("error: ", err)
		return ""
	}
	str := hex.EncodeToString(bytes)
	return str
}

// XOR two byte slices
func FixedXOR(a, b []byte) []byte {
	if len(a) != len(b) {
		fmt.Println("Slices aren't the same length (%v != %v)", len(a), len(b))
		return nil
	}
	res := make([]byte, len(a))
	for i := range a {
		res[i] = a[i] ^ b[i]
	}
	return res
}

// expand one byte to l sized byte-slice
func expandByte(b byte, l int) []byte {
	
	res := make([]byte, l)
	for i := range res {
		res[i] = b
	}
	return res
}

// convert rune to a byte, discard extra bytes
func convertRune(c rune) byte {
	l := utf8.RuneLen(c)
	if l != 1 {
		fmt.Println("Warning: discarding extra %d bytes in character", l - 1)
	}
	buf := make([]byte, l)
	buf = c
	return buf[0]
}

// Decrypt ciphertext encrypted with single characeter XOR
func SingleCharacterXORCipher(cipher, keyspace string) {

	cipherBytes, err := hex.DecodeString(cipher)

	if err != nil {
		fmt.Println("error: ", err)
		return
	}
	cipherLen := len(cipherBytes)

	// loop over letters and compute character histogram for decrypted text
	for _, c := range keyspace {
		// multiply the key
		b := convertRune(c) 
		key := expandByte(b, cipherLen)
		res := FixedXOR(cipherBytes, key)
		
	}

	// sort histograms accoring to distance to the target histogram
}

// 
func Solve3() {
	cipher := "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
	keyspace := "abcdefghijklmnopqrtstuvwxyzABCDEFGHIJKLMNOPQRSTUVWZYX"

	englishLetterFrequency := map[string]float64 {
		"e" : 12.02,
		"t" : 9.10,
		"a" : 8.12,
		"o" : 7.68,
		"i" : 7.31,
		"n" : 6.95,
		"s" : 6.28,
		"r" : 6.02,
		"h" : 5.92,
		"d" : 4.32,
		"l" : 3.98,
		"u" : 2.88,
		"c" : 2.71,
		"m" : 2.61,
		"f" : 2.30,
		"y" : 2.11,
		"w" : 2.09,
		"g" : 2.03,
		"p" : 1.82,
		"b" : 1.49,
		"v" : 1.11,
		"k" : 0.69,
		"x" : 0.17,
		"q" : 0.11,
		"j" : 0.10,
		"z" : 0.07, 
	}


	SingleCharacterXORCipher(cipher, keyspace)
}