// Tests for matasano

package matasano

import (
	"bytes"
	"encoding/hex"
	"testing"
)

//
// tests for task 1
//

var encodingTests = []struct {
	hex string
	b64 string
}{
	{"", ""},
	{"49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d",
		"SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"},
}

func TestHexToBase64(t *testing.T) {
	for _, tt := range encodingTests {
		res, err := HexToBase64(tt.hex)
		if res != tt.b64 {
			t.Errorf("HexToBase64(%v) = %v, want %v", tt.hex, res, tt.b64)
		}
		if err != nil {
			t.Errorf("HexToBase64(%v), Error %s", tt.hex, err)
		}
	}
}

func TestBase64ToHex(t *testing.T) {
	for _, tt := range encodingTests {
		res, err := Base64ToHex(tt.b64)
		if res != tt.hex {
			t.Errorf("Base64ToHex(%v) = %v, want %v", tt.b64, res, tt.hex)
		}
		if err != nil {
			t.Errorf("Base64ToHex(%v), Error %s", tt.b64, err)
		}
	}
}

//
// tests for task 2
//

var fixedXORTests = []struct {
	a   string
	b   string
	res string
}{
	{"1c0111001f010100061a024b53535009181c",
		"686974207468652062756c6c277320657965",
		"746865206b696420646f6e277420706c6179"},
	{"ffff",
		"ffff",
		"0000"},
}

func TestFixedXOR(t *testing.T) {
	for _, tt := range fixedXORTests {
		a, _ := hex.DecodeString(tt.a)
		b, _ := hex.DecodeString(tt.b)
		r, _ := hex.DecodeString(tt.res)
		res := FixedXOR(a, b)
		if !bytes.Equal(res, r) {
			t.Errorf("FixedXOR(%v, %v) = %v, want %v", tt.a, tt.b, hex.EncodeToString(res), tt.res)
		}
	}
}

//
// tests for task 3
//

var cipher = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
