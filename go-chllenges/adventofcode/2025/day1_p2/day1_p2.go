package day1p2

import (
	_ "embed"
	"strconv"
	"strings"
)

//go:embed input.txt
var inputFile string

func GetInputRecords() []string {
	return strings.Split(inputFile, "\n")
}
func GetPass(v int, seq []string) int {
	resArr := CallInSequence(v, seq)
	i := 0
	for _, val := range resArr {
		if val == 0 {
			i++
		}
	}
	return i
}
func CallInSequence(v int, seq []string) []int {
	res := v
	var resArr []int
	for _, val := range seq {
		f := DetectFunc(val)
		res = CallFunc(res, val, f)
		resArr = append(resArr, res)
	}
	return resArr
}

func CallFunc(in int, val string, f func(i int, n int) int) int {
	intst := val[1:]
	intval, _ := strconv.Atoi(intst)
	return f(in, intval)
}

func DetectFunc(val string) func(i int, n int) int {
	if strings.HasPrefix(val, "R") {
		return RotateRight
	} else {
		return RotateLeft
	}
}
func RotateLeft(i int, n int) int {
	if n >= 100 {
		n = n % 100
	}
	if i >= n {
		return i - n
	}
	return 100 - (n - i)
}

func RotateRight(i int, n int) int {
	if n >= 100 {
		n = n % 100
	}
	if (i + n) < 100 {
		return i + n
	}
	return (i + n) - 100
}
