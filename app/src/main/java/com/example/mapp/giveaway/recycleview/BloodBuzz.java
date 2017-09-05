package com.example.mapp.giveaway.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mapp.giveaway.PlaceInformation;
import com.example.mapp.giveaway.R;

import java.util.List;

/**
 * Created by harshi on 05/04/17.
 */

public class BloodBuzz extends RecyclerView.ViewHolder {

    public TextView place;
    public TextView addressPlace;
    public TextView date;
    public TextView time;

    public BloodBuzz(View itemView) {
        super(itemView);
        place = (TextView) itemView.findViewById(R.id.place);
        addressPlace = (TextView) itemView.findViewById(R.id.placeAddress);
        date = (TextView) itemView.findViewById(R.id.date);
        time = (TextView) itemView.findViewById(R.id.time);
    }

    public BloodBuzz(final View itemView, final List<PlaceInformation> taskObject) {
        super(itemView);
        place = (TextView) itemView.findViewById(R.id.place);
        addressPlace = (TextView) itemView.findViewById(R.id.placeAddress);
        date = (TextView) itemView.findViewById(R.id.date);
        time = (TextView) itemView.findViewById(R.id.time);
    }
}