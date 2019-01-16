package com.softcodeinfotech.helpapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcodeinfotech.helpapp.response.PasswordUpdateResponse;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountActivity extends AppCompatActivity {

    ImageButton back;
    ProgressBar pBar;
    String mEmail, mPassword;
    TextView logout;
    Button updatePassword;
    TextInputEditText password;
    Retrofit retrofit;
    ServiceInterface serviceInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setUpWidget();
        getData();


        //Retrofit
        //pBar.setVisibility(View.GONE);
        pBar.setVisibility(View.GONE);

        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceInterface = retrofit.create(ServiceInterface.class);

        mEmail = SharePreferenceUtils.getInstance().getString(Constant.USER_email);
       // Toast.makeText(this, "" + mEmail, Toast.LENGTH_SHORT).show();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePreferenceUtils.getInstance().deletePref();
                SharePreferenceUtils.getInstance().saveString(Constant.USER_profilestatus, "1");
                Intent signuploginIntent = new Intent(AccountActivity.this, SignupLoginActivity.class);
                startActivity(signuploginIntent);
                finishAffinity();
            }
        });

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

                if (mPassword.isEmpty()) {
                    Toast.makeText(AccountActivity.this, "Fill Password", Toast.LENGTH_SHORT).show();
                } else {
                    pBar.setVisibility(View.VISIBLE);
                    getData();
                    senDataReq();
                }
            }
        });
    }

    private void senDataReq() {
        Call<PasswordUpdateResponse> call = serviceInterface.passwordUpdate(convertPlainString(mEmail),
                convertPlainString(mPassword));
        call.enqueue(new Callback<PasswordUpdateResponse>() {
            @Override
            public void onResponse(Call<PasswordUpdateResponse> call, Response<PasswordUpdateResponse> response) {
                pBar.setVisibility(View.GONE);
                if (response.body().getStatus().equals(1)) {

                    Toast.makeText(AccountActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    SharePreferenceUtils.getInstance().saveString(Constant.USER_password, response.body().getInformation().getPassword());
                } else {
                    Toast.makeText(AccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasswordUpdateResponse> call, Throwable t) {
                pBar.setVisibility(View.GONE);

            }
        });


    }

    private void getData() {
        mPassword = password.getText().toString().trim();
    }

    private void setUpWidget() {
        back = findViewById(R.id.imageButton4);
        pBar = findViewById(R.id.progressBar);
        password = findViewById(R.id.updatePasswordEditText);
        updatePassword = findViewById(R.id.button7);
        logout = findViewById(R.id.textView75);
    }

    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
