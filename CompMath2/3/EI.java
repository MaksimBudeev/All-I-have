public class EI {
    static Double Aitken(Double[] x, Double[] y, int Num, int j, double X) {
        double result;
        result = (y[Num] * (X - x[Num + j]) - y[Num + 1] * (X - x[Num])) / (x[Num] - x[Num + j]);

        return result;
    }

    public static void main(String[] args) {
        int N = 5;
        // Точки, между которыми мы будем искать значение
        Double[] x = { 1.5, 1.52, 1.54, 1.56, 1.58 };
        // Значения в этих точках
        Double[] y = { 4.4817, 4.5722, 4.6646, 4.7588, 4.855 };
        // Массив, который сохраняет значения массива выше, так как в ходе выполнения формулы верхний массив изменяется
        Double[] buf = { 4.4817, 4.5722, 4.6646, 4.7588, 4.855 };
        Double X = 1.51;
        Double[] Y = new Double[5];

        int i, j;
        for (j = 1; j < N; j++) {
            for (i = 0; i < N - j; i++) {
                Y[i] = Aitken(x, y, i, j, X);
            }
            for (int k = 0; k < i; k++) {
                //переносим в массив полученные функцией знаения для последующей итерации
                y[k] = Y[k];
            }
        }
        // Y[0] само значение
        System.out.println(Y[0]);

        for (i = 0; i < N; i++) {
            System.out.println(x[i] + " " + buf[i]);
            if (X > x[i] && X < x[i + 1]) {
                System.out.println(X + " " + Y[0]);
            }
        }
    }
}
