package day1p2_test

import (
	"day1p2"
	"testing"
)

func TestRotateLeft(t *testing.T) {
	tt := []struct {
		i   int
		inc int
		exp int
	}{
		{50, 68, 82},
		{82, 30, 52},
	}
	for i, tc := range tt {
		got := day1p2.RotateLeft(tc.i, tc.inc)
		if got != tc.exp {
			t.Fatalf("Faield item %d Expected %v, got %v", i, tc.exp, got)
		}
	}

}

func TestRotateRight(t *testing.T) {
	tt := []struct {
		i   int
		inc int
		exp int
	}{
		{52, 48, 0},
		{95, 60, 55},
		{95, 60, 55},
		{0, 14, 14},
	}
	for i, tc := range tt {
		got := day1p2.RotateRight(tc.i, tc.inc)
		if got != tc.exp {
			t.Fatalf("Faield item %d Expected %v, got %v", i, tc.exp, got)
		}
	}

}
func TestGetPass(t *testing.T) {
	seq := day1p2.GetInputRecords()
	got := day1p2.GetPass(50, seq)
	if got != 1034 {
		t.Fatalf("expected 1034, go %d", got)
	}
}
