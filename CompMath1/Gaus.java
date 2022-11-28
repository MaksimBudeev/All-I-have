import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Gaus{
    public static void superMethod(double[][] matrix, int len, int column){
        int k = 0;
        int maxi;
        int scalingMultiplier;
        double multiplier;
        double[] results = new double[column - 1];


        double max;
        while (k != len - 1){
            max = 0;
            maxi = 0;
            for(int i = k; i < len; i++){
                if(Math.abs(matrix[i][k]) > max){
                    max = matrix[i][k];
                    maxi = i;
                }
            }
            if(max == 0){
                System.out.println("Неверная запись");
                return;
            }

            double[] temp = matrix[k];
            matrix[k] = matrix[maxi];
            matrix[maxi] = temp;

            scalingMultiplier = (len - 1) - k; 

            while (scalingMultiplier > 0){
                multiplier = matrix[len - scalingMultiplier][k] / matrix[k][k];

                if(matrix[len - scalingMultiplier][k] + (matrix[k][k] * multiplier) != 0){
                    multiplier = multiplier *-1;
                }

                for(int j = k; j < column; j++){
                    matrix[len - scalingMultiplier][j] = matrix[len - scalingMultiplier][j] + (matrix[k][j] * multiplier);
                }
                scalingMultiplier--;
            }
            k++;
        }




        for(int i = len - 1; i >= 0; i--){
            for(int j = i + 1; j < len; j++){
                matrix[i][column - 1] = matrix[i][column - 1] + ((results[j] * matrix[i][j]) *-1);
            }
            if(matrix[i][i] != 0) {
                results[i] = matrix[i][column - 1] / matrix[i][i];
            }else if(matrix[i][column - 1] == 0) {
                System.out.println("Система имеет множество решений");
                return;
            }else if(matrix[i][i] == 0){
                System.out.println("Система уравнений не имеет решений ");
                return;
            }
        }
        File file = new File("D:\\test2.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (double i: results) {
                fileWriter.write(String.valueOf(i));
                fileWriter.write(" ");
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
