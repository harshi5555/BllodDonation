package com.example.mapp.giveaway;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrentUserInfo extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;
    public FirebaseDatabase database;
    private HashMap<String, String> currentUseInf = new HashMap<String, String>();
    TextView name;
    TextView blood;
    TextView gender;
    TextView email;
    TextView city;


    List<UserInformation> information = new ArrayList<UserInformation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user_info);

        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("    Profile");
            toolbar.setLogo(R.drawable.homew);
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
            toolbar.setTitleTextColor(Color.WHITE);
        }

        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte =new Intent (CurrentUserInfo.this,HomeActivity.class);
                startActivity(inte);
            }
        });


        name =(TextView)findViewById(R.id.nameF);
        blood =(TextView)findViewById(R.id.bloodF);
        gender =(TextView)findViewById(R.id.genderF);
        city =(TextView)findViewById(R.id.cityF);
        email =(TextView)findViewById(R.id.emailF);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference();
        ref.child("Donors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            Iterable<DataSnapshot>children = dataSnapshot.getChildren();
                for (DataSnapshot child:children
                     ) { UserInformation userInformation=child.getValue(UserInformation.class);
                    information.add(userInformation);

                    System.out.println("Adding user  " + userInformation);
                    name.setText(userInformation.getName());
                    blood.setText(userInformation.getBloodGroup());
                    gender.setText(userInformation.getGender());
                    city.setText(userInformation.getCity());
                    email.setText(userInformation.getEmail());

                    String name = userInformation.getName();





                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        }

    }

