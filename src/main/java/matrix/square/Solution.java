package matrix.square;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'formingMagicSquare' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY s as parameter.
     */

    public static int formingMagicSquare(List<List<Integer>> matrix) {
        int[][] inputMatrix = new int[3][3];

        // Convert List<List<Integer>> to int[][]
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inputMatrix[i][j] = matrix.get(i).get(j);
            }
        }

        // All possible 3x3 magic squares
        int[][][] magicSquares = {
                {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
                {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
                {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
                {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
                {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
                {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
                {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
                {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}}
        };

        int minCost = Integer.MAX_VALUE;

        // Compare with each magic square and find the minimum cost
        for (int[][] magic : magicSquares) {
            int cost = calculateCost(inputMatrix, magic);
            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }
    // Calculate the transformation cost between two matrices
    private static int calculateCost(int[][] matrix, int[][] magic) {
        int cost = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cost += Math.abs(matrix[i][j] - magic[i][j]);
            }
        }
        return cost;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
//        InputStream stream=System.in;
//        String outputPath = System.getenv("OUTPUT_PATH");
        InputStream stream=new FileInputStream("src/test/resources/matrix/square/magic-square.txt");
        String outputPath = "src/test/resources/matrix/square/out_file";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath));

        List<List<Integer>> s = new ArrayList<>();

        IntStream.range(0, 3).forEach(i -> {
            try {
                s.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}