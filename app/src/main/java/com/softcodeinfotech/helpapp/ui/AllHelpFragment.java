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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcodeinfotech.helpapp.R;
import com.softcodeinfotech.helpapp.ServiceInterface;
import com.softcodeinfotech.helpapp.adapter.GetHelpListAdapter;
import com.softcodeinfotech.helpapp.model.GetHelpListModel;
import com.softcodeinfotech.helpapp.response.GetCategoryResponse;
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

    //spinner
    Spinner spinCategory;
    ArrayList<String> catId = new ArrayList<>();
    ArrayList<String> catName = new ArrayList<>();

    String mCatId,item;
    String latitude,longitude;


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
        spinCategory =view.findViewById(R.id.spinCategory);

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

        //spinner
        getCategoryReq();
        //if you want to set any action you can do in this listener
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                mCatId = catId.get(position);
                item = String.valueOf(arg0.getItemAtPosition(position));
               /* Toast.makeText(getActivity(), ""+item, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+mCatId, Toast.LENGTH_SHORT).show();
*/
                //get location

                MainActivity activity =(MainActivity)getActivity();
                latitude=activity.mylatitude();
                longitude=activity.mylongitude();
             /*   Toast.makeText(getActivity(), ""+latitude, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+longitude, Toast.LENGTH_SHORT).show();
*/

                getHelpListReq();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });







        return view;

    }

    private void getHelpListReq() {
        Call<GethelplistResponse> call = serviceInterface.getHelpLitstItem(convertPlainString(mCatId),convertPlainString(latitude),
                convertPlainString(longitude));
        call.enqueue(new Callback<GethelplistResponse>() {
            @Override
            public void onResponse(Call<GethelplistResponse> call, Response<GethelplistResponse> response) {
                replaceRecyler.removeAllViews();
                if (response.body().getStatus().equals(1)) {
                    pBar.setVisibility(View.GONE);
                    mHelpDetailsList.clear();
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


    private void getCategoryReq() {
        String securecode = "1234";
        Call<GetCategoryResponse> call = serviceInterface.getCategory(convertPlainString(securecode));
        call.enqueue(new Callback<GetCategoryResponse>() {
            @Override
            public void onResponse(Call<GetCategoryResponse> call, Response<GetCategoryResponse> response) {
                if (response.body() != null && response.body().getStatus().equals(1)) {
                    for (int i = 0; i < response.body().getInformation().size(); i++) {
                        catId.add(String.valueOf(response.body().getInformation().get(i).getCategoryId()));
                        catName.add(response.body().getInformation().get(i).getCategoryName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, catName);//setting the country_array to spinner
                    // string value
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinCategory.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "not inserted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetCategoryResponse> call, Throwable t) {

            }
        });


    }

}
