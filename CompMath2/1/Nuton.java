public class Nuton {
    public static void main(String[] args) {
        //эпсилон даёт нам необходимую точность вычислений в следующем цикле
        double EPS = 0.001;
        double x = 2;
        double x0 = 0.0;

        while (EPS < Math.abs(x0 - x)){
            x0 = x;
            //делим на свою же производную до тех пор, пока не дошли до нужной точности
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
