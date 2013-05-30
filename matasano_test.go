// Tests for matasano

package matasano

import "testing"

func TestHexToBase64(t *testing.T) {
	const in = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
	const out = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"

	if x := hexToBase64(in); x != out {
		t.Errorf("HexToBase64(%v) = %v, want %v", in, x, out)
	}
}

func TestBase64ToHex(t *testing.T) {

	const in = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
	const out = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"

	if x := base64ToHex(in); x != out {
		t.Errorf("Base64ToHex(%v) = %v, want %v", in, x, out)
	}
}
