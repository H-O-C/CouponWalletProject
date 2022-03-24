package com.example.mylogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//Settings allows a user to delete their account as of 2/15/22
public class Settings extends AppCompatActivity {
    Button delAccount;
    Button changePsswd;
    Button _logOut;
    Button _coupons;
    Button _nearbyStores;
    ImageButton myLogo;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        _coupons = findViewById(R.id.button);
        _nearbyStores = findViewById(R.id.button2);
        changePsswd = findViewById(R.id.button3);
        delAccount = findViewById(R.id.button4);
        _logOut = findViewById(R.id.button5);
        myLogo = findViewById(R.id.imageButton);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        delAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Creates dialog box to delete account
                AlertDialog.Builder dialog = new AlertDialog.Builder(Settings.this);
                dialog.setTitle("Are You Sure?");
                dialog.setMessage("This can not be undone");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        firebaseUser.delete();
                        startActivity(new Intent(Settings.this, MainActivity.class));
                    }
                });
                dialog.show();
            }
        });
        _coupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this, Categories.class));
            }
        });
        _nearbyStores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this, MapsActivity.class));
            }
        });
        changePsswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this, ForgotPassword.class));
            }
        });
        myLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this, Settings2.class));
            }
        });
        _logOut.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }));
    }
};