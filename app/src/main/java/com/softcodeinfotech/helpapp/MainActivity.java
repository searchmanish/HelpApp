package com.softcodeinfotech.helpapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.softcodeinfotech.helpapp.util.Constant;
import com.softcodeinfotech.helpapp.util.SharePreferenceUtils;

public class MainActivity extends AppCompatActivity {

    String email, name, age, gender, mobile, imageurl,uid;

    DrawerLayout drawer;
    ImageButton toggle;
    TextView profile, kyc, orders;
    BottomNavigationView bottom;
    TextView toolbar;
    TextView account;
    ImageButton settings;
    ImageView image;
    TextView dName, dEmail;

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


        dName.setText(name);
        dEmail.setText(email);
        Glide.with(this).load(imageurl).into(image);


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
                        Toast.makeText(MainActivity.this, "help me", Toast.LENGTH_SHORT).show();
                       // return true;
                        break;

                    case  R.id.helpersList:
                        toolbar.setText("Helpers List");
                        Toast.makeText(MainActivity.this, "Helpers List", Toast.LENGTH_SHORT).show();
                          break;


                }
                return;


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
        // settings = findViewById(R.id.imageButton6);

        //drawer design
        image = findViewById(R.id.imageView1);
        dName = findViewById(R.id.textView55);
        dEmail = findViewById(R.id.textView56);
    }


    //
    void toggleDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }
}
