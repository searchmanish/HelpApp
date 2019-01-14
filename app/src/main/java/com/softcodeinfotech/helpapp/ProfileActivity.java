package com.softcodeinfotech.helpapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcodeinfotech.helpapp.response.ProfileupdateResponse;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    TextInputEditText pNmae, pAge, pMobile, pGender;
    Button submit;
    String mName, mAge, mMobile, mGender, mEmail;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;

    Retrofit retrofit;
    ConstraintLayout rootlayout;
    ServiceInterface serviceInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupWidget();
        // getData();

        mEmail = SharePreferenceUtils.getInstance().getString("USER_email");

        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceInterface = retrofit.create(ServiceInterface.class);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                saveDataReq();
            }
        });

    }


    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radio);
        // btnDisplay = (Button) findViewById(R.id.btnDisplay);

        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        mGender = radioButton.getText().toString();

       /* Toast.makeText(ProfileActivity.this,
                radioButton.getText(), Toast.LENGTH_SHORT).show();
*/
    }


    private void getData() {

        mName = pNmae.getText().toString().trim();
        mAge = pAge.getText().toString().trim();
        mMobile = pMobile.getText().toString().trim();

        addListenerOnButton();

        // Toast.makeText(this, "" + mName + "" + mAge + "" + mMobile, Toast.LENGTH_SHORT).show();

    }


    private void saveDataReq() {
        Call<ProfileupdateResponse> call = serviceInterface.saveProfile(convertPlainString(mEmail), convertPlainString(mName),
                convertPlainString(mAge), convertPlainString(mGender), convertPlainString(mMobile), convertPlainString("1"));

        call.enqueue(new Callback<ProfileupdateResponse>() {
            @Override
            public void onResponse(Call<ProfileupdateResponse> call, Response<ProfileupdateResponse> response) {
                if (response.body().getStatus() == 1) {
                    SharePreferenceUtils.getInstance().saveString("USER_name", response.body().getInformation().getName());
                    SharePreferenceUtils.getInstance().saveString("USER_age", response.body().getInformation().getAge());
                    SharePreferenceUtils.getInstance().saveString("USER_gender", response.body().getInformation().getGender());
                    SharePreferenceUtils.getInstance().saveString("USER_mobile", response.body().getInformation().getMobile());

                    Toast.makeText(ProfileActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ProfileActivity.this,setProfileImageActivity.class);
                    startActivity(intent);
                    finish();


                }
            }

            @Override
            public void onFailure(Call<ProfileupdateResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupWidget() {
        pNmae = findViewById(R.id.p_name);
        pAge = findViewById(R.id.p_age);
        pMobile = findViewById(R.id.p_mobile);
        submit = findViewById(R.id.p_sumit);
    }

    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }


}
