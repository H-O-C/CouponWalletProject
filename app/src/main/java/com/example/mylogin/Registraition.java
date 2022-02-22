package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registraition extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText Register_Username , Register_FullName, Register_EmailAddress, Register_Password;
    private Button Register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraition);

        mAuth = FirebaseAuth.getInstance();

        Register_button = (Button) findViewById(R.id.Register_button);
        Register_button.setOnClickListener(this);
        Register_Username = (EditText)  findViewById(R.id.Register_FirstName);
        Register_FullName = (EditText) findViewById(R.id.Register_LastName);
        Register_EmailAddress = (EditText) findViewById(R.id.Register_EmailAddress);
        Register_Password = (EditText) findViewById(R.id.Register_Password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Register_button:
            Register_button();
            break;
        }
    }
    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void Register_button() {
        String email = Register_EmailAddress.getText().toString().trim();
        String first_name = Register_Username.getText().toString().trim();
        String last_name = Register_FullName.getText().toString().trim();
        String password = Register_Password.getText().toString().trim();

        if (first_name.isEmpty()){
            Register_Username.setError("First Name is required!");
            Register_Username.requestFocus();
            return;
        }
        if (last_name.isEmpty()){
            Register_FullName.setError("Last name is required!");
            Register_FullName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            Register_EmailAddress.setError("Email is required!");
            Register_EmailAddress.requestFocus();
            return;
        }
        if (password.isEmpty()){
            Register_Password.setError("Password is required!");
            Register_Password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Register_EmailAddress.setError("Please provide valid email address");
            Register_EmailAddress.requestFocus();
            return;
        }
        if (password.length()<6){
            Register_Password.setError("Password has to be at least 6 characters long");
            Register_Password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(first_name,last_name,email,password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Registraition.this, "User has been register successfully!", Toast.LENGTH_LONG).show();
                                        switchActivities();
                                    }else {
                                        Toast.makeText(Registraition.this, "Failed to register try again!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(Registraition.this, "Failed to register!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}