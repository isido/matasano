// Matasano crypto challenge
// Functions needed for the first part

package matasano

import (
	"encoding/base64"
	"encoding/hex"
	"fmt"
)

// Convert hex string to base64 encoding
// (Challenge 1)
func HexToBase64(s string) (string, error) {
	bytes, err := hex.DecodeString(s)
	str := base64.StdEncoding.EncodeToString(bytes)
	return str, err
}

// Convert base64 encoded string to hex representation
// (Challenge 1)
func Base64ToHex(s string) (string, error) {
	bytes, err := base64.StdEncoding.DecodeString(s)
	str := hex.EncodeToString(bytes)
	return str, err
}

// XOR two byte slices
// (Challenge 2)
func FixedXOR(a, b []byte) []byte {
	if len(a) != len(b) {
		fmt.Println("FixedXOR: Slices aren't the same length (%v != %v)", len(a), len(b))
		return nil
	}
	res := make([]byte, len(a))
	for i := range a {
		res[i] = a[i] ^ b[i]
	}
	return res
}

// expand one byte to l sized byte-slice
func multiplyByte(b byte, l int) []byte {
	res := make([]byte, l)
	for i := range res {
		res[i] = b
	}
	return res
}

// types for character histograms and dissimilarity matrix
type CharacterHistogram map[rune]float64
type CharacterCount map[rune]int
type CharacterDissimilarityMatrix map[rune]map[rune]float64

// count different characters in a string
func NewCountCharacters(s string) CharacterCount {
	hgrm := make(CharacterCount)
	for _, c := range s {
		val := hgrm[c]
		hgrm[c] = val + 1
	}
	return hgrm
}

// make character histogram from a character count
func NewCharacterHistogram(hgrm CharacterCount) CharacterHistogram {
	occ := 0
	for c := range hgrm {
		occ = occ + hgrm[c]
	}
	norm := make(CharacterHistogram)
	for c := range hgrm {
		norm[c] = float64(hgrm[c]) / float64(occ)
	}
	return norm
}

// compute dissimilarity matrix between two character histograms
func NewCharacterDissimilarityMatrix(a, b CharacterHistogram) CharacterDissimilarityMatrix {
	diss := make(CharacterDissimilarityMatrix)
	for aa := range a {
		for bb := range b {
			diss[aa][bb] = 0
		}
	}

	return diss		
}

// Challenge 3
func Solve3() {
	cipherHex := "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
	keyspace := []byte("abcdefghijklmnopqrtstuvwxyzABCDEFGHIJKLMNOPQRSTUVWZYX")
	/*
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
	*/
	cipher, err := hex.DecodeString(cipherHex)

	if err != nil {
		fmt.Println("Solve3: error: ", err)
		return
	}

	if err != nil {
		fmt.Println("Solve3: error: ", err)
		return
	}

	l := len(cipher)

	for _, b := range keyspace {
		key := multiplyByte(b, l)
		res := FixedXOR(cipher, key)
		fmt.Println(string(res))
	}
}
