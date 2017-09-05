package com.example.mapp.giveaway;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;


public class ImageDonation extends AppCompatActivity {
private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ImageView imageView;
    private EditText description;
    private ImageButton imageButton;
    private Uri imguri;
    private Spinner spinner;
    public static final String FB_Storage_path ="image/";
    public static final String FB_Database_path ="image/";

    public static final int Request_code = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_donation);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        imageView= (ImageView)findViewById(R.id.donorImageId);
        description=(EditText)findViewById(R.id.donorDesId);
        imageButton = (ImageButton) findViewById(R.id.donorImgBtnId);

    }
}
