package com.example.mapp.giveaway;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by harshi on 06/04/17.
 */

public class EmailHandling extends AppCompatActivity {
    //TextView emailSend;
    EditText messege;
    Button sendEmail;
    final static int RESULT_OK = 1;
    Toolbar toolbar;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailhandling);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));






        }


        toolbar = (Toolbar) findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("    Blood Donation");
            toolbar.setLogo(R.drawable.homew);
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
            toolbar.setTitleTextColor(Color.WHITE);}

        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte =new Intent (EmailHandling.this,HomeActivity.class);
                startActivity(inte);
            }
        });

        //emailSend = (TextView) findViewById(R.id.emailSend);
            messege = (EditText) findViewById(R.id.message);
        messege.setSelection(0);
           messege.getSelectionStart();

        sendEmail = (Button) findViewById(R.id.sendEmail);


            final Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();
            email = (String) bundle.getString("email");
            System.out.println("email???????   " + email);

            // if (email != null) {
            //emailSend.setText(email);


            sendEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EmailHandling.this);
                    builder.setMessage("Do you want to proceed?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String message = messege.getText().toString();
                            String to = email;
                            String subject = (" Blood Donation");
                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});

                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                            emailIntent.setType("text/plain");
                            startActivityForResult(Intent.createChooser(emailIntent,"Choose anEmail Client"),1);
                        }


                    }).setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });

        }


        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            if (resultCode == RESULT_OK) {

            }
            Intent inten = new Intent(this, SearchDonors.class);
            startActivity(inten);

        }
    }




