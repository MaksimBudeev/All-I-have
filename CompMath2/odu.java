package kurs;

public class odu {
    static double F(double x, double y) {
        return x+y;
    }

    static void Euler(double[] X, double[] Y, int iMax, double h) {

        for (int i = 1; i < iMax; i++) {
            X[i] = X[i - 1] + h;
            Y[i] = Y[i - 1] + h * F(X[i - 1], Y[i - 1]);
        }
        for (int i = 0; i < iMax; i++) {
            System.out.println("Y[" + i + "]=" + Y[i] + " ");
        }
        System.out.println("\n\n");
    }

    static void CauchyEuler(double[] X, double[] Y, int iMax, double h) {

        double[] Y1 = new double[iMax];

        for (int i = 1; i < iMax; i++) {
            X[i] = X[i - 1] + h;
            Y1[i] = Y[i - 1] + h * F(X[i - 1], Y[i - 1]);
            Y[i] = Y[i - 1] + h/2 * (F(X[i - 1], Y[i - 1]) + F(X[i], Y1[i]));
        }

        for (int i = 0; i < iMax; i++) {
            System.out.println("Y[" + i + "]=" + Y[i] + " ");
        }
        System.out.println("\n\n");
    }

    static void RungeKutta(double[] X, double[] Y, int iMax, double h) {

        double[] K1 = new double[iMax];
        double[] K2 = new double[iMax];
        double[] K3 = new double[iMax];
        double[] K4 = new double[iMax];

        for (int i = 1; i < iMax; i++) {
            X[i] = X[i - 1] + h;
            K1[i] = h * F(X[i - 1], Y[i - 1]);
            K2[i] = h * F(X[i - 1] + h / 2.0, Y[i - 1] + K1[i] / 2.0);
            K3[i] = h * F(X[i - 1] + h / 2, Y[i - 1] + K2[i] / 2);
            K4[i] = h * F(X[i - 1] + h, Y[i - 1] + K3[i]);
            Y[i] = Y[i - 1] + (K1[i] + 2 * K2[i] + 2 * K3[i] + K4[i]) / 6;
        }

        System.out.println("\n\n");
        for (int i = 0; i < iMax; i++) {
            System.out.println("Y[" + i + "]=" + Y[i] + " ");
        }
    }

    public static void main(String[] args) {
        double h = 0.1;
        int iMax = 10;
        double[] X = new double[iMax];
        double[] Y = new double[iMax];
        X[0] = 0; Y[0] = 1;

        Euler(X, Y, iMax, h);
        CauchyEuler(X, Y, iMax, h);
        RungeKutta(X, Y, iMax, h);

    }
}
