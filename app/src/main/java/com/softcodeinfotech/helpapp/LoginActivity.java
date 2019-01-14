package com.softcodeinfotech.helpapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcodeinfotech.helpapp.response.SigninResponse;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.DataValidation;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    String mEmail, mPassword, mForgotPass, mSignup;
    Button login;
    TextView forgotPass, signup;
    ProgressBar pBar;
    ImageButton back;

    Retrofit retrofit;
    ServiceInterface serviceInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpWidget();
        pBar.setVisibility(View.GONE);
        //getData();

        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceInterface = retrofit.create(ServiceInterface.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotpassIntent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(forgotpassIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

                if (DataValidation.isNotValidEmail(mEmail)) {
                    Toast.makeText(LoginActivity.this, "Fill Valid Email", Toast.LENGTH_SHORT).show();
                } else if (mPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fill email", Toast.LENGTH_SHORT).show();
                } else {
                    pBar.setVisibility(View.VISIBLE);

                    signinReq();
                    Toast.makeText(LoginActivity.this, "" + mEmail + " " + mPassword, Toast.LENGTH_SHORT).show();

                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pBar.setVisibility(View.GONE);
                Intent signupIntent = new Intent(LoginActivity.this, EmailActivity.class);
                startActivity(signupIntent);
                finish();
            }
        });
    }

    private void signinReq() {
        Call<SigninResponse> call = serviceInterface.userlogin(convertPlainString(mEmail), convertPlainString(mPassword));
        call.enqueue(new Callback<SigninResponse>() {
            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                if (response.body().getStatus() == 1) {
                    pBar.setVisibility(View.GONE);
                    //Toast.makeText(LoginActivity.this, "userid"+response.body().getInformation().getUserId(), Toast.LENGTH_SHORT).show();


                    Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                //Toast.makeText(LoginActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        mEmail = email.getText().toString().trim();
        mPassword = password.getText().toString().trim();
    }

    private void setUpWidget() {
        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        forgotPass = findViewById(R.id.textView10);
        signup = findViewById(R.id.textView6);
        login = findViewById(R.id.button3);
        pBar = findViewById(R.id.progressBar2);
        back = findViewById(R.id.imageButton3);
    }

    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
