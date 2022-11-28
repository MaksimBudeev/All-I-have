import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

//        1 0 1 2
//        1 2 3 6
//        3 0 3 6
        int lines = 1;
        int colums = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\test.txt"));
            String str = bufferedReader.readLine();
            if(str == null) {
                System.out.println("Файл пустой");
            }
            String[] temp = str.split("\\s+");
            colums = temp.length;
            while (bufferedReader.readLine() != null){
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
        for(int i = 0; i < lines; i++){
            for (int j = 0; j < colums; j++){
                matrix[i][j] = scanner.nextDouble();
            }
        }
        scanner.close();

        for (int i = 0; i < lines; i++)
        {
            for (int j = 0; j < colums; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        Gaus.superMethod(matrix,lines,colums);







    }
}
