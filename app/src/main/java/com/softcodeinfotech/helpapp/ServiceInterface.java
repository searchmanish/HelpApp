package com.softcodeinfotech.helpapp;


import com.softcodeinfotech.helpapp.response.EmailResponse;
import com.softcodeinfotech.helpapp.response.PasswordResponse;
import com.softcodeinfotech.helpapp.response.ProfileupdateResponse;
import com.softcodeinfotech.helpapp.response.SigninResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceInterface {

    @Multipart
    @POST("helpapp/emailverify.php")
    Call<EmailResponse> emailVerify(

            @Part("email") RequestBody email,
            @Part("otp") RequestBody otp,
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );

    @Multipart
    @POST("helpapp/savepassword.php")
    Call<PasswordResponse> savePassword(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password
    );

    @Multipart
    @POST("helpapp/profileupdate.php")
    Call<ProfileupdateResponse> saveProfile(
            @Part("email") RequestBody email,
            @Part("name") RequestBody name,
            @Part("age") RequestBody age,
            @Part("gender") RequestBody gender,
            @Part("mobile") RequestBody mobile,
            @Part("profilestatus") RequestBody profilestatus

            );

    @Multipart
    @POST("helpapp/user_signin.php")
    Call<SigninResponse> userlogin(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password
    );

}
