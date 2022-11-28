public class seven {
    static double f(double x)
    {
        return (x + 1) * Math.sin(x);
    }

    static double rightrectangle(double a, double b, int n)
    {
        double x;
        double sum = 0.0;
        double fx;
        double h = (b - a) / n; //шаг разбиения
        //формула правых прямоугольников
        for (int i = 1; i <= n; i++)
        {
            x = a + i * h;
            fx = f(x);
            sum += fx;
        }
        return (sum * h);
    }

    static double trapezoid(double a, double b, int n) {
        double h = (b - a) / n;
        double result = 0;
        for (int i = 0; i < n; i++) {
            double x1 = a + i * h;
            double x2 = a + (i + 1) * h;
            //формула трапеции
            result += ((x2 - x1)/2) * (f(x1) + f(x2));
        }

        return result;
    }

    static double simpson(double a, double b, int n) {
        double h = (b - a) / n; //шаг разбиения
        double result = 0;
        for (int i = 0; i < n; i++) {
            double x1 = a + i * h;
            double x2 = a + (i + 1) * h;
            //формула симпсона
            result += ((x2 - x1)/6) * (f(x1) + 4*f((x1 + x2)/2) + f(x2));
        }

        return result;
    }

    public static void main(String[] args) {
        double a = 1.6, b = 2.4;
        double eps = 1e-2;

        double newValue, oldValue = 0.0;
        int n = 10;

        newValue = rightrectangle(a, b, n);
        while ((Math.abs(newValue - oldValue) > eps)) {
            oldValue = newValue;
            n = 2 * n;
            newValue = rightrectangle(a, b, n);
        }
        System.out.println("rectangle = " + newValue + "\n");

        newValue = trapezoid(a, b, n);
        while ((Math.abs(newValue - oldValue) > eps)) {
            oldValue = newValue;
            n = 2 * n;
            newValue = trapezoid(a, b, n);
        }
        System.out.println("trapezoid = " + newValue + "\n");

        newValue = simpson(a, b, n);
        while ((Math.abs(newValue - oldValue) > eps)) {
            oldValue = newValue;
            n = 2 * n;
            newValue = simpson(a, b, n);
        }
        System.out.println("simpson = " + newValue + "\n");
    }
}
