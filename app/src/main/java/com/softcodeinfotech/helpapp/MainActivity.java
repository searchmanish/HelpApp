package com.softcodeinfotech.helpapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

public class MainActivity extends AppCompatActivity {

    String email,name,age,gender,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = SharePreferenceUtils.getInstance().getString("USER_email");
        name = SharePreferenceUtils.getInstance().getString("USER_name");
        age = SharePreferenceUtils.getInstance().getString("USER_age");
        gender = SharePreferenceUtils.getInstance().getString("USER_gender");
        mobile = SharePreferenceUtils.getInstance().getString("USER_mobile");


        Toast.makeText(this, ""+email+""+name+""+""+age+""+gender+""+mobile, Toast.LENGTH_SHORT).show();
    }
}
