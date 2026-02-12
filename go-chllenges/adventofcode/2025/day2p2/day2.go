package day2p2

import (
	_ "embed"
	"strconv"
	"strings"
)

//go:embed input.txt
var input string

func GetFullSumInvalidIds() int64 {
	ranges := strings.Split(input, ",")
	var sum int64
	for _, r := range ranges {
		v := GetSumInvalidIds(r)
		sum += v
	}
	return sum
}

func GetSumInvalidIds(rangeStr string) int64 {
	arr := GetInvalidIds(rangeStr)
	if len(arr) == 0 {
		return 0
	}
	var sum int64
	for _, v := range arr {
		sum += v
	}
	return sum
}

func GetInvalidIds(rangeStr string) []int64 {
	a, b := getRange(rangeStr)
	duplicates := findDuplicatesInRange(a, b)
	return duplicates
}

func getRange(rangeStr string) (int, int) {
	arr := strings.Split(rangeStr, "-")
	a, _ := strconv.Atoi(arr[0])
	b, _ := strconv.Atoi(arr[1])
	return a, b
}

func findDuplicatesInRange(a, b int) []int64 {
	var duplicates []int64
	for i := a; i <= b; i++ {
		if !containsDuplicate(i) && !containsSequence(i) {
			continue
		}
		duplicates = append(duplicates, int64(i))
	}
	return duplicates
}

func containsDuplicate(i int) bool {
	v := strconv.Itoa(i)
	l := len(v)
	if l%2 != 0 {
		return false
	}
	a := v[0 : l/2]
	b := v[l/2:]
	return a == b
}
func containsSequence(i int) bool {
	str := strconv.Itoa(i)
	maxPatternLen := len(str) / 2
	for l := 1; l <= maxPatternLen; l++ {
		if len(str)%l != 0 {
			continue
		}
		pattern := str[:l]
		if strings.Repeat(pattern, len(str)/l) == str {
			return true
		}
	}
	return false
}
