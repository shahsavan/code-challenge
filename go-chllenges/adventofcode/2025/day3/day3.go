package day3

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
	var a, b int64
	for _, c := range str {
		i, _ := strconv.ParseInt(string(c), 10, 0)
		if b > a {
			a, b = b, i
		} else if i > b {
			b = i
		}
	}
	str = strconv.FormatInt(a, 10) + strconv.FormatInt(b, 10)
	r, _ := strconv.ParseInt(str, 10, 0)
	return int(r)
}
