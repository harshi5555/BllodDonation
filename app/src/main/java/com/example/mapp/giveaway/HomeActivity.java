package com.example.mapp.giveaway;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    TextView signin;
    Button becomeDonor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mapp.giveaway.R.layout.activity_home);

        initialize();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        }


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
         //   getSupportActionBar().setTitle("");
            toolbar.getBackground().setAlpha(0);
            toolbar.setTitleTextColor(Color.WHITE);

            // FirebaseAuth user = firebaseAuth.getCurrentUser();
            // toolbar.setText(user);
        }


    }

    private void initialize() {
        signin = (TextView) findViewById(R.id.signin);
        becomeDonor = (Button) findViewById(R.id.becomeDonor);
        // buttoninvite.setOnClickListener(this);
        signin.setOnClickListener(this);
        becomeDonor.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.becomeDonor:
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, SignUp.class);
                startActivity(intent);
                break;
            case R.id.signin:
                Intent intent1 = new Intent();
                intent1.setClass(HomeActivity.this, Login.class);
                startActivity(intent1);
                break;

        }
    }
}
