public class Hord {
    public static void main(String[] args) {
        double x = 2;
        double a = 1, b = 2;
        double eps = 0.01;

        while (Math.abs(b - a) > eps)
        {
            b = a;
            a = x;
            x -= calc(a) * (a - b)/(calc(a) - calc(b));
        }

        System.out.println(x);

    }

    static double calc(double x){
        return x*x - 3;
    }

    static double derivative(double x){
        return 2*x;
    }
}
