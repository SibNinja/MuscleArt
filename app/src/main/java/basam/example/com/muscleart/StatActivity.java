package basam.example.com.muscleart;

import android.app.Activity;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.broooapps.lineargraphview2.DataModel;
import com.broooapps.lineargraphview2.LinearGraphView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;

import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.util.ArrayList;
import java.util.List;

import static basam.example.com.muscleart.PropActivity.count;
//import static basam.example.com.muscleart.PropActivity.sKey;


public class StatActivity extends AppCompatActivity {
    Dialog dialog;
    TextView rec,fsp;
    Button btnCancel,btnRec;
    FirebaseDatabase db;      //подключение
    DatabaseReference size;
    String color1="#e87451",color2="#5cd186";
    double wrist;
    String str1;
    String str2;
    double sum1=0,sum2=0;
    int[] max =new int[8];
    int[] pro =new int[8];
    int[] reg =new int[8];
    RelativeLayout root_stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        btnCancel = findViewById(R.id.btnCancel);
        btnRec = findViewById(R.id.btnRec);
        //rec = dialog.findViewById(R.id.Rec);
        //fsp = dialog.findViewById(R.id.food_sport);
        root_stat = findViewById(R.id.root_stat);
        Intent intent = getIntent();
         wrist = intent.getDoubleExtra("wrist", 0);
        double[] pMy = intent.getDoubleArrayExtra("my_prop");
        double[] p = intent.getDoubleArrayExtra("perfect");

        for (int i=0;i<8;i++)
        {   reg[i]=0;
            max[i]=(int)p[i];
            pro[i]=(int)pMy[i];
            if(pMy[i]>p[i]){
                pro[i]=(int)p[i]; max[i]=(int)pMy[i];
                reg[i] = (int)(pMy[i]-p[i]);
                pro[i] = pro[i]/2;
            }
            sum1=sum1+Math.abs(p[i]-pMy[i]);
            sum2=sum2+(p[i]-pMy[i]);
        }
        str1 = String.valueOf(sum1);
        str2 = String.valueOf(sum2);
        //Snackbar.make(root_stat, str2,Snackbar.LENGTH_SHORT).show();
        if(sum1<20) str1="Вы в отличной форме!";else str1="Вам необходимо привести себя в форму! ";
        if(sum2>=0) str2="Вам необходимо сосредоточиться на увеличении мышечной массы. Сбалансируйте питание и постепенно увеличивайте калорийность. Занимайтесь силовыми тренировками хотя бы раз в неделю. Минимизируйте стресс и недосыпания во время восстановления. Результаты не заставят себя ждать!";
        if(sum2<0) str2="Вам необходимо сосредоточиться уменьшении лишнего веса. Сбалансируйте питание и постепенно уменьшайте калорийность. Увеличивайте интенсивность тренировок и повседневную активность. Минимизируйте стресс и недосыпания во время восстановления. Результаты не заставят себя ждать!";
        drow1(); drow2();
        drow3(); drow4();
        drow5(); drow6();
        drow7(); drow8();
    //   rec.setText(str1);
      //  fsp.setText(str2);






        /*db = FirebaseDatabase.getInstance();
        size = db.getReference(sKey);
        size.child("neck").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                if(!TextUtils.isEmpty(text))
                    stat.setText(text);
                yNeck=stat.getText().toString();
                y=Double.parseDouble(yNeck);
                statField.setText(String.valueOf(y));
                GraphView graph = (GraphView) findViewById(R.id.graph);
                BarGraphSeries series = new BarGraphSeries(new DataPoint[] {
                        new DataPoint(0, y+10),
                        new DataPoint(1, y+1),
                        new DataPoint(2, y+4),
                        new DataPoint(3, y-1),
                        new DataPoint(4, 6)
                });
                graph.addSeries(series);
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

*/

        btnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrist < 17){
                    showDialog1();

                }
                else if((wrist >= 17)&&(wrist <= 19.5)) {
                    showDialog();
                }
                else if (wrist > 19.5) {
                    showDialog2();
                }

            }
        });




        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    private void drow1() {
        LinearGraphView lgv = findViewById(R.id.linear_graph_view1);
        List<DataModel> dataList = new ArrayList<>();
        if(reg[0]>0) {dataList.add(new DataModel("Two", color1, reg[0]));lgv.setData(dataList, pro[0]);}
        else {dataList.add(new DataModel("One", color2, pro[0]));lgv.setData(dataList, max[0]);}

    }
    private void drow2() {
        LinearGraphView lgv2 = findViewById(R.id.linear_graph_view2);
        List<DataModel> dataList2 = new ArrayList<>();
        if(reg[1]>0) {dataList2.add(new DataModel("Two", color1, reg[1])); lgv2.setData(dataList2, pro[1]);}
        else{dataList2.add(new DataModel("Two", color2, pro[1])); lgv2.setData(dataList2, max[1]);}

    }
    private void drow3() {
        LinearGraphView lgv3 = findViewById(R.id.linear_graph_view3);
        List<DataModel> dataList3 = new ArrayList<>();
        if(reg[2]>0) {dataList3.add(new DataModel("Two", color1, reg[2])); lgv3.setData(dataList3, pro[2]);}
        else{dataList3.add(new DataModel("Three", color2, pro[2])); lgv3.setData(dataList3, max[2]);}

    }
    private void drow4() {
        LinearGraphView lgv4 = findViewById(R.id.linear_graph_view4);
        List<DataModel> dataList4 = new ArrayList<>();
        if(reg[3]>0) {dataList4.add(new DataModel("Two", color1, reg[3]));lgv4.setData(dataList4, pro[3]);}
        else{dataList4.add(new DataModel("Two", color2, pro[3]));lgv4.setData(dataList4, max[3]);}

    }
    private void drow5() {
        LinearGraphView lgv5 = findViewById(R.id.linear_graph_view5);
        List<DataModel> dataList5 = new ArrayList<>();
        if(reg[4]>0) {dataList5.add(new DataModel("Two", color1, reg[4]));lgv5.setData(dataList5, pro[4]);}
        else {dataList5.add(new DataModel("One", color2, pro[4]));lgv5.setData(dataList5, max[4]);}

    }
    private void drow6() {
        LinearGraphView lgv6 = findViewById(R.id.linear_graph_view6);
        List<DataModel> dataList6 = new ArrayList<>();
       if(reg[5]>0) {dataList6.add(new DataModel("Two", color1, reg[5]));  lgv6.setData(dataList6, pro[5]);}
       else {dataList6.add(new DataModel("Two", color2, pro[5]));  lgv6.setData(dataList6, max[5]);}

    }
    private void drow7() {
        LinearGraphView lgv7 = findViewById(R.id.linear_graph_view7);
        List<DataModel> dataList7 = new ArrayList<>();
        if(reg[6]>0) {dataList7.add(new DataModel("Two", color1, reg[6])); lgv7.setData(dataList7, pro[6]);}
        else  {dataList7.add(new DataModel("One", color2, pro[6])); lgv7.setData(dataList7, max[6]);}

    }
    private void drow8() {
        LinearGraphView lgv8 = findViewById(R.id.linear_graph_view8);
        List<DataModel> dataList8 = new ArrayList<>();
        if(reg[7]>0) {dataList8.add(new DataModel("Two", color1, reg[7])); lgv8.setData(dataList8, pro[7]);}
        else  { dataList8.add(new DataModel("Two", color2, pro[7])); lgv8.setData(dataList8, max[7]);}

    }
    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.recom_dialog);
        Button buy = dialog.findViewById(R.id.btnBuy);
        TextView rec = dialog.findViewById(R.id.Rec);
        TextView fsp = dialog.findViewById(R.id.food_sport);
        rec.setText(str1);
        fsp.setText(str2);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //rec.setText(str1+str2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void showDialog1() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.recom_dialog1);
        Button buy = dialog.findViewById(R.id.btnBuy);
        TextView rec = dialog.findViewById(R.id.Rec);
        TextView fsp = dialog.findViewById(R.id.food_sport);
        rec.setText(str1);
        fsp.setText(str2);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void showDialog2() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.recom_dialog2);
        Button buy = dialog.findViewById(R.id.btnBuy);
        TextView rec = dialog.findViewById(R.id.Rec);
        TextView fsp = dialog.findViewById(R.id.food_sport);
        rec.setText(str1);
        fsp.setText(str2);
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
