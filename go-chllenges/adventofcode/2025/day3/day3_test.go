package day3_test

import (
	"day3"
	"testing"
)

var tt = []struct {
	row string
	exp int
}{
	{row: "987654321111111", exp: 98},
	{row: "811111111111119", exp: 89},
	{row: "234234234234278", exp: 78},
	{row: "818181911112111", exp: 92},
}

func TestFindRowJoltage(t *testing.T) {
	for i, ti := range tt {
		got := day3.FindRowJoltage(ti.row)
		if got != ti.exp {
			t.Errorf("at item %d, expected %d, got %d", i, ti.exp, got)
		}
	}
}
func TestFindRowJoltageTotal(t *testing.T) {
	got := day3.FindRowJoltageTotal()
	if got != 17405 {
		t.Errorf(" expected %d, got %d", 17405, got)
	}
}
