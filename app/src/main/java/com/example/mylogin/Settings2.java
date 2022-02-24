package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings2 extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;

    FirebaseUser user;
    DatabaseReference reference; // user, reference, and userID will be needed to display the user info/email, name.
    String userID;

    ImageView imageToUpload; //Upload image

    Button category; // Categories activity button
    Button mySettings; // Settings activity button
    Button myLocation; // set the user location
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);
        imageToUpload = (ImageView) findViewById(R.id.imageToUpload);
        user = FirebaseAuth.getInstance().getCurrentUser(); // gets current user
        reference = FirebaseDatabase.getInstance().getReference("Users"); // the reference get the known data base path "Users"
        userID = user.getUid(); // To get the directory after "Users" you need the userID which is different and automatically generated for each user. From the userID directory, you can access the user data
        final TextView emailAddress = (TextView) findViewById(R.id.EmailTxt); // the final text to display on user profile
        final TextView _name = (TextView) findViewById((R.id.NameTxt));
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() { // function for displaying user info
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    String fullName = userProfile.first_name;
                    String email = userProfile.email;
                    _name.setText(fullName);
                    emailAddress.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageToUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }
}