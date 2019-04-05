package com.example.faizu.learn;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button button;
    TextView textView;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textview);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void registerUser()
    {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if(Email.isEmpty())
        {
            email.setError("Required");
            email.requestFocus();
            return;

        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            email.setError("Enter a valid Email");
            email.requestFocus();
            return;
        }
        if(Password.isEmpty())
        {
            password.setError("Required");
            password.requestFocus();
            return;

        }
        if(Password.length()<6)
        {
            password.setError("Minimum length of a password should be 6");
            password.requestFocus();
            return;
        }
        progressDialog.setMessage("Registering...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Registered Succeeded",Toast.LENGTH_SHORT).show();
                    createUserProfile(task.getResult().getUser());
                }
                else
                {
                    if(task.getException()instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"Already Registered",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"User Authentication failed",Toast.LENGTH_SHORT).show();
                    }



                }
                progressDialog.cancel();
            }
        });


    }

    private  void createUserProfile(FirebaseUser user)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        String userId = user.getUid();
        String email = user.getEmail();
        myRef.child(userId).child("Name").setValue("null");
        myRef.child(userId).child("Email").setValue(email);
        myRef.child(userId).child("Phone").setValue("null");
        myRef.child(userId).child("Address").setValue("null");

    }

}
