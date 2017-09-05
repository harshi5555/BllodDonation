package com.example.mapp.giveaway;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.R.attr.category;
import static android.R.attr.data;
import static com.example.mapp.giveaway.R.id.imageView;


public class DonorsPage extends AppCompatActivity implements View.OnClickListener{
    private static final int PICK_IMAGE_REQUEST = 234;
    EditText donorDesc;
    ImageView donorImage;
    Button donorUplaodBtn;
    ImageButton donorImgBtn;
    EditText textCont;
    EditText item;
    Uri imageUri;
    ProgressDialog progressDialog;
    private final int SELECT_PHOTO = 1;
    Bitmap selectedImage;
    String selectedImageStr = null;
    FirebaseAuth firebaseAuth;
    EditText location;
    Uri imgFilePath;
    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    private Uri filePath;
    Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_page);
        spinner1 = (Spinner) findViewById(com.example.mapp.giveaway.R.id.spinner1);
        item =(EditText)findViewById(R.id.itemDes);
        donorDesc = (EditText) findViewById(R.id.donorDesId);
        donorImage = (ImageView) findViewById(R.id.donorImageId);
        donorUplaodBtn = (Button) findViewById(R.id.donorUploadBtnId);
        donorImgBtn = (ImageButton) findViewById(R.id.donorImgBtnId);
        textCont = (EditText) findViewById(R.id.textContact);
        location=(EditText)findViewById(R.id.location);

        System.out.println("onCreate start ");
        firebaseAuth = FirebaseAuth.getInstance();
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array. category_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
             getSupportActionBar().setTitle("    Status");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //toolbar.setLogo(R.drawable.ic_action_back);
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
            toolbar.setTitleTextColor(Color.WHITE);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        donorImgBtn.setOnClickListener(this);
        donorUplaodBtn.setOnClickListener(this);}


        private void showFileChooser() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    donorImage .setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


      public String getFileExtension(Uri uri) {
          ContentResolver cR = getContentResolver();
          MimeTypeMap mime = MimeTypeMap.getSingleton();
          return mime.getExtensionFromMimeType(cR.getType(uri));
      }



    private void uploadFile() {
        //checking if file is available
        if (filePath != null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            StorageReference sRef = storageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                            String category ="";
                            try {
                               category = spinner1.getSelectedItem().toString().trim();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //creating the upload object to store uploaded image details
                            Upload upload = new Upload(donorDesc.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString(),category,textCont.getText().toString().trim(),location.getText().toString().trim(),item.getText().toString().trim());

                            //adding an upload to firebase database
                            String uploadId = mDatabase.push().getKey();
                            mDatabase.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) 50) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected
        }
    }


    @Override
    public void onClick(View v) {

        if (v == donorImgBtn) {
            showFileChooser();
        } else if (v == donorUplaodBtn) {
            uploadFile();
        }
    }
    }







