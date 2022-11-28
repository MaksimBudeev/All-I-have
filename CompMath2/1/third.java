public class third {
    public static void main(String[] args) {
        double postx = 2;
        double prex = 1;
        double midx = (prex + postx)/2;
        
        double eps = 0.001;
        // В методе бисекций суть такова: Если непрерывная функция на концах некоторого интервала имеет значения разных знаков, то внутри этого интервала у нее есть корень
        // В итоге мы смотрим значения, сравниваем, если они разных знаков - указателю либо левого, либо правого значения присваиваем значение, которое стоит между ними
        // И так мы делаем до тех пор, пока либо не дойдём до нужной точности, либо пока значения не примут один и тот же знак.
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
