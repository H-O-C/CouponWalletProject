package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Settings2 extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;

    FirebaseUser user;
    DatabaseReference reference; // user, reference, and userID will be needed to display the user info/email, name.
    String userID;
    ImageButton newSettings;// Settings activity button

    StorageReference storageReference;
    ImageView imageToUpload; // Profile Image
    Button bUploadImage; //Select Photo Button
    Button saveImage;
    Uri selectedImage;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);
        newSettings = (ImageButton) findViewById(R.id.settingsBtn);

        imageToUpload = (ImageView) findViewById(R.id.imageToUpload);
        saveImage = (Button) findViewById(R.id.button7);
        bUploadImage = (Button) findViewById(R.id.button6);
        saveImage.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser(); // gets current user
        reference = FirebaseDatabase.getInstance().getReference("Users"); // the reference get the known data base path "Users"
        userID = user.getUid(); // To get the directory after "Users" you need the userID which is different and automatically generated for each user. From the userID directory, you can access the user data
        final TextView emailAddress = (TextView) findViewById(R.id.EmailTxt); // the final text to display on user profile
        final TextView _name = (TextView) findViewById((R.id.NameTxt));
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageToUpload);
            }
        });
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

        newSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings2.this, Settings.class));
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button6:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.button7:
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading File.....");
                progressDialog.show();

                final StorageReference fileRef = storageReference.child("profile.jpg");
                fileRef.putFile(selectedImage)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(Settings2.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                if(progressDialog.isShowing())
                                    progressDialog.dismiss();
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Picasso.get().load(uri).into(imageToUpload);
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(Settings2.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }
}