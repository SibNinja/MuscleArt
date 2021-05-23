package basam.example.com.muscleart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;



public class MapActivity extends AppCompatActivity {

    EditText height, weight, exp, wrist;
    RelativeLayout root_map;
    Button btnCancel, btnNext;
    double k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        height =  findViewById(R.id.heightField);
        weight =  findViewById(R.id.weightField);
        exp =  findViewById(R.id.expField);
        wrist =  findViewById(R.id.wristField);


        btnCancel = findViewById(R.id.btnCancel);
        btnNext = findViewById(R.id.btnNext);

        root_map = findViewById(R.id.root_map);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(height.getText().toString())){
                    Snackbar.make(root_map,"Укажите ваш рост",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(weight.getText().toString())){
                    Snackbar.make(root_map,"Укажите ваш вес",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(wrist.getText().toString())){
                    Snackbar.make(root_map,"Укажите ваш обхват запястья",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(exp.getText().toString())){
                    Snackbar.make(root_map,"Укажите ваш опыт тренировок",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if((Double.parseDouble(height.getText().toString())<50)||(Double.parseDouble(height.getText().toString())>285)){
                    Snackbar.make(root_map,"Укажите ваш настоящий рост в сантиметрах",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if((Double.parseDouble(weight.getText().toString())<10)||(Double.parseDouble(weight.getText().toString())>500)){
                    Snackbar.make(root_map,"Укажите ваш настоящий вес в килограммах",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if((Double.parseDouble(wrist.getText().toString())<3)||(Double.parseDouble(wrist.getText().toString())>35)){
                    Snackbar.make(root_map,"Укажите ваш настоящий обхват запястья в сантиметрах",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if((Integer.parseInt(exp.getText().toString())>130)){
                    Snackbar.make(root_map,"Укажите ваш настоящий стаж тренировок в зале",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                showProp();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    private void showProp() {
        Integer e = Integer.valueOf(exp.getText().toString());
        Double wr = Double.valueOf(wrist.getText().toString());
        Double h = Double.valueOf(height.getText().toString());
        Double w = Double.valueOf(weight.getText().toString());
        k = w/h;
        Intent i = new Intent(this, PropActivity.class);
        i.putExtra("kf", k);
        i.putExtra("wrist", wr);
        i.putExtra("exp", e);
        startActivity(i);

      // finish();
    }
}