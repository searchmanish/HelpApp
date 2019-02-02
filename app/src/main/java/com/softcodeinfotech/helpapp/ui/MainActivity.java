package com.softcodeinfotech.helpapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
import com.softcodeinfotech.helpapp.adapter.GetHelpListAdapter;
import com.softcodeinfotech.helpapp.model.GetHelpListModel;
import com.softcodeinfotech.helpapp.response.GethelplistResponse;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String email, name, age, gender, mobile, imageurl,uid,state;

    DrawerLayout drawer;
    ImageButton toggle;
    TextView profile, kyc, orders;
    BottomNavigationView bottom;
    TextView toolbar;
    TextView account,myHistory;
    ImageButton settings;
    ImageView image;
    TextView dName, dEmail;
    FloatingActionButton fabButton;

    //RecylerView
    ProgressBar pBar;

    Retrofit retrofit;
    ServiceInterface serviceInterface;

    String TAG = "MainActivity";
    private  RecyclerView replaceRecyler;
    private ArrayList<GetHelpListModel> mHelpDetailsList = new ArrayList<GetHelpListModel>();
    private GetHelpListAdapter getHelpListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        setUpWidget();

        email = SharePreferenceUtils.getInstance().getString(Constant.USER_email);
        name = SharePreferenceUtils.getInstance().getString(Constant.USER_name);
        age = SharePreferenceUtils.getInstance().getString(Constant.User_age);
        gender = SharePreferenceUtils.getInstance().getString(Constant.USER_gender);
        mobile = SharePreferenceUtils.getInstance().getString(Constant.USER_mobile);
        imageurl = SharePreferenceUtils.getInstance().getString(Constant.USER_imageurl);
        uid = SharePreferenceUtils.getInstance().getString(Constant.USER_id);




      //  Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();


        // Toast.makeText(this, ""+email+""+name+""+""+age+""+gender+""+mobile, Toast.LENGTH_SHORT).show();

        //for default placeholder image in glide
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bgp);
        requestOptions.error(R.drawable.bgp);

        dName.setText(name);
        dEmail.setText(email);
        Glide.with(this).setDefaultRequestOptions(requestOptions).load(imageurl).into(image);

        //
        pBar.setVisibility(View.VISIBLE);


        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceInterface = retrofit.create(ServiceInterface.class);

        state = SharePreferenceUtils.getInstance().getString(Constant.USER_state);
       // Toast.makeText(this, ""+state, Toast.LENGTH_SHORT).show();
        Log.v( "state", state);

        // replaceRecyler = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        replaceRecyler.setLayoutManager(mLayoutManger);
        replaceRecyler.setItemAnimator(new DefaultItemAnimator());

        getHelpListAdapter = new GetHelpListAdapter(this, mHelpDetailsList, GetScreenWidth());
        replaceRecyler.setAdapter(getHelpListAdapter);
        replaceRecyler.setItemAnimator(new DefaultItemAnimator());
        getHelpListReq();



        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        kyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kycIntent = new Intent(MainActivity.this,KycActivity.class);
                startActivity(kycIntent);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountIntent = new Intent(MainActivity.this,AccountActivity.class);
                startActivity(accountIntent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        myHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myhistoryIntent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(myhistoryIntent);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addHelpIntent = new Intent(MainActivity.this,AddHelpActivity.class);
                startActivity(addHelpIntent);
            }
        });

       /*
        myTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setText("My Test");

                bottom.setSelectedItemId(R.id.test);

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MyTest test = new MyTest();
                ft.replace(R.id.replace, test);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.test:
                        toolbar.setText("My Test");

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        MyTest test = new MyTest();
                        ft.replace(R.id.replace, test);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft.commit();
                        drawer.closeDrawer(GravityCompat.START);

                        return true;
                    case R.id.college:
                        toolbar.setText("College Info");

                        FragmentManager fm1 = getSupportFragmentManager();
                        FragmentTransaction ft1 = fm1.beginTransaction();
                        CollegeInfo test1 = new CollegeInfo();
                        ft1.replace(R.id.replace, test1);
                        ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft1.commit();
                        drawer.closeDrawer(GravityCompat.START);

                        return true;

                    case R.id.home:
                        toolbar.setText("Home");

                        FragmentManager fm2 = getSupportFragmentManager();
                        FragmentTransaction ft2 = fm2.beginTransaction();
                        Home home = new Home();
                        ft2.replace(R.id.replace, home);
                        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft2.commit();
                        drawer.closeDrawer(GravityCompat.START);

                        return true;

                }

                return true;
            }
        });

        settings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Orders.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        toolbar.setText("Home");

        FragmentManager fm2 = getSupportFragmentManager();
        FragmentTransaction ft2 = fm2.beginTransaction();
        Home home = new Home();
        ft2.replace(R.id.replace, home);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        //ft.addToBackStack(null);
        ft2.commit();
        drawer.closeDrawer(GravityCompat.START);

        bottom.setSelectedItemId(R.id.home);
*/
        //

        bottom.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.helpMe:
                        toolbar.setText("Help Me");
                      //  Toast.makeText(MainActivity.this, "help me", Toast.LENGTH_SHORT).show();
                       // return true;
                        break;

                    case  R.id.helpersList:
                        toolbar.setText("Helpers List");
                        Intent intent = new Intent(MainActivity.this,AllHelperActivity.class);
                        startActivity(intent);
                      //  Toast.makeText(MainActivity.this, "Helpers List", Toast.LENGTH_SHORT).show();
                          break;


                }
                return;


            }

        });

    }

    private void getHelpListReq() {
        Call<GethelplistResponse> call = serviceInterface.getHelpLitstItem(convertPlainString(state));
        call.enqueue(new Callback<GethelplistResponse>() {
            @Override
            public void onResponse(Call<GethelplistResponse> call, Response<GethelplistResponse> response) {
                if (response.body().getStatus().equals(1))
                {
                    pBar.setVisibility(View.GONE);
                    for (int i = 0; i < response.body().getInformation().size(); i++)
                    {
                        mHelpDetailsList.add(new GetHelpListModel(response.body().getInformation().get(i).getHelpTitle(),
                                String.valueOf(response.body().getInformation().get(i).getTimestamp()),
                                response.body().getInformation().get(i).getHelpDescription()
                                , String.valueOf(response.body().getInformation().get(i).getHelpCategoryId()),
                                response.body().getInformation().get(i).getStatus()
                                , response.body().getInformation().get(i).getState(),
                                String.valueOf( response.body().getInformation().get(i).getUserId())));
                    }
                    getHelpListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GethelplistResponse> call, Throwable t) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setUpWidget() {
        ///
        drawer = findViewById(R.id.drawer);
        toggle = findViewById(R.id.imageButton4);
        profile = findViewById(R.id.textView58);
        orders = findViewById(R.id.textView61);
        bottom = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.textView27);
        kyc = findViewById(R.id.textView59);
        account = findViewById(R.id.textView62);
        myHistory = findViewById(R.id.textView60);
        // settings = findViewById(R.id.imageButton6);
        fabButton = findViewById(R.id.floatingActionButton3);

        //drawer design
        image = findViewById(R.id.imageView1);
        dName = findViewById(R.id.textView55);
        dEmail = findViewById(R.id.textView56);

        //recyler
        replaceRecyler = findViewById(R.id.replaceRecycler);
        pBar = findViewById(R.id.pBar);
    }


    //
    void toggleDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    //
    private int GetScreenWidth() {
        int width = 100;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;

        return width;
    }

    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
