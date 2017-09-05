package com.example.mapp.giveaway;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FindDonationPlace extends AppCompatActivity {
    private ArrayList<PlaceInformation> places= new ArrayList<PlaceInformation>();

    private List <PlaceInformation> placeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PlaceAdapter placeAdapter;
    private CardView cardView;

  TextView textView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donation_place);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("    Blood Donation Centers");
            toolbar.setLogo(R.drawable.homew);
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
            toolbar.setTitleTextColor(Color.WHITE);}

        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte =new Intent (FindDonationPlace.this,HomeActivity.class);
                startActivity(inte);
            }
        });



        recyclerView = (RecyclerView)findViewById(R.id.roomRecyclerView1);

        placeAdapter = new PlaceAdapter(placeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(placeAdapter);


        findPlaceData();

    }

    private void findPlaceData() {

        PlaceInformation place = new PlaceInformation("Akalla- Microsoft AB (4052)","Finlandsgatan 30, Kista .","3 juli 2017","18.45 - 15.00");
        placeList.add(place);
        place = new PlaceInformation("Åkersberga - Alceahuset (0094)","Hackstavägen 22 .","7 juni 2017","9.00 - 18.00");
        placeList.add(place);
        place = new PlaceInformation("Älvsjö Industriområde- k AB (0100)","Varuvägen 7  .","18 maj 2017",	"8.30 - 15.30");
        placeList.add(place);

        place = new PlaceInformation("Arlanda- Terminal 5 (0098)","Vid parkeringshus P51 . ","2 maj 2017","9.00 - 15.00");
        placeList.add(place);

        place = new PlaceInformation("Årsta - (0123)","Byängsgränd 6","16 maj 2017 ","8.30 - 15.30");
        placeList.add(place);

        place = new PlaceInformation("Årsta- Årstaberg (4092)","Upplagsvägen 5","27 april 2017","8.00 - 16.00");
        placeList.add(place);

        place = new PlaceInformation("Bergshamra- if Försäkringsbolag (0178)","Barks väg 15 .","11 maj 2017","8.45 - 15.15");
        placeList.add(place);

        place = new PlaceInformation("Bromma- Blocks (4031)"," Ulvsundavägen 185D","9 augusti 2017","8.30 - 18.00");
        placeList.add(place);

        place = new PlaceInformation("Bromma- Hästsportens Hus (4004)","möbyvägen 5","16 maj 2017","8.30 - 15.15");
        placeList.add(place);

        place = new PlaceInformation("City- Alecta (0124)","Regeringsgatan 107 .","30 maj 2017 .","8.30 - 15.30");
        placeList.add(place);

        place = new PlaceInformation("City- Riksbanken (7003)","Malmskillnadsgatan 1-7 ."
                ,"24 juli 2017","8.30 - 15.30");
        placeList.add(place);

        place = new PlaceInformation("City- World Trade Center (7027)","Klarabergsviadukten 72 ."
                ,"5 juni 2017","8.30 - 15.30");
        placeList.add(place);


        placeAdapter.notifyDataSetChanged();
    }

}


