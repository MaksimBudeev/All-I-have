import java.util.ArrayList;
import java.util.List;

public class InterNuton {
    //функция находит раздельную разности
    private static Double dy(List<Double> Y, List<Double> X){
        //Если узловых значений больше 2 - мы формируем раздельные списки
        if (Y.size() > 2)
        {
            List<Double> Yleft = new ArrayList<Double>(Y);
            List<Double> Xleft = new ArrayList<Double>(X);
            //Удаляем первый элемент
            Xleft.remove(0);
            Yleft.remove(0);
            List<Double> Yright = new ArrayList<Double>(Y);
            List<Double> Xright = new ArrayList<Double>(X);
            //Удаляем последний элемент
            Xright.remove(Y.size() - 1);
            Yright.remove(Y.size() - 1);
            //Передаём следующую раздельную разность до тех пор, пока мы не придём к размеру 2
            return (dy(Yleft, Xleft) - dy(Yright, Xright)) / (X.get(X.size() - 1) - X.get(0));
        }
        else if (Y.size() == 2)
        {
            //Возвращаем раздельную разность
            return (Y.get(1) - Y.get(0)) / (X.get(1) - X.get(0));
        }

        return null;
    }

    public static double getValue(Double[] X, Double[] Y, Double x){
        // инициализируем нулевым значением из формулы
        Double res = Y[0];
        Double buf;
        List<Double> Xlist;
        List<Double> Ylist;
        for (int i = 1; i < Y.length; i++)
        {
            Xlist = new ArrayList<Double>();
            Ylist = new ArrayList<Double>();
            buf = 1.0;

            //Формируются раздельные разности
            for(int j = 0; j <= i; j++)
            {
                //Аргументы для раздельной разности
                Xlist.add(X[j]);
                Ylist.add(Y[j]);
                if (j<i)
                    //Складываем перемножения, например f(0) + f(x0, x1) * (x-x0) | Эти перемножения мы складываем в buf
                    // j<i потому что в формуле выше мы не берём x1, мы берём x0, то есть здесь мы отсекаем последнее
                    buf *= x - X[j];
            }
            res += dy(Ylist, Xlist)*buf;
        }
        return res;
    }

    public static double F(Double x) {
        return Math.pow(x,3) - 3*Math.pow(x,2) - 8*Math.abs(x);
    }

    public static void main(String[] args) {

        // Сама суть метода - найти значение функции между двумя уже известными выколотыми точками
        // Массив X - это набор наших точек
        Double[] X = new Double[] {0.0, 2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0};
        // Массив Y - значения функции в этих точках
        Double[] Y = new Double[X.length];
        for (int i = 0; i < X.length; i++){
            Y[i] = F(X[i]);
        }
        Double x;
        Double y;
        // А здесь мы с шагом 2.0 смотрим чему будет равна функция в точке i. То есть в точке 1.0, 3.0, 5.0, и т.д
        for (Double i = 1.0; i < X.length; i += 2) {
            x = i;
            y = getValue(X, Y, x);
            System.out.println("x = " + x + " y = " + y);
        }
    }
}
