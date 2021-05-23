package basam.example.com.muscleart;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class PropActivity extends AppCompatActivity {
    Dialog dialog;
    BDHelper helper = new BDEmul();
    Button btnCancel, btnNext, help;
    RelativeLayout root_p;
    //Базовые параметры
    double[] pMy = new double[8];
    //стартовые, желаемые, время в неделях
    EditText neck, biceps, forearm, chest, waist, basin, hips, shin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop);
        help = findViewById(R.id.btnHelp);
        btnCancel = findViewById(R.id.btnCancel);
        btnNext = findViewById(R.id.btnNext);

        root_p = findViewById(R.id.root_prop);

        neck =  findViewById(R.id.neckMy);
        biceps =  findViewById(R.id.bicepsMy);
        forearm =  findViewById(R.id.forearmMy);
        chest =  findViewById(R.id.chestMy);
        waist =  findViewById(R.id.waistMy);
        basin =  findViewById(R.id.basinMy);
        hips =  findViewById(R.id.hipsMy);
        shin =  findViewById(R.id.shinMy);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(neck.getText().toString())||(Double.parseDouble(neck.getText().toString())>70)||(Double.parseDouble(neck.getText().toString())<10)){
                    Snackbar.make(root_p,"Укажите ваш обхват шеи (10-70 см)",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(biceps.getText().toString())||(Double.parseDouble(biceps.getText().toString())>90)||(Double.parseDouble(biceps.getText().toString())<10)){
                    Snackbar.make(root_p,"Укажите ваш обхват бицепса (10-90 см)",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(forearm.getText().toString())||(Double.parseDouble(forearm.getText().toString())>70)||(Double.parseDouble(forearm.getText().toString())<10)){
                    Snackbar.make(root_p,"Укажите ваш обхват предплечья (10-70 см)",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(chest.getText().toString())||(Double.parseDouble(chest.getText().toString())>150)||(Double.parseDouble(chest.getText().toString())<50)){
                    Snackbar.make(root_p,"Укажите ваш обхват груди (50-150 см)",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(waist.getText().toString())||(Double.parseDouble(waist.getText().toString())>170)||(Double.parseDouble(waist.getText().toString())<30)){
                    Snackbar.make(root_p,"Укажите вашу окружность талии (30-170 см)",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(basin.getText().toString())||(Double.parseDouble(basin.getText().toString())>150)||(Double.parseDouble(basin.getText().toString())<40)){
                    Snackbar.make(root_p,"Укажите ваш обхват таза (40-150 см)",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(hips.getText().toString())||(Double.parseDouble(hips.getText().toString())>150)||(Double.parseDouble(hips.getText().toString())<20)){
                    Snackbar.make(root_p,"Укажите обхват бедра (20-150 см)",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(shin.getText().toString())||(Double.parseDouble(shin.getText().toString())>70)||(Double.parseDouble(shin.getText().toString())<10)){
                    Snackbar.make(root_p,"Укажите ваш обхват голени (10-70 см)",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                String r = neck.getText().toString();
                pMy[0]= Double.parseDouble(r);
                r = biceps.getText().toString();
                pMy[1]= Double.parseDouble(r);
                r = forearm.getText().toString();
                pMy[2]= Double.parseDouble(r);
                r = chest.getText().toString();
                pMy[3]= Double.parseDouble(r);
                r = waist.getText().toString();
                pMy[4]= Double.parseDouble(r);
                r = basin.getText().toString();
                pMy[5]= Double.parseDouble(r);
                r = hips.getText().toString();
                pMy[6]= Double.parseDouble(r);
                r = shin.getText().toString();
                pMy[7]= Double.parseDouble(r);

                showPreview();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }


    private void showPreview() {
        Intent intent = getIntent();
        double k = intent.getDoubleExtra("kf", 0);
        double wrist = intent.getDoubleExtra("wrist", 0);
        int exp = intent.getIntExtra("exp", 0);
        double[] p = helper.GetData(k);

        Intent i = new Intent(this, PreviewActivity.class);
        i.putExtra("my_prop", pMy);
        i.putExtra("perfect", p);
        i.putExtra("wrist",wrist);
        i.putExtra("exp",exp);
        startActivity(i);

        // finish();
    }

    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        Button buy = dialog.findViewById(R.id.btnBuy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
