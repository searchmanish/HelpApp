package com.softcodeinfotech.helpapp.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcodeinfotech.helpapp.R;
import com.softcodeinfotech.helpapp.ServiceInterface;
import com.softcodeinfotech.helpapp.adapter.GetAllHelperListAdapter;
import com.softcodeinfotech.helpapp.response.GetIndividualUserResponse;
import com.softcodeinfotech.helpapp.util.Constant;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HelpDeatailDescActivity extends AppCompatActivity {

    String user_id;
    ImageButton back;
    ImageView imageView;
    TextView title,desc,address,posttime;
    Button call,chat,profile;
    String mMobile;

    String number;
    String whatsappurl;


    ProgressBar pBar;

    Retrofit retrofit;
    ServiceInterface serviceInterface;

    String detail_userid,detail_image,detail_title,detail_desc,detail_address,detail_posttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_deatail_desc);

        setUpWidget();

        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceInterface = retrofit.create(ServiceInterface.class);


        final Intent intent = getIntent();
        user_id = intent.getExtras().getString("detail_userid");
        Toast.makeText(this, ""+user_id, Toast.LENGTH_SHORT).show();



        detail_image = intent.getExtras().getString("detail_image");
        detail_title = intent.getExtras().getString("detail_title");
        detail_desc= intent.getExtras().getString("detail_desc");
        detail_address= intent.getExtras().getString("detail_address");
        detail_posttime=intent.getExtras().getString("detail_posttime");

        setData();
       setDataReq();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mMobile));
                if (ActivityCompat.checkSelfPermission(HelpDeatailDescActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpDeatailDescActivity.this,IndividualHelpActivity.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                     number=""+91+""+mMobile;
                     whatsappurl=""+"https://api.whatsapp.com/send?phone=+"+""+number+""+"&text=I'm%20interested%20to%20help%20please%20contact%20me%20source%20elfee\"";
               // "https://api.whatsapp.com/send?phone=+919911938591&text=I'm%20interested%20to%20help%20please%20contact%20me%20source%20elfee";

                openWhatsappContact(number);
               // Toast.makeText(HelpDeatailDescActivity.this, ""+whatsappurl, Toast.LENGTH_SHORT).show();


            }
        });
    }


    private void setUpWidget() {
        back=findViewById(R.id.imageBack);
        title= findViewById(R.id.details_title);
        desc = findViewById(R.id.details_description);
        address = findViewById(R.id.details_address);
        posttime = findViewById(R.id.details_posttime);
        call = findViewById(R.id.details_call);
        chat = findViewById(R.id.details_chat);
        profile= findViewById(R.id.details_profile);
        imageView=findViewById(R.id.details_imageView);
        pBar= findViewById(R.id.pBar);
    }

    private void setData() {
        title.setText(detail_title);
        desc.setText(detail_desc);
        address.setText(detail_address);
        posttime.setText(detail_posttime);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bgp);
        requestOptions.error(R.drawable.bgp);
        Glide.with(HelpDeatailDescActivity.this).setDefaultRequestOptions(requestOptions).load(detail_image)
                .into(imageView);
    }


    //
    private void setDataReq() {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), user_id);
        Call<GetIndividualUserResponse> call = serviceInterface.getIndividualUserDetails(convertPlainString(user_id));
        call.enqueue(new Callback<GetIndividualUserResponse>() {
            @Override
            public void onResponse(Call<GetIndividualUserResponse> call, Response<GetIndividualUserResponse> response) {
                if (response.body().getStatus().equals(1))
                {
                   pBar.setVisibility(View.GONE);

                   /* mName = String.valueOf(response.body().getInformation().getName());
                    mEmail = String.valueOf(response.body().getInformation().getEmail());
                    mAge = String.valueOf(response.body().getInformation().getAge());
                    mGender = String.valueOf(response.body().getInformation().getGender());
                    url = String.valueOf(response.body().getInformation().getImageUrl());*/
                    mMobile =String.valueOf(response.body().getInformation().getMobile());

                  /*  VisibilityOn();
                    setData();
*/


                }
            }

            @Override
            public void onFailure(Call<GetIndividualUserResponse> call, Throwable t) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(HelpDeatailDescActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }


    //whatsapp
    void openWhatsappContact(String number) {
        //9911938591
      /*  Uri uri = Uri.parse("smsto:" + "9911938591");
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.putExtra("sms_body", "abccd");
        i.setPackage("com.whatsapp");
        startActivity(i);*/

        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(whatsappurl
                       // "https://api.whatsapp.com/send?phone=+919911938591&text=I'm%20interested%20to%20help%20please%20contact%20me%20source%20elfee"
                       // "https://api.whatsapp.com/send?phone=+number&text=I'm%20interested%20to%20help%20please%20contact%20me%20source%20elfee"

                )));
    }
}
