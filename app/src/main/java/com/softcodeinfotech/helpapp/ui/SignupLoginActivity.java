package com.softcodeinfotech.helpapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softcodeinfotech.helpapp.R;

public class SignupLoginActivity extends AppCompatActivity {
    Button Signup, Login;
    TextView skip;

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

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mainIntent = new Intent(SignupLoginActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

            }
        });
    }


    private void setUpWidget() {
        Signup = findViewById(R.id.button);
        Login = findViewById(R.id.button2);
        skip = findViewById(R.id.textView15);

    }
}
