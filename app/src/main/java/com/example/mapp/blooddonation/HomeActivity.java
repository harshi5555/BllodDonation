package com.example.mapp.blooddonation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    Button buttonDonate;
    Button buttoninvite;
    Button buttonSearch;
    Button buttonStatic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mapp.blooddonation.R.layout.activity_home);

        initialize();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);





        }





        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Blood Donation");
            toolbar.getBackground().setAlpha(0);
            toolbar.setTitleTextColor(Color.WHITE);

            // FirebaseAuth user = firebaseAuth.getCurrentUser();
            // toolbar.setText(user);
        }


    }

    private void initialize() {
        buttonStatic = (Button) findViewById(com.example.mapp.blooddonation.R.id.statistic);
        buttonSearch = (Button) findViewById(com.example.mapp.blooddonation.R.id.search);
        buttonDonate = (Button) findViewById(com.example.mapp.blooddonation.R.id.donation);
        //buttoninvite = (Button) findViewById(com.example.mapp.blooddonation.R.id.invite);

       // buttoninvite.setOnClickListener(this);
        buttonDonate.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);
        buttonStatic.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(com.example.mapp.blooddonation.R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == com.example.mapp.blooddonation.R.id.myicon) {

            Intent intent = new Intent(HomeActivity.this, CurrentUserInfo.class);
            startActivity(intent);
            return true;
        }
        int res2_id = item.getItemId();

        if (res_id == R.id.informationD) {

            Intent intent = new Intent(HomeActivity.this, Information.class);
            startActivity(intent);
            return true;
        }

        int res1_id = item.getItemId();
        if(res1_id == R.id.logoutM){


            startActivity(new Intent(this,Login.class));

        }
        return true;

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.example.mapp.blooddonation.R.id.donation:
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, FindDonationPlace.class);
                startActivity(intent);
                break;
            case com.example.mapp.blooddonation.R.id.search:
                Intent intent1 = new Intent();
                intent1.setClass(HomeActivity.this, SearchDonors.class);
                startActivity(intent1);
                break;

            case R.id.statistic:
                Intent intentIn = new Intent();
                intentIn.setClass(HomeActivity.this, Statistics.class);
                startActivity(intentIn);
                break;


            }


        }


    }
