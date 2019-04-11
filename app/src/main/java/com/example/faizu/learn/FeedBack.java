package com.example.faizu.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Thanks for your FeedBack", Toast.LENGTH_SHORT).show();
                writeFirebase();

            }

        });
    
    }


    public  void writeFirebase()
    {
        String feedBack = editText.getText().toString();
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference("User");
            String userId = user.getUid().toString();

            myRef.child(userId).child("FeedBack").push().setValue(feedBack);
        }

    }
}
