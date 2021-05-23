package basam.example.com.muscleart;

public class BDEmul implements BDHelper{
    public  double[] GetData(double k){

        double[] prop1 = new double[8];

        if (k <= 0.35){
            prop1 = new double[] {35.6, 33.3, 27.7, 92.5, 69.3, 83.3, 50, 33.3};
        }
        else if ((k > 0.35)&&(k <= 0.37)){
            prop1 = new double[] {36.8, 34.5, 28.7, 96.3, 72.1, 86.6, 51.8, 34.5};
        }
        else if ((k > 0.37)&&(k <= 0.4)){
            prop1 = new double[] {38.1, 35.8, 30, 99.8, 74.7, 89.7, 53.8, 35.8};
        }
        else if ((k > 0.4)&&(k <= 0.43)){
            prop1 = new double[] {39.6, 37.1, 31, 103.4, 76.2, 93,  55.9, 37.1};
        }
        else if ((k > 0.43)&&(k <= 0.45)){
            prop1 = new double[] {40.9, 38.4, 32, 106.9, 80.3, 96.3, 57.7, 38.4};
        }
        else if ((k > 0.45)&&(k <= 0.48)){
            prop1 = new double[] {42.4, 39.9, 33.3, 110.5, 82.8 ,99.6 , 59.7, 39.9};
        }
        else if ((k > 0.48)&&(k <= 0.51)){
            prop1 = new double[] {43.7, 41.1, 34.3, 114.3, 85.6, 102.9, 61.7, 41.1};
        }
        else if ((k > 0.51)&&(k <= 0.55)){
            prop1 = new double[] {45.2, 42.4, 35.3, 117.9, 88.4, 105.9, 63.5, 42.4};
        }
        else if (k > 0.55){
            prop1 = new double[] {46.5, 43.9, 36.6, 121.9, 91.4, 109.7, 65.8, 43.9};
        }
        return prop1;
    }

}
