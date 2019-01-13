package com.softcodeinfotech.helpapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcodeinfotech.helpapp.response.ForgotpassResponse;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.DataValidation;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    Button send;
    String mEmail;
    ImageButton getBack;

    Retrofit retrofit;
    ServiceInterface serviceInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setUpWidget();
        getData();

        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceInterface = retrofit.create(ServiceInterface.class);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                if (DataValidation.isNotValidEmail(mEmail)) {
                    Toast.makeText(ForgotPassword.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();

                } else {
                    getData();
                    sendReq();
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


    private void sendReq() {
        Call<ForgotpassResponse> call = serviceInterface.forgotPassword(convertPlainString(mEmail));
        call.enqueue(new Callback<ForgotpassResponse>() {
            @Override
            public void onResponse(Call<ForgotpassResponse> call, Response<ForgotpassResponse> response) {
                Toast.makeText(ForgotPassword.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                if (response.body().getStatus() == 1) {
                    Log.i("sucess", response.body().getMsg());
                    Intent loginIntent = new Intent(ForgotPassword.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ForgotpassResponse> call, Throwable t) {
                Toast.makeText(ForgotPassword.this, "" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

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

    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
