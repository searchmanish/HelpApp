package com.softcodeinfotech.helpapp.ui;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcodeinfotech.helpapp.R;
import com.softcodeinfotech.helpapp.ServiceInterface;
import com.softcodeinfotech.helpapp.beanresponse.GetmobileverifyResponse;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.DataValidation;
import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignupActivity extends AppCompatActivity {
    EditText mobile, password, username;
    Button submit;
    ImageButton back;
    TextView alreadymember;
    String mMobile, mPassword, mUsername;
    String val;
    ProgressBar pBar;

    //gmail facebook Integration

    ImageView gmail,facebook;

    Retrofit retrofit;

    ConstraintLayout rootlayout;

    ServiceInterface serviceInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setUpWidget();
        getdata();

        pBar.setVisibility(View.GONE);


        //Retrofit
        //pBar.setVisibility(View.GONE);

        Gson gson = new GsonBuilder().create();

        //okhttp client
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        serviceInterface = retrofit.create(ServiceInterface.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        alreadymember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinIntent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(signinIntent);
                finish();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMobile = mobile.getText().toString().trim();
                getdata();

                if (DataValidation.isValidPhoneNumber(mMobile)) {
                    Toast.makeText(SignupActivity.this, "Input valid mobile number", Toast.LENGTH_SHORT).show();
                } else if (mPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Input valid password", Toast.LENGTH_SHORT).show();

                } else if (mUsername.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Input valid Username", Toast.LENGTH_SHORT).show();

                } else {
                    int randomPIN = (int) (Math.random() * 9000) + 1000;
                    val = "" + randomPIN;
                    pBar.setVisibility(View.VISIBLE);
                    sendDataReq();


                }
            }
        });


        //gmail
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Gmail", Toast.LENGTH_SHORT).show();
            }
        });

        //Facebook
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "facebook", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getdata() {
        mMobile = mobile.getText().toString().trim();
        mUsername = username.getText().toString().trim();
        mPassword = password.getText().toString().trim();

    }

    private void sendDataReq() {

        Call<GetmobileverifyResponse> call = serviceInterface.mobileVerify(convertPlainString(mMobile), convertPlainString(val), convertPlainString(mUsername), convertPlainString(mPassword));
        call.enqueue(new Callback<GetmobileverifyResponse>() {
            @Override
            public void onResponse(Call<GetmobileverifyResponse> call, Response<GetmobileverifyResponse> response) {

                if (response.body().getStatus() == 1) {
                    pBar.setVisibility(View.GONE);
                    SharePreferenceUtils.getInstance().saveString("USER_otp", response.body().getInformation().getOtp());
                    SharePreferenceUtils.getInstance().saveString("USER_mobile", response.body().getInformation().getMobile());
                    SharePreferenceUtils.getInstance().saveString("USER_name", response.body().getInformation().getName());
                    SharePreferenceUtils.getInstance().saveString("USER_password", response.body().getInformation().getPassword());

                    Intent intent = new Intent(SignupActivity.this, MailVerifyActivity.class);
                    intent.putExtra("mobile", mMobile);
                    intent.putExtra("otp", val);
                    startActivity(intent);
                    finish();


                    // Toast.makeText(SignupActivity.this, "" + response.body().getMsg() + " " + val, Toast.LENGTH_SHORT).show();
                } else {
                    pBar.setVisibility(View.GONE);

                    Toast.makeText(SignupActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<GetmobileverifyResponse> call, Throwable t) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void setUpWidget() {

        mobile = findViewById(R.id.editText);
        username = findViewById(R.id.editText3);
        password = findViewById(R.id.editText2);
        submit = findViewById(R.id.button3);
        alreadymember = findViewById(R.id.textView10);
        pBar = findViewById(R.id.progressBar2);
        back = findViewById(R.id.imageButton3);

        //
        gmail = findViewById(R.id.imageView7);
        facebook = findViewById(R.id.imageView8);
    }

    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
