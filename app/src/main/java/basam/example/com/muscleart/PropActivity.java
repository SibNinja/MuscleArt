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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PropActivity extends AppCompatActivity {
    Dialog dialog;
    BDHelper helper = new BDEmul();
    Button btnCancel, btnNext, help;
    RelativeLayout root_p;
    FirebaseDatabase db;      //подключение
    DatabaseReference size;
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

        dataBaseInit();


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
        pushSize();
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


    private void dataBaseInit() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("key1");
        //key = key+"/Size";
        //попытка сохранить то что ввели
        db = FirebaseDatabase.getInstance();
        size = db.getReference(key);
        size.child("neck").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    neck.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        size.child("biceps").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    biceps.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        size.child("forearm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    forearm.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        size.child("chest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    chest.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        size.child("waist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    waist.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        size.child("basin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    basin.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        size.child("hips").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    hips.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        size.child("shin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    shin.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void pushSize() {

        size.child("neck").setValue(neck.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
        size.child("biceps").setValue(biceps.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
        size.child("forearm").setValue(forearm.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
        size.child("chest").setValue(chest.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
        size.child("waist").setValue(waist.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
        size.child("basin").setValue(basin.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
        size.child("hips").setValue(hips.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
        size.child("shin").setValue(shin.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }


}
