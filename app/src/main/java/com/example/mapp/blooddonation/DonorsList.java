package com.example.mapp.blooddonation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mapp.blooddonation.recycleview.DonorViewHolder;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by harshi on 27/03/17.
 */

public class DonorsList extends AppCompatActivity {

    private ArrayList<UserInformation> users = new ArrayList<UserInformation>();

    private Context ctx;
    private DatabaseReference mFirebaseDatabaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseRecyclerAdapter<UserInformation, DonorViewHolder> mFirebaseAdapter;
    private CardView cardView;
    public ImageButton button;
    boolean isFilter = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(com.example.mapp.blooddonation.R.layout.activity_donorslist_view);
      Toolbar  toolbar = (Toolbar) findViewById(R.id.toolb);
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
                Intent inte =new Intent (DonorsList.this,HomeActivity.class);
                startActivity(inte);
            }
        });


        //cardView =(CardView)findViewById(R.id.card_view);
        //cardView.setCardBackgroundColor(Color.parseColor("#ff8a80"));

        firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();

        recyclerView = (RecyclerView)findViewById(com.example.mapp.blooddonation.R.id.roomRecyclerView);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        final String selectedBG = (String) bundle.getString("BloodGroup");
        final String selectedCity = (String) bundle.getString("CityName");
        System.out.println("DonorsList ........selectedCity  "+selectedCity + " selectedBG "+selectedBG);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setStackFromEnd(true);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


        if ( (selectedCity != null && !selectedCity.isEmpty() )){
            isFilter = true;
        }
        Query query =  mFirebaseDatabaseReference.child("Donors").orderByChild("bloodGroup").equalTo(selectedBG);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<UserInformation, DonorViewHolder>(
                UserInformation.class,
                com.example.mapp.blooddonation.R.layout.donors_list,
                DonorViewHolder.class,
                query ) {

            @Override
            protected void populateViewHolder(DonorViewHolder viewHolder, UserInformation model, int position) {

                System.out.println("DonorsList.populateViewHolder isFilter ?  "+isFilter);
                System.out.println("DonorsList model.getName() "+model.getName());
                if(!isFilter && selectedCity.trim().isEmpty()){
                    System.out.println("DonorsList Activity = filter by city no  " );
                    viewHolder.dName.setText(model.getName());
                    viewHolder.dAge.setText(model.getAge());
                    viewHolder.dAddress.setText(model.getAddress());
                    viewHolder.setEmail(model.getEmail());
                    ImageButton button = viewHolder.image;
                    button.setVisibility(View.VISIBLE);
                    TextView tvDName = viewHolder.dName;
                    TextView tvDAge = viewHolder.dAge;
                    TextView tvDAddress = viewHolder.dAddress;
                    tvDName.setVisibility(View.VISIBLE);
                    tvDAge.setVisibility(View.VISIBLE);
                    tvDAddress.setVisibility(View.VISIBLE);
                }
                if(isFilter && selectedCity.equalsIgnoreCase(model.getCity())){
                    System.out.println("DonorsList Activity = filter by city yes  " );
                    viewHolder.dName.setText(model.getName());
                    viewHolder.dAge.setText(model.getAge());
                    viewHolder.dAddress.setText(model.getAddress());
                    viewHolder.setEmail(model.getEmail());
                    ImageButton button = viewHolder.image;
                    button.setVisibility(View.VISIBLE);
                    TextView tvDName = viewHolder.dName;
                    TextView tvDAge = viewHolder.dAge;
                    TextView tvDAddress = viewHolder.dAddress;
                    tvDName.setVisibility(View.VISIBLE);
                    tvDAge.setVisibility(View.VISIBLE);
                    tvDAddress.setVisibility(View.VISIBLE);
                }
            }

        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount){
                System.out.println("MainActivity.registerAdapterDataObserver ");
                System.out.println("MainActivity registerAdapterDataObserver model.getName() "+positionStart);

                super.onItemRangeInserted(positionStart, itemCount);
                int roomCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 || (positionStart >= (roomCount -1) && lastVisiblePosition == (positionStart -1))){
                    //     recyclerView.scrollToPosition(positionStart);
                }
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItem(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mFirebaseAdapter);

        button = (ImageButton)findViewById(R.id.email);
        // button.setOnClickListener(this);

    }




  /*  private void populateListView (UserInformation ui){
        TextView tvN = (TextView) findViewById(R.id.dName);

        tvN.setText(ui.getName());
        TextView tvA = (TextView) findViewById(R.id.dAge);
        tvA.setText(ui.getAge());
        System.out.println("Donor Name "+ui.getName() + " donor age "+ui.getAge());
    }*/

    private void showDonors(RecyclerView recyclerView ) {
        System.out.println("  Show Donors " + users.size());
        for (UserInformation ui : users) {
            System.out.println("  ID " + ui.getName() + "  " + ui.getEmail() + " password " + ui.getPassWord());
        }

    }



}

//recycleViewAdapter = new RecycleViewAdapter(DonorsList.this, users);
     /*   MyAdapter adapter = new MyAdapter(users,recyclerView,this);
        recyclerView.setAdapter(adapter);
        System.out.println("  Set Adapter "+adapter);*/








/*@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        Log.d("test", "test");
        Intent int2 = new Intent(this, SearchDonors.class);

        startActivity(int2);


        }
        }*/