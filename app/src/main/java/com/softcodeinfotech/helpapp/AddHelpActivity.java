package com.softcodeinfotech.helpapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.softcodeinfotech.helpapp.response.GetCategoryResponse;
import com.softcodeinfotech.helpapp.response.HelpDataInsertResponse;
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

public class AddHelpActivity extends AppCompatActivity {
    ImageButton back;
    ProgressBar pBar;
    Spinner spinCategory;
    EditText title, desc;
    Button submit;

    String mUserid, mTitle, mDesc, mCatId, mState, item;

    ArrayList<String> catId = new ArrayList<>();
    ArrayList<String> catName = new ArrayList<>();
    Retrofit retrofit;
    ServiceInterface serviceInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_help);
        catId = new ArrayList<>();
        catName = new ArrayList<>();

        setUpwidget();
        getData();
        pBar.setVisibility(View.GONE);

        mUserid = SharePreferenceUtils.getInstance().getString(Constant.USER_id);
        mState = SharePreferenceUtils.getInstance().getString(Constant.USER_state);

        //Toast.makeText(this, ""+mUserid, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, ""+mState, Toast.LENGTH_SHORT).show();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceInterface = retrofit.create(ServiceInterface.class);


        getCategoryReq();


        //if you want to set any action you can do in this listener
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                mCatId = catId.get(position);
                item = String.valueOf(arg0.getItemAtPosition(position));
                // Toast.makeText(AddNoteActivity.this, ""+item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                if (mTitle.isEmpty()) {
                    Toast.makeText(AddHelpActivity.this, "Fill Title", Toast.LENGTH_SHORT).show();
                } else if (mDesc.isEmpty()) {
                    Toast.makeText(AddHelpActivity.this, "Fill Desc", Toast.LENGTH_SHORT).show();
                } else {

                    pBar.setVisibility(View.VISIBLE);
                    getData();
                 //   Toast.makeText(AddHelpActivity.this, ""+mCatId+""+mUserid+""+mTitle+""+
                  //          ""+mDesc+""+mState, Toast.LENGTH_SHORT).show();
                    saveData();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void saveData() {
        Call<HelpDataInsertResponse> call = serviceInterface.helpDataInsert(convertPlainString(mUserid),
                convertPlainString(mTitle), convertPlainString(mDesc), convertPlainString(mCatId), convertPlainString(mState));
        call.enqueue(new Callback<HelpDataInsertResponse>() {
            @Override
            public void onResponse(Call<HelpDataInsertResponse> call, Response<HelpDataInsertResponse> response) {
                pBar.setVisibility(View.GONE);
                if (response.body().getStatus().equals(1)) {
                    title.setText("");
                    desc.setText("");
                    Toast.makeText(AddHelpActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HelpDataInsertResponse> call, Throwable t) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(AddHelpActivity.this, "network issue", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void getCategoryReq() {
        String securecode = "1234";
        Call<GetCategoryResponse> call = serviceInterface.getCategory(convertPlainString(securecode));
        call.enqueue(new Callback<GetCategoryResponse>() {
            @Override
            public void onResponse(Call<GetCategoryResponse> call, Response<GetCategoryResponse> response) {
                if (response.body().getStatus().equals(1)) {
                    for (int i = 0; i < response.body().getInformation().size(); i++) {
                        catId.add(String.valueOf(response.body().getInformation().get(i).getCategoryId()));
                        catName.add(response.body().getInformation().get(i).getCategoryName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddHelpActivity.this,
                            android.R.layout.simple_spinner_item, catName);//setting the country_array to spinner
                    // string value
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinCategory.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<GetCategoryResponse> call, Throwable t) {

            }
        });


    }

    private void setUpwidget() {
        back = findViewById(R.id.backButton);
        pBar = findViewById(R.id.progressBar);
        title = findViewById(R.id.title_text);
        desc = findViewById(R.id.desc);
        submit = findViewById(R.id.submit);
        spinCategory = findViewById(R.id.spinCategory);
    }

    private void getData() {
        mTitle = title.getText().toString().trim();
        mDesc = desc.getText().toString().trim();
    }


    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
