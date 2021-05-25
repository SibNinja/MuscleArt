package basam.example.com.muscleart;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ProgActivity extends AppCompatActivity {
    Button btnNext, btnCancel;
    TextView neck,  biceps,  forearm,  chest,  waist,  basin,  hips,  shin;
    TextView neck2, biceps2, forearm2, chest2, waist2, basin2, hips2, shin2;
    TextView neck3, biceps3, forearm3, chest3, waist3, basin3, hips3, shin3;
    double wrist;
    double[] time;
    double[] pMy;
    double[] p;
    double[] diff = new double[8];
    String[] str = new String[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prognos);

        neck =  findViewById(R.id.neck1);
        biceps =  findViewById(R.id.biceps1);
        forearm =  findViewById(R.id.forearm1);
        chest =  findViewById(R.id.chest1);
        waist =  findViewById(R.id.waist1);
        basin =  findViewById(R.id.basin1);
        hips =  findViewById(R.id.hips1);
        shin =  findViewById(R.id.shin1);

        neck2 =  findViewById(R.id.neck2);
        biceps2 =  findViewById(R.id.biceps2);
        forearm2 =  findViewById(R.id.forearm2);
        chest2 =  findViewById(R.id.chest2);
        waist2 =  findViewById(R.id.waist2);
        basin2 =  findViewById(R.id.basin2);
        hips2 =  findViewById(R.id.hips2);
        shin2 =  findViewById(R.id.shin2);

        neck3 =  findViewById(R.id.neck3);
        biceps3 =  findViewById(R.id.biceps3);
        forearm3 =  findViewById(R.id.forearm3);
        chest3 =  findViewById(R.id.chest3);
        waist3 =  findViewById(R.id.waist3);
        basin3 =  findViewById(R.id.basin3);
        hips3 =  findViewById(R.id.hips3);
        shin3 =  findViewById(R.id.shin3);


        btnNext = findViewById(R.id.btnNext);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        pMy = intent.getDoubleArrayExtra("my_prop");
        p = intent.getDoubleArrayExtra("perfect");
        time = intent.getDoubleArrayExtra("p_time");
         wrist = intent.getDoubleExtra("wrist", 0);
        //Intent i = new Intent(this, StatActivity.class);
        //i.putExtra("wrist",wrist);
        stint();
        row2();
        row3();
        row1();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStatistic();
            }
        });
    }

    private void stint() {
        for(int i = 0; i < 8; i++) {
            str[i] = "Прибавить";
            }
        }

    private void row1() {
        neck.setText(str[0]);
        biceps.setText(str[1]);
        forearm.setText(str[2]);
        chest.setText(str[3]);
        waist.setText(str[4]);
        basin.setText(str[5]);
        hips.setText(str[6]);
        shin.setText(str[7]);
    }
    private void row2() {
        for(int i = 0; i < 8; i++)
        {
            diff[i] = (p[i] - pMy[i]);
            if (diff[i] < 0){
                diff[i] = -diff[i];
                str[i] = "Убрать";
            }
        }
        String d = new DecimalFormat("#0.0 см").format(diff[0]);
        neck2.setText(d);
        d = new DecimalFormat("#0.0 см").format(diff[1]);
        biceps2.setText(d);
        d = new DecimalFormat("#0.0 см").format(diff[2]);
        forearm2.setText(d);
        d = new DecimalFormat("#0.0 см").format(diff[3]);
        chest2.setText(d);
        d = new DecimalFormat("#0.0 см").format(diff[4]);
        waist2.setText(d);
        d = new DecimalFormat("#0.0 см").format(diff[5]);
        basin2.setText(d);
        d = new DecimalFormat("#0.0 см").format(diff[6]);
        hips2.setText(d);
        d = new DecimalFormat("#0.0 см").format(diff[7]);
        shin2.setText(d);
    }
    private void row3() {
        //заполнение ряда недель
        String d = new DecimalFormat("#0 недель").format(time[0]);
        neck3.setText(d);
        d = new DecimalFormat("#0 недель").format(time[1]);
        biceps3.setText(d);
        d = new DecimalFormat("#0 недель").format(time[2]);
        forearm3.setText(d);
        d = new DecimalFormat("#0 недель").format(time[3]);
        chest3.setText(d);
        d = new DecimalFormat("#0 недель").format(time[4]);
        waist3.setText(d);
        d = new DecimalFormat("#0 недель").format(time[5]);
        basin3.setText(d);
        d = new DecimalFormat("#0 недель").format(time[6]);
        hips3.setText(d);
        d = new DecimalFormat("#0 недель").format(time[7]);
        shin3.setText(d);
    }


    private void showStatistic() {
        Intent i = new Intent(this, StatActivity.class);
        i.putExtra("my_prop", pMy);
        i.putExtra("perfect", p);
        i.putExtra("wrist",wrist);
        startActivity(i);
    }
}
