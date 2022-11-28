
public class MonteCarlo {
    public static void main(String[] args) {
        System.out.println(getPi(10000));
        System.out.println(getPi(100000));
        System.out.println(getPi(1000000));
        System.out.println(getPi(10000000));
        System.out.println(getPi(100000000));

    }
    public static double getPi(int numThrows){
        int inCircle= 0;
        for(int i= 0;i < numThrows;i++){
            //квадрат со стороной длины 2 с центром в 0 имеет x and y в диапазоне от -1 до 1
            double randX= (Math.random() * 2) - 1;
            double randY= (Math.random() * 2) - 1;
            //Координаты точки
            double dist= Math.sqrt(randX * randX + randY * randY);
            if(dist < 1){//Круг с диаметром 2
                inCircle++;
            }
        }
        // Площадь квадрата * площадь фигуры / общих точек
        return 4.0 * inCircle / numThrows;
    }
}
