package com.example.mapp.giveaway;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.Telephony;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by harshi on 29/08/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Upload> uploads;

    public MyAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Upload upload = uploads.get(position);

        holder.textViewName.setText(upload.getName());
       // holder.contact.setText(upload.getContact());
       // holder.catagory.setText(upload.getCategory());
        holder.location.setText(upload.getLocation());
        holder.itemDon.setText(upload.getItem());


        Glide.with(context).load(upload.getUrl()).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageView;
       // public TextView catagory;
        public TextView contact;
        public TextView location;
        public Button message;
        public Context ctx;
        public TextView itemDon;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        private String email;

        public ViewHolder(View itemView) {
            super(itemView);
            ctx = itemView.getContext();
            itemDon =(TextView)itemView.findViewById(R.id.item);
            location = (TextView)itemView.findViewById(R.id.locationTxt);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            //catagory = (TextView)itemView.findViewById(R.id.itemCatagory);
            contact = (TextView)itemView.findViewById(R.id.contact);
            message=(Button)itemView.findViewById(R.id.message);
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(ctx, SmsActivity.class);
                    mIntent.putExtra("email",email);
                    ctx.startActivity(mIntent);
                }
            });
        }


    }




}

