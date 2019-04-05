package com.example.faizu.learn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserProfileChangeRequest;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AccountFragment extends Fragment {
    public AccountFragment() {
        // constructor
    }
    TextView name,phone,email,address;
    EditText nameInput,phoneInput,emailInput,addressInput;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_account,container,false);

        name = (TextView)rootView.findViewById(R.id.name);
        phone = (TextView)rootView.findViewById(R.id.phone);
        email = (TextView)rootView.findViewById(R.id.email);
        address = (TextView)rootView.findViewById(R.id.address);
        nameInput = (EditText)rootView.findViewById(R.id.nameInput);
        phoneInput = (EditText)rootView.findViewById(R.id.phoneInput);
        emailInput = (EditText)rootView.findViewById(R.id.emailInput);
        addressInput = (EditText)rootView.findViewById(R.id.addressInput);
        button = (Button)rootView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Updating", Toast.LENGTH_SHORT).show();
                updateProfile();
            }
        });


        return rootView;
    }

    public void updateProfile() {
        final String Name = name.getText().toString();
        final String Phone = phone.getText().toString();
        final String Email = email.getText().toString();
        final String Address = address.getText().toString();

           final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(user.getDisplayName()).build();
        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("User");
                    String userId = user.getUid();
                    myRef.child(userId).child("Name").setValue(Name);
                    myRef.child(userId).child("Email").setValue(Email);
                    myRef.child(userId).child("Phone").setValue(Phone);
                    myRef.child(userId).child("Address").setValue(Address);

                }
                else
                {
                    Toast.makeText(getActivity(),"Error", Toast.LENGTH_SHORT).show();;
                }

            }
        });



        }


}
