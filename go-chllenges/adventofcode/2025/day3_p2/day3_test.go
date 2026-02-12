package day3p2_test

import (
	day3p2 "day3"
	"testing"
)

var tt = []struct {
	row string
	exp int
}{
	{row: "987654321111111", exp: 987654321111},
	{row: "811111111111119", exp: 811111111119},
	{row: "234234234234278", exp: 434234234278},
	{row: "818181911112111", exp: 888911112111},
}

func TestFindRowJoltage(t *testing.T) {
	for i, ti := range tt {
		got := day3p2.FindRowJoltage(ti.row)
		if got != ti.exp {
			t.Errorf("at item %d, expected %d, got %d", i, ti.exp, got)
		}
	}
}
func TestFindRowJoltageTotal(t *testing.T) {
	got := day3p2.FindRowJoltageTotal()
	if got != 171990312704598 {
		t.Errorf(" expected %d, got %d", 17405, got)
	}
}
