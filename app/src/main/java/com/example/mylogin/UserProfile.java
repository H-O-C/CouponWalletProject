package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity
{
    FirebaseUser user;
    DatabaseReference reference; // user, reference, and userID will be needed to display the user info/email, name.
    String userID;

    Button category; // Categories activity button
    Button mySettings; // Settings activity button
    Button myLocation; // set the user location
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mySettings = (Button) findViewById(R.id.testBtn);
        myLocation = (Button) findViewById(R.id.location);
        category = (Button) findViewById(R.id.category);
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

        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this, Settings.class));
            }
        });
        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this, MapsActivity.class));
            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this, Categories.class));
            }
        });

    }



}