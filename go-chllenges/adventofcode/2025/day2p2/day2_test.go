package day2p2_test

import (
	day2p2 "day2"
	"reflect"
	"testing"
)

var tt = []struct {
	in       string
	invalids []int64
}{
	{in: "11-22", invalids: []int64{11, 22}},
	{in: "95-115", invalids: []int64{99, 111}},
	{in: "998-1012", invalids: []int64{999, 1010}},
	{in: "1188511880-1188511890", invalids: []int64{1188511885}},

	{in: "222220-222224", invalids: []int64{222222}},

	{in: "1698522-1698528", invalids: []int64{}},

	{in: "446443-446449", invalids: []int64{446446}},

	{in: "38593856-38593862", invalids: []int64{38593859}},
	{in: "565653-565659", invalids: []int64{565656}},
	{in: "824824821-824824827", invalids: []int64{824824824}},
	{in: "2121212118-2121212124", invalids: []int64{2121212121}},
}

func TestCheckValid(t *testing.T) {

	for i, tti := range tt {
		invals := day2p2.GetInvalidIds(tti.in)
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
		rowSum := day2p2.GetSumInvalidIds(tti.in)
		sum += rowSum
	}
	if sum != 4174379265 {
		t.Errorf("expected 1227775554, got %d", sum)
	}
}

func TestGetFullSumInvalidIds(t *testing.T) {
	s := day2p2.GetFullSumInvalidIds()
	if s != 45814076230 {
		t.Errorf("Expected 12200, got %v", s)
	}
}
