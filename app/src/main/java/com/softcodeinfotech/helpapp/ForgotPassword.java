package com.softcodeinfotech.helpapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.softcodeinfotech.helpapp.util.DataValidation;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    Button send;
    String mEmail;
    ImageButton getBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setUpWidget();
        getData();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataValidation.isNotValidEmail(mEmail)) {
                    Toast.makeText(ForgotPassword.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();

                } else {
                    seneq();
                }
            }
        });

        getBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    private void seneq() {
        Toast.makeText(this, "Call serever for mail", Toast.LENGTH_SHORT).show();
    }


    private void getData() {
        mEmail = email.getText().toString().trim();
    }

    private void setUpWidget() {

        email = findViewById(R.id.editText);
        send = findViewById(R.id.button3);
        getBack = findViewById(R.id.imageButton3);

    }
}
