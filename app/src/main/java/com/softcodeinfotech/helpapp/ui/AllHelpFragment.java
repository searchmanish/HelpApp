package com.softcodeinfotech.helpapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

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

import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllHelpFragment extends Fragment {

    String email, name, age, gender, mobile, imageurl, uid, state;

    //RecylerView
    ProgressBar pBar;

    Retrofit retrofit;
    ServiceInterface serviceInterface;

    String TAG = "MainActivity";
    private RecyclerView replaceRecyler;
    private ArrayList<GetHelpListModel> mHelpDetailsList = new ArrayList<GetHelpListModel>();
    private GetHelpListAdapter getHelpListAdapter;


    public AllHelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_help, container, false);

        replaceRecyler =view.findViewById(R.id.replaceRecycler);
        pBar = view.findViewById(R.id.progressBar6);

        email = SharePreferenceUtils.getInstance().getString(Constant.USER_email);
        name = SharePreferenceUtils.getInstance().getString(Constant.USER_name);
        age = SharePreferenceUtils.getInstance().getString(Constant.User_age);
        gender = SharePreferenceUtils.getInstance().getString(Constant.USER_gender);
        mobile = SharePreferenceUtils.getInstance().getString(Constant.USER_mobile);
        imageurl = SharePreferenceUtils.getInstance().getString(Constant.USER_imageurl);
        uid = SharePreferenceUtils.getInstance().getString(Constant.USER_id);


        //
        pBar.setVisibility(View.VISIBLE);


        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceInterface = retrofit.create(ServiceInterface.class);

        //state = SharePreferenceUtils.getInstance().getString(Constant.USER_state);
        state = "delhi";
        // Toast.makeText(this, ""+state, Toast.LENGTH_SHORT).show();
        Log.v("state", state);


        // replaceRecyler = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        replaceRecyler.setLayoutManager(mLayoutManger);
        replaceRecyler.setItemAnimator(new DefaultItemAnimator());

        getHelpListAdapter = new GetHelpListAdapter(getContext(), mHelpDetailsList, GetScreenWidth());
        replaceRecyler.setAdapter(getHelpListAdapter);
        replaceRecyler.setItemAnimator(new DefaultItemAnimator());
       getHelpListReq();


        return view;

    }

    private void getHelpListReq() {
        Call<GethelplistResponse> call = serviceInterface.getHelpLitstItem(convertPlainString(state));
        call.enqueue(new Callback<GethelplistResponse>() {
            @Override
            public void onResponse(Call<GethelplistResponse> call, Response<GethelplistResponse> response) {
                if (response.body().getStatus().equals(1)) {
                    pBar.setVisibility(View.GONE);
                    for (int i = 0; i < response.body().getInformation().size(); i++) {
                        mHelpDetailsList.add(new GetHelpListModel(response.body().getInformation().get(i).getHelpTitle(),
                                String.valueOf(response.body().getInformation().get(i).getTimestamp()),
                                response.body().getInformation().get(i).getHelpDescription()
                                , String.valueOf(response.body().getInformation().get(i).getHelpCategoryId()),
                                response.body().getInformation().get(i).getStatus()
                                , response.body().getInformation().get(i).getState(),
                                String.valueOf(response.body().getInformation().get(i).getUserId()),
                                response.body().getInformation().get(i).getAddress(),
                                response.body().getInformation().get(i).getLatitude(),
                                response.body().getInformation().get(i).getLongitude(),
                                response.body().getInformation().get(i).getImage()));
                    }
                    getHelpListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GethelplistResponse> call, Throwable t) {
                pBar.setVisibility(View.GONE);
               // Toast.makeText(MainActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private int GetScreenWidth() {
        int width = 100;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;

        return width;
    }

    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }

}
