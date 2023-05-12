package com.example.signuploginfirebase;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView profileImageView;
    private EditText nameEditText, ageEditText, addressEditText, phoneEditText;
    private Button changeProfileImageButton, saveProfileButton;

    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile pictures").child(currentUser.getUid() + ".jpg");

        profileImageView = findViewById(R.id.profile_image);
        nameEditText = findViewById(R.id.name_edit_text);
        ageEditText = findViewById(R.id.age_edit_text);
        addressEditText = findViewById(R.id.address_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        changeProfileImageButton = findViewById(R.id.change_profile_image_button);
        saveProfileButton = findViewById(R.id.save_profile_button);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String age = dataSnapshot.child("age").getValue(String.class);
                String address = dataSnapshot.child("address").getValue(String.class);
                String phone = dataSnapshot.child("phone").getValue(String.class);
                nameEditText.setText(name);
                ageEditText.setText(age);
                addressEditText.setText(address);
                phoneEditText.setText(phone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });
    }}