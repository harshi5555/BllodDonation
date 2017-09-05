package com.example.mapp.giveaway;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    Button buttonSignup;
    EditText editTextName;
    EditText editTextPassword1;
    EditText editTextAge;
    EditText editTextPhone;
    EditText editTextAddress;
    EditText editTextCity;
    EditText editTextEmail1;
   // Spinner spinner;
    Spinner spinner1;
    String donorDB = "donor";
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    private Firebase  mRootRef;
    String msg = "";
    UserInformation userInformation ;
    boolean isSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mapp.giveaway.R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        initialize();



        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                com.example.mapp.giveaway.R.array.bloodGroup_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                com.example.mapp.giveaway.R.array.Gender_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

    }
    private void initialize(){
        buttonSignup = (Button)findViewById(com.example.mapp.giveaway.R.id.singup1);
        editTextEmail1 =(EditText)findViewById(com.example.mapp.giveaway.R.id.email1);
        editTextPassword1 =(EditText)findViewById(com.example.mapp.giveaway.R.id.password1);

        editTextName =(EditText)findViewById(com.example.mapp.giveaway.R.id.name);
        editTextAge =(EditText)findViewById(com.example.mapp.giveaway.R.id.age);

        editTextAddress =(EditText)findViewById(com.example.mapp.giveaway.R.id.adress);
        editTextPhone =(EditText)findViewById(com.example.mapp.giveaway.R.id.phone);
        editTextCity =(EditText)findViewById(R.id.city);

       // spinner = (Spinner) findViewById(com.example.mapp.giveaway.R.id.spinner2);
        spinner1 = (Spinner) findViewById(com.example.mapp.giveaway.R.id.spinner1);
        databaseReference = FirebaseDatabase.getInstance().getReference("Donors");
        databaseReference.child(donorDB);
        buttonSignup.setOnClickListener(this);
    }

    private void saveUserInformation(){

        String name  = editTextName.getText().toString().trim();
        String age  = editTextAge.getText().toString().trim();
        String address  = editTextAddress.getText().toString().trim();
        String city  = editTextCity.getText().toString().trim();
        String phone  = editTextPhone.getText().toString().trim();
        String email  = editTextEmail1.getText().toString().trim();
        String password  = editTextPassword1.getText().toString().trim();
        String gender= "";
        String bloodGroup = "";

        try {
            gender  = spinner1.getSelectedItem().toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
           // bloodGroup = spinner.getSelectedItem().toString().trim();
        } catch (Exception e){
            e.printStackTrace();
        }

        userInformation = new UserInformation(city,address,age, bloodGroup,email,gender,name,password, phone  );

        System.out.println(email);
        System.out.println(password);

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                System.out.println("*****************");
                System.out.println(task);

                if( task != null && task.getException() != null){
                    msg = task.getException().getMessage();
                }else{
                    msg = "no exception" ;
                }
                System.out.println("***************** msg "+msg);

                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user != null){
                        String id = databaseReference.push().getKey();

                        databaseReference.child(id).setValue(userInformation);
                        isSignUp = true;
                    }
                    System.out.println("User creation is =========== "+user);
                    // Toast.makeText(this,"Information Saved....",Toast.LENGTH_LONG).show();
                    System.out.println("User creation is successful!!!");
                    System.out.println (firebaseAuth.getCurrentUser());

                    startActivity(new Intent(getApplicationContext(),Login.class));


                }else{
                    System.out.println("User creation failed!!!");
                    isSignUp =false;
                }
            }
        });


        Toast.makeText(this,"Information Saved....",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        if(v == buttonSignup){
            saveUserInformation();
        }
    }
}























