package com.softcodeinfotech.helpapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcodeinfotech.helpapp.response.PasswordResponse;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailVerifyActivity extends AppCompatActivity {

    TextInputEditText otp, password, retypePassword;
    TextInputLayout otpLayout;
    Button procced, savepassword;
    String mOtp, mPassword, mRetypepassword;

    //intent data
    String intentEmail, intentOtp;

    Retrofit retrofit;
    ConstraintLayout rootlayout;

    ServiceInterface serviceInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verify);

        setupWidget();
        getData();
        password.setVisibility(View.GONE);
        retypePassword.setVisibility(View.GONE);
        savepassword.setVisibility(View.GONE);

       /* intentEmail = getIntent().getStringExtra("email");
        intentOtp = getIntent().getStringExtra("otp");
*/
        intentEmail = SharePreferenceUtils.getInstance().getString("USER_email");
        intentOtp = SharePreferenceUtils.getInstance().getString("USER_otp");

        //Retrofit
        //pBar.setVisibility(View.GONE);

        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceInterface = retrofit.create(ServiceInterface.class);

        procced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                if (intentOtp.equals(mOtp)) {

                    password.setVisibility(View.VISIBLE);
                    retypePassword.setVisibility(View.VISIBLE);
                    savepassword.setVisibility(View.VISIBLE);
                    //
                    otp.setVisibility(View.GONE);
                    procced.setVisibility(View.GONE);
                    otpLayout.setVisibility(View.GONE);


                } else {
                    Toast.makeText(EmailVerifyActivity.this, "Invalid otp" + intentOtp, Toast.LENGTH_SHORT).show();
                }

            }
        });

        savepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                if (mPassword.equals(mRetypepassword)) {

                    saveDataReq();

                } else {
                    Toast.makeText(EmailVerifyActivity.this, "Password mismatch please retype", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveDataReq() {

        Call<PasswordResponse> call = serviceInterface.savePassword(convertPlainString(intentEmail), convertPlainString(mPassword));

        call.enqueue(new Callback<PasswordResponse>() {
            @Override
            public void onResponse(Call<PasswordResponse> call, Response<PasswordResponse> response) {
                if (response.body().getStatus() == 1) {
                    Toast.makeText(EmailVerifyActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EmailVerifyActivity.this, ProfilesActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PasswordResponse> call, Throwable t) {
                Toast.makeText(EmailVerifyActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getData() {
        mOtp = otp.getText().toString().trim();
        mPassword = password.getText().toString().trim();
        mRetypepassword = retypePassword.getText().toString().trim();
    }

    private void setupWidget() {
        otp = findViewById(R.id.otp);
        password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retypePassword);
        procced = findViewById(R.id.proceed);
        savepassword = findViewById(R.id.setPassword);
        otpLayout = findViewById(R.id.textInputLayout2);
    }

    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
