public class Hord {
    public static void main(String[] args) {
        double x = 2;
        double a = 1, b = 2;

        //эпсилон даёт нам необходимую точность вычислений в следующем цикле
        double eps = 0.01;

        while (Math.abs(b - a) > eps)
        {
            b = a;
            a = x;
            //Как в методе Ньютона, только не по производной, а по уже готовому выражению
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
