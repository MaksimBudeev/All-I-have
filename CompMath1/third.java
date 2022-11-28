public class third {
    public static void main(String[] args) {
        double postx = 2;
        double prex = 1;
        double midx = (prex + postx)/2;
        double eps = 0.001;

        while (Math.abs(prex - postx) > eps){
            if(calc(prex) * calc(midx) < 0){
                postx = midx;
            }else if(calc(postx) * calc(midx) < 0){
                prex = midx;
            }else{
                break;
            }
            midx = (prex + postx)/2;
        }


        System.out.println(midx);
    }

    static double calc(double x){
        return x*x - 3;
    }

    static double derivative(double x){
        return 2*x;
    }
}
