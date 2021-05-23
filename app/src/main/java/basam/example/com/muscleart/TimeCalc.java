package basam.example.com.muscleart;

public class TimeCalc extends TimeAbs {
    public double[] calcTime(double[] p1, double[] p2, double wrist, int exp) {

        double[] time = new double[8];
        double[] speed;
        double speedX = 0;

        if (exp > 5){exp = 5;}
        if (exp < 1){exp = 1;}

        if (wrist < 16.5) {
            speed = new double[] {0.08, 0.04, 0.02, 0.01, 0.005};
            speedX = speed [exp-1];
        }

        else if ((wrist >= 16.5)&&(wrist <= 19.5)) {
            speed = new double[] {0.1, 0.05, 0.025, 0.012, 0.006};
            speedX = speed [exp-1];
        }

        else if (wrist > 19.5) {
            speed = new double[] {0.12, 0.06, 0.03, 0.015, 0.007};
            speedX = speed [exp-1];
        }

        for(int i = 0; i < 8; i++)
        {
            time[i] = (p1[i] - p2[i])/speedX;
            if (time[i] < 0){time[i] = -time[i];}
        }
        return time;
    }

    public void SetData() {
        System.out.println("ok, работаю");
    }
}
