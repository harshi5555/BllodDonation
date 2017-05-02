package com.example.mapp.blooddonation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by harshi on 19/04/17.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {

    private List<PlaceInformation>placeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView place, addressPlace, date,time;

        public MyViewHolder(View view) {
            super(view);
            place = (TextView) itemView.findViewById(R.id.place);
            addressPlace = (TextView) itemView.findViewById(R.id.placeAddress);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);

        }
    }

    public PlaceAdapter(List<PlaceInformation> placeList) {

        this.placeList = placeList;
    }
    @Override
    public PlaceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaceAdapter.MyViewHolder holder, int position) {
      PlaceInformation place  = placeList.get(position);
        holder.place.setText(place.getPlace());
        holder.addressPlace.setText(place.getAddress());
        holder.date.setText(place.getDate());
        holder.time.setText(place.getTime());
    }

    @Override
    public int getItemCount() {

        return placeList.size();
    }
}
