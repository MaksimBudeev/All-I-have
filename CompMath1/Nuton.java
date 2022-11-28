public class Nuton {
    public static void main(String[] args) {
        double EPS = 0.001;
        double x = 2;
        double x0 = 0.0;

        while (EPS < Math.abs(x0 - x)){
            x0 = x;
            x = x0 - calc(x) / derivative(x);
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
