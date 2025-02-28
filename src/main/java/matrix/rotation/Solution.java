package matrix.rotation;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

    public static void matrixRotation(List<List<Integer>> matrix, int r) {
        if (matrix.isEmpty()) {
            throw new RuntimeException("Empty matrix");
        }
        int m = matrix.size();
        var first = matrix.get(0);
        int n = first.size();
        int numLayers = Math.min(m, n) / 2;

        validate(matrix, m, n, r);

        for (int layer = 0; layer < numLayers; layer++) {
            List<Integer> layerElements = extractLayerElements(matrix, layer, n, m);

            int numRotations = r % layerElements.size();
            Collections.rotate(layerElements, -numRotations);

            placeElementsToMatrix(m, n, layer, matrix, layerElements);
        }

        for (List<Integer> row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private static void placeElementsToMatrix(int m, int n, int layer, List<List<Integer>> matrix, List<Integer> layerElements) {
        int index = 0;
        for (int j = layer; j < n - layer; j++) {
            matrix.get(layer).set(j, layerElements.get(index++));
        }
        for (int i = layer + 1; i < m - layer - 1; i++) {
            matrix.get(i).set(n - 1 - layer, layerElements.get(index++));
        }
        for (int j = n - 1 - layer; j >= layer; j--) {
            matrix.get(m - 1 - layer).set(j, layerElements.get(index++));
        }
        for (int i = m - 2 - layer; i > layer; i--) {
            matrix.get(i).set(layer, layerElements.get(index++));
        }
    }

    private static List<Integer> extractLayerElements(List<List<Integer>> matrix, int layer, int n, int m) {
        List<Integer> elements = new ArrayList<>();
        for (int j = layer; j < n - layer; j++) {
            elements.add(matrix.get(layer).get(j));
        }
        for (int i = layer + 1; i < m - layer - 1; i++) {
            elements.add(matrix.get(i).get(n - 1 - layer));
        }
        for (int j = n - 1 - layer; j >= layer; j--) {
            elements.add(matrix.get(m - 1 - layer).get(j));
        }
        for (int i = m - 2 - layer; i > layer; i--) {
            elements.add(matrix.get(i).get(layer));
        }
        return elements;
    }

    private static void validate(List<List<Integer>> matrix, int m, int n, int r) {
        int minM = 2;
        int maxN = 300;
        int minR = 1;
        int maxR = (int) Math.pow(10, 9);

        if (m < minM) {
            throw new IllegalArgumentException(String.format("Invalid value for m: %d. m must be between %d and %d.", m, minM, maxN));
        }
        if (n > maxN) {
            throw new IllegalArgumentException(String.format("Invalid value for n: %d. n must be between %d and %d.", n, minM, maxN));
        }
        if (r < minR || r > maxR) {
            throw new IllegalArgumentException(String.format("Invalid value for r: %d. r must be between %d and %d.", r, minR, maxR));
        }
        if (Math.min(m, n) % 2 != 0) {
            throw new IllegalArgumentException("Invalid dimensions: The minimum of m and n must be even.");
        }

        for (int i = 0; i < m; i++) {
            if (matrix.get(i).size() != n) {
                throw new RuntimeException("Inconsistent matrix dimensions. All rows must have the same length.");
            }
            for (int j = 0; j < n; j++) {
                int val = matrix.get(i).get(j);
                if (val < 1 || val > Math.pow(10, 8)) {
                    throw new RuntimeException(String.format("Invalid matrix element value at index (%d, %d): %d", i, j, val));
                }
            }
        }
    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("src/test/resources/matrix/rotation/rotation-input");
//        InputStream in=System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}