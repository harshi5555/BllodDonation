package com.example.harshi.blooddonation;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
           /*Button buttonSignup;
           EditText editTextName;
           EditText editTextPassword1;
           EditText textViewAge;
           EditText editTextGender;
           EditText editTextAddress;
            EditText textView;

    ProgressDialog progressDialog;
           FirebaseAuth firebaseAuth;  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bloodGroup_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Gender_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);


    }

    /*pri// Apply the adapter to the spinnervate void initialize(){
        bspinner.setAdapter(adapter);uttonSignin = (Button)findViewById(R.id.login);
        editTextEmail =(EditText)findViewById(R.id.email);
        editTextPassword =(EditText)findViewById(R.id.password);
        textViewSignup = (TextView)findViewById(R.id.singup);

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
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this,"Registered Successfully",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(SignUp.this,"Could not register. Please try again",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }


      @Override
    public void onClick(View v) {

          if (v == buttonSignin) {
              registerUser();
          }
          if (v == textViewSignup) {

          }
      } */

}




















