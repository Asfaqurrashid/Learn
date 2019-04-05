package com.example.faizu.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingAcitivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_acitivity);
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Applying", Toast.LENGTH_SHORT).show();
                int radioId = radioGroup.getCheckedRadioButtonId();
                int id = 0;
                if(radioId == R.id.green)
                {
                    id = 1;
                }
                 if(radioId == R.id.blue)
                {
                    id = 2;
                }
                 if(radioId == R.id.coloraccent)
                 {
                     id = 3;
                 }
                 if(radioId == R.id.gray)
                 {
                     id = 4;
                 }
                Intent intent = new Intent(getApplicationContext(),itemActivity.class);
                 intent.putExtra("EXTRA_NUMBER",id);
                 startActivity(intent);
            }
        });
    }
}
