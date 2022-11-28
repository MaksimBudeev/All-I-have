import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Stream;

public class kekw {
    public static void main(String[] args) {

            int lines = 1;
            int colums = 0;
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\test.txt"));
                String str = bufferedReader.readLine();
                if (str == null) {
                    System.out.println("Файл пустой");
                }
                String[] temp = str.split("\\s+");
                colums = temp.length;
                while (bufferedReader.readLine() != null) {
                    lines++;
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            File file = new File("D:\\test.txt");
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            double[][] matrix = new double[lines][colums];
            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < colums; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }
            scanner.close();

            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < colums; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }

            Iteraction.InteractionMethod(matrix,matrix.length);
        }

        class Iteraction {
            public static double expectedAccuracy = 0.001;

            public static void InteractionMethod(double[][] matrix, int len) {
                double[] results = new double[len];
                double[] b = new double[len];
                Double[][] cMatrix = new Double[len][len];
                double[] cx = new double[len];
                double normalizedB = 0;
                double normalizedC = 0;

                double tmp;

                for (int i = 0; i < len; i++) {
                    tmp = matrix[i][i];

                    for (int j = 0; j <= len; j++) {
                        matrix[i][j] /= tmp;
                    }
                }

                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < len; j++) {
                        cMatrix[i][j] = matrix[i][j];
                    }

                    cMatrix[i][i] = 0.0;
                    b[i] = matrix[i][len];
                }


                for (int i = 0; i < len; ++i) {
                    for (int j = 0; j < len; j++) {
                        results[i] += Math.abs(cMatrix[i][j]);
                        normalizedC = results[0];

                        if (results[i] > normalizedC) {
                            normalizedC = results[i];
                        }
                    }
                }


                for (int i = 0; i < len; i++) {
                    normalizedB += Math.abs(b[i]);
                }

                int steps = (int) Math.ceil((Math.log((1 - normalizedC) * expectedAccuracy / normalizedB) / Math.log(normalizedC)));

                for (int i = 0; i < steps; i++) {
                    for (int j = 0; j < len; j++) {
                        cx[j] = 0;
                    }

                    for (int j = 0; j < len; j++) {
                        for (int k = 0; k < len; k++) {
                            cx[j] += cMatrix[j][k] * results[k];
                        }
                    }

                    for (int j = 0; j < len; j++) {
                        results[j] = b[j] - cx[j];
                    }
                }

                for (int i = 0; i < len; i++) {
                    System.out.println("x" + i + ": " + results[i]);
                }

                System.out.println("Process is complite for " + steps + " steps");
            }
        }
    }