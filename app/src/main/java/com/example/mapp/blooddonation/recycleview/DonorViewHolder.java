package com.example.mapp.blooddonation.recycleview;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mapp.blooddonation.EmailHandling;
import com.example.mapp.blooddonation.Information;
import com.example.mapp.blooddonation.UserInformation;
import com.example.mapp.blooddonation.R;

import java.util.List;

import static android.R.id.message;

/**
 * Created by harshi on 27/03/17.
 */

public class DonorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView dName;
    public TextView dAge;
    public TextView dAddress;
    public ImageButton image ;
    public Context ctx;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    public DonorViewHolder(final View itemView) {
        super(itemView);
       //
        System.out.println("DonorViewHolder constructor  ");
        dName = (TextView)itemView.findViewById(R.id.donorname);
        dAge = (TextView)itemView.findViewById(R.id.address);
        dAddress = (TextView)itemView.findViewById(R.id.age);
        image = (ImageButton) itemView.findViewById(R.id.email);
        ctx = itemView.getContext();
        image.setOnClickListener(this);
        System.out.println("*******    " + ctx);
    }


    public DonorViewHolder(final View itemView, final List<UserInformation> taskObject) {
        super(itemView);
        System.out.println("DonorViewHolder constructor  ");
        dName = (TextView)itemView.findViewById(R.id.donorname);
        dAge = (TextView)itemView.findViewById(R.id.address);
        dAddress = (TextView)itemView.findViewById(R.id.age);
        image = (ImageButton) itemView.findViewById(R.id.email);
        ctx = itemView.getContext();
        image.setOnClickListener(this);
        System.out.println("******* 2   " + ctx);
    }


    @Override
    public void onClick(View v) {
        Intent mIntent = new Intent(ctx, EmailHandling.class);
        mIntent.putExtra("email",email);
        ctx.startActivity(mIntent);

    }
}

