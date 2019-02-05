package com.softcodeinfotech.helpapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softcodeinfotech.helpapp.R;

public class SignupLoginActivity extends AppCompatActivity {
    Button Signup, Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);

        setUpWidget();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(SignupLoginActivity.this, SignupActivity.class);
                startActivity(signupIntent);
               // finish();

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(SignupLoginActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();

            }
        });
    }

    private void setUpWidget() {
        Signup = findViewById(R.id.button);
        Login = findViewById(R.id.button2);

    }
}
