package day3p2

import (
	_ "embed"
	"strconv"
	"strings"
)

//go:embed input.txt
var in string

func FindRowJoltageTotal() int {
	line := strings.Split(in, "\n")
	var sum int
	for _, l := range line {
		v := FindRowJoltage(l)
		sum += v
	}
	return sum
}

func FindRowJoltage(str string) int {
	const maxLen int = 12
	var resStr string
	for i := range maxLen {
		neededLen := maxLen - i
		scope := 1 + len(str) - neededLen
		scope = min(scope, len(str))
		subStr := str[0:scope]
		m, p := findMaxPos(subStr)
		resStr = resStr + m
		str = str[p+1:]
	}
	r, _ := strconv.ParseInt(resStr, 10, 0)
	return int(r)
}

func findMaxPos(str string) (string, int) {
	pos := 0
	m := rune(str[0])
	for i, v := range str {
		if v > m {
			m = v
			pos = i
		}
	}
	return string(m), pos
}
