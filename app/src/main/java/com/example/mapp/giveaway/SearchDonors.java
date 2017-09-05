package com.example.mapp.giveaway;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchDonors extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<UserInformation> searchUsers = new ArrayList<UserInformation>();
    Spinner bgSpinner;
    EditText editText;
    private DatabaseReference mFirebaseDatabaseReference;
    Button searchButton;
    Button list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mapp.giveaway.R.layout.activity_search_donors);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.primary_dark));


        }


        Toolbar  toolbar = (Toolbar) findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("    Search Donors");
            toolbar.setLogo(R.drawable.homew);
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
            toolbar.setTitleTextColor(Color.WHITE);}

        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte =new Intent (SearchDonors.this,HomeActivity.class);
                startActivity(inte);
            }
        });

        list = (Button)findViewById(R.id.listClick);
        searchButton = (Button) findViewById(com.example.mapp.giveaway.R.id.search);
        editText = (EditText) findViewById(com.example.mapp.giveaway.R.id.adress);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("Donors");
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(" Number of donors available " + dataSnapshot.getChildrenCount());
                long count = dataSnapshot.getChildrenCount();
                System.out.println("dataSnapshot.getChildren() " + dataSnapshot.getChildrenCount());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    UserInformation ui = child.getValue(UserInformation.class);
                    searchUsers.add(ui);
                    System.out.println("Adding user  " + ui);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        searchButton.setOnClickListener(this);
        list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String category = "";
        String city = "";
        try {
            Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
            category = spinner.getSelectedItem().toString();
            city = ((TextView) findViewById(R.id.searchCity)).getText().toString();
            System.out.println("Selected blood group is >" + category + "<" + " selected city >"+city+"<");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intentIn = new Intent();

        if(intentIn!= null){
            intentIn.putExtra("DonorsList", searchUsers);
            intentIn.putExtra("BloodGroup",category);
            intentIn.putExtra("CityName",city);
        }


        switch (v.getId()) {
            case R.id.listClick:
             //   intentIn.setClass(SearchDonors.this, DonorsList.class);
               // startActivity(intentIn);
                break;

            case R.id.search:
                intentIn.setClass(SearchDonors.this, MapsActivity.class);
                startActivity(intentIn);
                break;
        }


    }



}

