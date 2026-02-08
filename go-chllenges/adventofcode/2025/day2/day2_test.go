package day2_test

import (
	"day2"
	"reflect"
	"testing"
)

var tt = []struct {
	in       string
	invalids []int64
}{
	{in: "11-22", invalids: []int64{11, 22}},
	{in: "95-115", invalids: []int64{99}},
	{in: "998-1012", invalids: []int64{1010}},
	{in: "1188511880-1188511890", invalids: []int64{1188511885}},

	{in: "222220-222224", invalids: []int64{222222}},

	{in: "1698522-1698528", invalids: []int64{}},

	{in: "446443-446449", invalids: []int64{446446}},

	{in: "38593856-38593862", invalids: []int64{38593859}},
}

func TestCheckValid(t *testing.T) {

	for i, tti := range tt {
		invals := day2.GetInvalidIds(tti.in)
		if len(invals) == 0 && len(tti.invalids) == 0 {
			continue
		}
		if !reflect.DeepEqual(tti.invalids, invals) {
			t.Errorf("error in item %d, expected invalids %v, got invalids %v", i, tti.invalids, invals)
		}
	}
}

func TestGetSumInvalidIds(t *testing.T) {
	var sum int64
	for _, tti := range tt {
		rowSum := day2.GetSumInvalidIds(tti.in)
		sum += rowSum
	}
	if sum != 1227775554 {
		t.Errorf("expected 1227775554, got %d", sum)
	}
}

func TestGetFullSumInvalidIds(t *testing.T) {
	s := day2.GetFullSumInvalidIds()
	if s != 35367539282 {
		t.Errorf("Expected 12200, got %v", s)
	}
}
