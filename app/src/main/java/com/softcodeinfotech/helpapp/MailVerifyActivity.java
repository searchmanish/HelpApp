package com.softcodeinfotech.helpapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

public class MailVerifyActivity extends AppCompatActivity {

    EditText otp;
    Button verify;
    //intent data
    String intentEmail, intentOtp;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_verify);

        setupWidget();
        intentEmail = SharePreferenceUtils.getInstance().getString("USER_email");
        intentOtp = SharePreferenceUtils.getInstance().getString("USER_otp");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otp.getText().toString().isEmpty()) {
                    Toast.makeText(MailVerifyActivity.this, "Enter otp first", Toast.LENGTH_SHORT).show();
                } else {
                    if (otp.getText().toString().equals(intentOtp) ) {
                        Intent profileIntent = new Intent(MailVerifyActivity.this, ProfilesActivity.class);
                        startActivity(profileIntent);
                        finish();
                    } else {
                        Toast.makeText(MailVerifyActivity.this, "Incorrect otp", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void setupWidget() {

        otp = findViewById(R.id.editText);
        verify = findViewById(R.id.button3);
        back = findViewById(R.id.imageButton3);
    }


}
