package com.softcodeinfotech.helpapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
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
import com.softcodeinfotech.helpapp.response.EmailResponse;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.DataValidation;
import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailActivity extends AppCompatActivity {
    EditText email, password, username;
    Button submit;
    ImageButton back;
    TextView alreadymember;
    String mEmail, mPassword, mUsername;
    String val;
    ProgressBar pBar;

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

        alreadymember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinIntent = new Intent(EmailActivity.this,LoginActivity.class);
                startActivity(signinIntent);
                finish();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEmail = email.getText().toString().trim();
                getdata();

                if (DataValidation.isNotValidEmail(mEmail)) {
                    Toast.makeText(EmailActivity.this, "Input valid Email", Toast.LENGTH_SHORT).show();
                } else if (mPassword.isEmpty()) {
                    Toast.makeText(EmailActivity.this, "Input valid password", Toast.LENGTH_SHORT).show();

                } else if (mUsername.isEmpty()) {
                    Toast.makeText(EmailActivity.this, "Input valid Username", Toast.LENGTH_SHORT).show();

                } else {
                    int randomPIN = (int) (Math.random() * 9000) + 1000;
                    val = "" + randomPIN;
                     pBar.setVisibility(View.VISIBLE);
                    sendDataReq();


                }
            }
        });

    }

    private void getdata() {
        mEmail = email.getText().toString().trim();
        mUsername = username.getText().toString().trim();
        mPassword = password.getText().toString().trim();

    }

    private void sendDataReq() {

        Call<EmailResponse> call = serviceInterface.emailVerify(convertPlainString(mEmail), convertPlainString(val), convertPlainString(mUsername), convertPlainString(mPassword));
        call.enqueue(new Callback<EmailResponse>() {
            @Override
            public void onResponse(Call<EmailResponse> call, Response<EmailResponse> response) {

                if (response.body().getStatus() == 1) {
                    pBar.setVisibility(View.GONE);
                    SharePreferenceUtils.getInstance().saveString("USER_otp", response.body().getInformation().getOtp());
                    SharePreferenceUtils.getInstance().saveString("USER_email", response.body().getInformation().getEmail());
                    SharePreferenceUtils.getInstance().saveString("USER_name",response.body().getInformation().getName());
                    SharePreferenceUtils.getInstance().saveString("USER_password",response.body().getInformation().getPassword());

                    Intent intent = new Intent(EmailActivity.this, MailVerifyActivity.class);
                    intent.putExtra("email", mEmail);
                    intent.putExtra("otp", val);
                    startActivity(intent);
                    finish();


                    Toast.makeText(EmailActivity.this, "" + response.body().getMsg() + " " + val, Toast.LENGTH_SHORT).show();
                } else {
                    pBar.setVisibility(View.GONE);

                    Toast.makeText(EmailActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<EmailResponse> call, Throwable t) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(EmailActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void setUpWidget() {

        email = findViewById(R.id.editText);
        username = findViewById(R.id.editText3);
        password = findViewById(R.id.editText2);
        submit = findViewById(R.id.button3);
        alreadymember = findViewById(R.id.textView10);
        pBar = findViewById(R.id.progressBar2);
        back = findViewById(R.id.imageButton3);
    }

    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
