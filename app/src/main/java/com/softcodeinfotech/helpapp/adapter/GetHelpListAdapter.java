package com.softcodeinfotech.helpapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softcodeinfotech.helpapp.ui.IndividualHelpActivity;
import com.softcodeinfotech.helpapp.R;
import com.softcodeinfotech.helpapp.model.GetHelpListModel;

import java.util.List;

public class GetHelpListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<GetHelpListModel> mHelpListModel;
    private String TAG = "HelpListAdapter";
    private int mScrenwidth;

    public GetHelpListAdapter(Context mContext, List<GetHelpListModel> mHelpListModel, int mScrenwidth) {

        this.mContext = mContext;
        this.mHelpListModel = mHelpListModel;
        this.mScrenwidth = mScrenwidth;
    }


    public class GetHelpListAdapterHolder extends RecyclerView.ViewHolder {
        TextView mHelpTitle, mHelpTimeStamp, mHelpDescription;
        CardView mCardView;

        public GetHelpListAdapterHolder(@NonNull View itemView) {
            super(itemView);
            mHelpTitle = itemView.findViewById(R.id.helpTitle);
            mHelpTimeStamp = itemView.findViewById(R.id.helpTime);
            mHelpDescription = itemView.findViewById(R.id.helpDesc);
            mCardView =  itemView.findViewById(R.id.helpCardView);
        }


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.help_list, viewGroup, false);
        return new GetHelpListAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final GetHelpListModel getHelpListModel = mHelpListModel.get(i);


        ((GetHelpListAdapterHolder) viewHolder).mHelpTitle.setText(getHelpListModel.getHelpTitle());
        ((GetHelpListAdapterHolder) viewHolder).mHelpDescription.setText(getHelpListModel.getHelpDescription());
        ((GetHelpListAdapterHolder) viewHolder).mHelpTimeStamp.setText(getHelpListModel.getHelpTimeStamp());

        ((GetHelpListAdapterHolder) viewHolder).mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_id = getHelpListModel.getUserId();
                Intent intent = new Intent(mContext,IndividualHelpActivity.class);
                intent.putExtra("user_id", user_id);
                 mContext.startActivity(intent);

               // Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
              //  Toast.makeText(mContext, ""+user_id, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mHelpListModel.size();
    }
}
