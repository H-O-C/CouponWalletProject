package com.example.mylogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // They have to be same name that it is on activity_main.xml
private TextView register, forgot_password;
private EditText email , password;
private Button Login;
private FirebaseAuth mAuth;
private Button category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        Login = (Button) findViewById(R.id.Login);
        Login.setOnClickListener(this);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        forgot_password = (TextView)findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(this);

        category = (Button) findViewById(R.id.category);
        category.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this, Registraition.class));
                break;
            case R.id.Login:
                UserLogin();
                break;
            case R.id.forgot_password:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
            case R.id.category:
                startActivity(new Intent(this, Categories.class));
                break;


        }
    }

    private void UserLogin() {
        String emailVer = email.getText().toString().trim();
        String passwordVer = password.getText().toString().trim();
        if (emailVer.isEmpty()){
            email.setError("Username is required!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailVer).matches()) {
            email.setError("Please provide valid email address");
            email.requestFocus();
            return;
        }
        if (passwordVer.isEmpty()){
            password.setError("Please provide a password");
            password.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailVer,passwordVer).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        //redirect to maps page
                        startActivity(new Intent(MainActivity.this,UserProfile.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"Check email for account verification", Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(MainActivity.this, "Failed to log in check credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}