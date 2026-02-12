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
	pass := CallInSequence(v, seq)
	return pass
}
func CallInSequence(v int, seq []string) int {
	var zc, zcsum int
	for _, val := range seq {
		f := DetectFunc(val)
		v, zc = CallFunc(v, val, f)
		zcsum += zc
	}
	return zcsum
}

func CallFunc(in int, val string, f func(i int, n int) (int, int)) (int, int) {
	intst := val[1:]
	intval, _ := strconv.Atoi(intst)
	return f(in, intval)
}

func DetectFunc(val string) func(i int, n int) (int, int) {
	if strings.HasPrefix(val, "R") {
		return RotateRight
	} else {
		return RotateLeft
	}
}
func RotateLeft(i int, n int) (int, int) {
	var res, zc int
	if n >= 100 {
		zc = n / 100
		n = n % 100
	}
	if i >= n {
		res = i - n
	} else {
		if i != 0 {
			zc++
		}
		res = 100 - (n - i)
	}

	if res == 0 {
		zc += 1
	}
	return res, zc
}

func RotateRight(i int, n int) (int, int) {
	var res, zc int
	if n >= 100 {
		zc = n / 100
		n = n % 100
	}

	if (i + n) < 100 {
		res = i + n
	} else {
		res = (i + n) - 100
	}

	if res == 0 {
		zc++
	} else if i+n > 100 {
		zc++
	}
	return res, zc
}
