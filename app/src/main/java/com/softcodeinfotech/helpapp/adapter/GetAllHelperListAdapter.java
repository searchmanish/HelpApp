package com.softcodeinfotech.helpapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softcodeinfotech.helpapp.ui.HelpersProfileActivity;
import com.softcodeinfotech.helpapp.R;
import com.softcodeinfotech.helpapp.model.GetAllHelperListModel;

import java.util.List;

public class GetAllHelperListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<GetAllHelperListModel> mHelperListModel;
    private String TAG = "HelperListAdapter";
    private int mScrenwidth;

    String iName,iGender,iAddress,iEmail,iImage,iAge;

    public GetAllHelperListAdapter(Context mContext,
                                   List<GetAllHelperListModel> mHelperListModel, int mScrenwidth) {
        this.mContext = mContext;
        this.mHelperListModel = mHelperListModel;
        this.mScrenwidth = mScrenwidth;
    }

    public class GetAllHelperListAdapterHolder extends RecyclerView.ViewHolder{
        TextView mName, mGender, mAddress;
         ImageView mImageurl;
         CardView mHelpersProfileCardView;

        public GetAllHelperListAdapterHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.helperName);
            mAddress =itemView.findViewById(R.id.helperAddress);
            mGender = itemView.findViewById(R.id.helperGender);
            mImageurl = itemView.findViewById(R.id.helperImageView);
            mHelpersProfileCardView=itemView.findViewById(R.id.helpersProfileCardView);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_helper_list, viewGroup, false);
        return new GetAllHelperListAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final GetAllHelperListModel allHelperListModel = mHelperListModel.get(i);

        ((GetAllHelperListAdapterHolder) viewHolder).mName.setText(allHelperListModel.getName());
        ((GetAllHelperListAdapterHolder) viewHolder).mGender.setText(allHelperListModel.getGender());
        ((GetAllHelperListAdapterHolder) viewHolder).mAddress.setText(allHelperListModel.getAddress());


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bgp);
        requestOptions.error(R.drawable.bgp);
        Glide.with(mContext).setDefaultRequestOptions(requestOptions).load(allHelperListModel.getImageUrl())
                .into(((GetAllHelperListAdapterHolder) viewHolder).mImageurl);



        ((GetAllHelperListAdapterHolder) viewHolder).mHelpersProfileCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //data for intent
                String iName,iGender,iAddress,iEmail,iImage,iAge;
                iName =allHelperListModel.getName();
                iAddress=allHelperListModel.getAddress();
                iGender=allHelperListModel.getGender();
                iEmail=allHelperListModel.getEmail();
                iImage=allHelperListModel.getImageUrl();
                iAge=allHelperListModel.getAge();

                Intent intent = new Intent(mContext,HelpersProfileActivity.class);
                intent.putExtra("name", iName);
                intent.putExtra("address",iAddress);
                intent.putExtra("gender",iGender);
                intent.putExtra("email",iEmail);
                intent.putExtra("image",iImage);
                intent.putExtra("age",iAge);

                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mHelperListModel.size();
    }
}
