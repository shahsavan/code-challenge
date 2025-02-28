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
        var sum= matrix.stream()
                .map(l->l.stream().mapToInt(Integer::intValue).sum())
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
        return 0;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
//        InputStream stream=System.in;
//        String outputPath = System.getenv("OUTPUT_PATH");
        InputStream stream=new FileInputStream("src/matrix/square/magic-square.txt");
        String outputPath = "src/matrix/square/out_file";
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