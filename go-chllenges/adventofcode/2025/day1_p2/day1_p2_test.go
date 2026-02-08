package day1p2_test

import (
	"day1p2"
	"testing"
)

type ttm struct {
	i    int
	inc  int
	exp  int
	expz int
}

func TestRotateLeft(t *testing.T) {
	tt := []ttm{
		{i: 50, inc: 68, exp: 82, expz: 1},
		{i: 82, inc: 30, exp: 52, expz: 0},
		{i: 0, inc: 5, exp: 95, expz: 0},
		{i: 55, inc: 55, exp: 0, expz: 1},
		{i: 0, inc: 1, exp: 99, expz: 0},
		{i: 99, inc: 99, exp: 0, expz: 1},
		{i: 14, inc: 82, exp: 32, expz: 1},
	}
	for i, tc := range tt {
		got, gotz := day1p2.RotateLeft(tc.i, tc.inc)
		if got != tc.exp {
			t.Errorf(" item %d Expected %v, got %v", i, tc.exp, got)
		}
		if gotz != tc.expz {
			t.Errorf(" item %d Expected number of zero %v, got %v", i, tc.expz, gotz)
		}
	}

}

func TestRotateRight(t *testing.T) {
	tt := []ttm{
		{i: 52, inc: 48, exp: 0, expz: 1},
		{i: 95, inc: 60, exp: 55, expz: 1},
		{i: 0, inc: 14, exp: 14, expz: 0},
	}
	for i, tc := range tt {
		got, gotz := day1p2.RotateRight(tc.i, tc.inc)
		if got != tc.exp {
			t.Errorf("Faield item %d Expected %v, got %v", i, tc.exp, got)
		}
		if gotz != tc.expz {
			t.Errorf(" item %d Expected number of zero %v, got %v", i, tc.expz, gotz)
		}
	}

}
func TestGetPass(t *testing.T) {
	seq := day1p2.GetInputRecords()
	got := day1p2.GetPass(50, seq)
	if got != 6166 {
		t.Fatalf("expected 1034, got %d", got)
	}
}
