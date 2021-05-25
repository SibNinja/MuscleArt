package basam.example.com.muscleart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {
    RelativeLayout root_pr;
    TextView my_body;
    Button btnNext, btnCancel;
    TextView neckBest, bicepsBest, forearmBest, chestBest, waistBest, basinBest, hipsBest, shinBest;
    TimeCalc cTime = new TimeCalc();
    double[] pMy;
    double[] p;
    double[] time;
    double wrist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        my_body = findViewById(R.id.mpr);
        root_pr = findViewById(R.id.root_preview);
        btnNext = findViewById(R.id.btnNext);
        btnCancel = findViewById(R.id.btnCancel);

        neckBest = findViewById(R.id.neckBest);
        bicepsBest = findViewById(R.id.bicepsBest);
        forearmBest = findViewById(R.id.forearmBest);
        chestBest = findViewById(R.id.chestBest);
        waistBest = findViewById(R.id.waistBest);
        basinBest = findViewById(R.id.basinBest);
        hipsBest = findViewById(R.id.hipsBest);
        shinBest = findViewById(R.id.shinBest);

        setBestProp();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrognosis();
            }
        });
    }

    private void showPrognosis() {

        Intent i = new Intent(this, ProgActivity.class);
        i.putExtra("my_prop", pMy);
        i.putExtra("perfect", p);
        i.putExtra("p_time", time);
        i.putExtra("wrist", wrist);
        startActivity(i);

        // finish();
    }

    private void setBestProp() {
        Intent intent = getIntent();
        pMy = intent.getDoubleArrayExtra("my_prop");
        p = intent.getDoubleArrayExtra("perfect");
        wrist = intent.getDoubleExtra("wrist",0);
        int exp = intent.getIntExtra("exp",0);
        String type="Мезоморф";
        if(wrist < 16.5) type = "Эктоморф";
        else if(wrist > 19.5) type = "Эндоморф";
        my_body.setText(type);
        String r = String.valueOf(p[0]);
        neckBest.setText(r);
        r = String.valueOf(p[1]);
        bicepsBest.setText(r);
        r = String.valueOf(p[2]);
        forearmBest.setText(r);
        r = String.valueOf(p[3]);
        chestBest.setText(r);
        r = String.valueOf(p[4]);
        waistBest.setText(r);
        r = String.valueOf(p[5]);
        basinBest.setText(r);
        r = String.valueOf(p[6]);
        hipsBest.setText(r);
        r = String.valueOf(p[7]);
        shinBest.setText(r);
        time = cTime.calcTime(pMy,p,wrist,exp);

    }
}
