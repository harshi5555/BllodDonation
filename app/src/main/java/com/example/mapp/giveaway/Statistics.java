package com.example.mapp.giveaway;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Statistics extends AppCompatActivity {

    final List<UserInformation>userserInformation = new ArrayList<UserInformation>();
    int total = 0;
    String mTotal = "";


    int a, ap, an,  o , op, on,  bp, bn, abp, abn;
    int bloodDonation [] ;

    TextView textView;
    String bloodType[] = {"A+","A-","O+","O-","B+","B-","AB+","AB-"};

    HashSet<String> typeArray= new HashSet<String>();
    ArrayList<Integer> bloodCound = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("    Statistics");
            toolbar.setLogo(R.drawable.homew);
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
            toolbar.setTitleTextColor(Color.WHITE);}

        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte =new Intent (Statistics.this,HomeActivity.class);
                startActivity(inte);
            }
        });



         ap = an = op = on = bp = bn = abp = abn = 0;
        textView =(TextView)findViewById(R.id.count);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Donors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot>children =dataSnapshot.getChildren();
               long count = dataSnapshot.getChildrenCount();
                System.out.println("dataSnapshot.getChildren() " + dataSnapshot.getChildrenCount());
                System.out.println("dataSnapshot.getChildrenCount()  "+dataSnapshot.getChildrenCount());

                for ( DataSnapshot child: children){
                    UserInformation userInformation =child.getValue(UserInformation.class);
                    // userserInformation.add(userInformation);
                    typeArray.add(userInformation.getBloodGroup().toUpperCase());
                    String bg = userInformation.getBloodGroup().toUpperCase();
                    System.out.println(">"+bg+"<");
                    switch (bg){
                       // case "A" : a++ ; break;
                        case "A+" : ap++ ; break;
                        case "A-" : an++ ; break;
                        //case "O" : o++ ; break;
                        case "O+" : op++ ; break;
                        case "O-" : on++ ; break;
                        case "B+" : bp++ ; break;
                        case "B-" : bn++ ; break;
                        case "AB+" : abp++ ; break;
                        case "AB-" : abn++ ; break;
                    }
                }

                System.out.println("typeArray  "+typeArray);
                System.out.println( " ap "+ap + " an "+an +" o "+o + " op "+op +" on "+on + " bn "+bn + " abp "+abp+ " abn "+abn);
                bloodDonation = new int []{  ap , an ,  op, on, bp, bn, abp, abn};
                setupPieChart();
                total = a+ap+an+o+op+on+bp+bn+abp+abn;
                System.out.println("total"  + total);
                mTotal = "Total Donors  "+total;
                textView.setText(mTotal);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
    private  void setupPieChart(){
        //populating a list of pieEntries
        List<PieEntry>pieEntries = new ArrayList<>();
        System.out.println("bloodDonation length "+bloodDonation.length + "  bloodType length "+bloodType.length);
        for (int i = 0;i<bloodType.length; i++){
            pieEntries.add ( new PieEntry(bloodDonation[i],bloodType[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"Blood Donations");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));



        //get the charts
        PieChart chart = (PieChart)findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();

    }
}