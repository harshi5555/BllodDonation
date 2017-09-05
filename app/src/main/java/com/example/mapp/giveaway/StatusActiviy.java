package com.example.mapp.giveaway;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class StatusActiviy extends AppCompatActivity {
    Button btnCamera;
    Button btnGift;
    //recyclerview object
    private RecyclerView recyclerView;
    //adapter object
    private RecyclerView.Adapter adapter;
    //database reference
    private DatabaseReference mDatabase;
    //progress dialog
    private ProgressDialog progressDialog;
    //list to hold all the uploaded images
    private List<Upload> uploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_activiy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
           getSupportActionBar().setTitle(" 123   ");
          //  toolbar.setLogo(R.drawable.homew);
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
            toolbar.setTitleTextColor(Color.WHITE);
        }
        btnCamera = (Button) findViewById(R.id.btn_camera);
        btnGift = (Button) findViewById(R.id.btn_gift);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        uploads = new ArrayList<>();


        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
        //adding an event listener to fetch values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog.dismiss();
                //iterating through all the values in database

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploads.add(upload);
                }
                //creating adapter

                adapter = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }

    public void toggleView(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                Intent intentIn = new Intent(StatusActiviy.this, DonorsPage.class);
                startActivity(intentIn);
                break;
            case R.id.btn_gift:
              //  Intent intent = new Intent(StatusActiviy.this, SearchDonors.class);
             //   startActivity(intent);
                break;
        }


    }
    }


