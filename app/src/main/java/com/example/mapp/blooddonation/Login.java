package com.example.mapp.blooddonation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity implements View.OnClickListener {
    Button buttonSignin;
    EditText editTextEmail;
    EditText editTextPassword;
    TextView textViewSignup;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mapp.blooddonation.R.layout.activity_login);
        initialize();

        /*firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }*/
    }



    private void initialize(){
        buttonSignin = (Button)findViewById(com.example.mapp.blooddonation.R.id.login);
        editTextEmail =(EditText)findViewById(com.example.mapp.blooddonation.R.id.email);
        editTextPassword =(EditText)findViewById(com.example.mapp.blooddonation.R.id.password);
        textViewSignup = (TextView)findViewById(com.example.mapp.blooddonation.R.id.singup);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        textViewSignup.setOnClickListener(this);
        buttonSignin.setOnClickListener(this);    }

    private void registerUser(){
        String email =editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
      //  progressDialog.setMessage("Registering User...");
        //progressDialog.show();
     /*
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Registered Successfully",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(Login.this,"Could not register. Please try again",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.hide();
                    }
                });
*/

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }else {
                    Toast.makeText(Login.this,"Could not Login. Please check your credentials",Toast.LENGTH_LONG).show();
                }
                progressDialog.hide();
            }
        });
       // firebaseAuth.signOut();
    }

    @Override
    public void onClick(View v) {

        if(v == buttonSignin){
            registerUser();
        }
        if(v==textViewSignup){
            Intent intent = new Intent(Login.this,SignUp.class);
            startActivity(intent);
        }


    }
}