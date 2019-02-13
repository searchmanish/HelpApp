package com.softcodeinfotech.helpapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softcodeinfotech.helpapp.R;
import com.softcodeinfotech.helpapp.model.HelpModel;

import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//
    private Context mContext;
    private List<HelpModel> mHelpHistoryModel;
    private String TAG = "HistoryAdapter";
    private int mScrenwidth;

    public HistoryAdapter(Context mContext, List<HelpModel> mHelpHistoryModel, int mScrenwidth) {
        this.mContext = mContext;
        this.mHelpHistoryModel = mHelpHistoryModel;
        this.mScrenwidth = mScrenwidth;
    }


    public class HistoryAdapterHolder extends RecyclerView.ViewHolder {

        TextView mHelpTitle, mHelpTimeStamp;

        public HistoryAdapterHolder(@NonNull View itemView) {
            super(itemView);

            mHelpTitle = itemView.findViewById(R.id.helpTitle);
            mHelpTimeStamp = itemView.findViewById(R.id.helpTime);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_history, viewGroup, false);
        return new HistoryAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final HelpModel  helpModel =mHelpHistoryModel.get(i);

        ((HistoryAdapterHolder) viewHolder).mHelpTitle.setText(helpModel.getHelpTitle());
        ((HistoryAdapterHolder) viewHolder).mHelpTimeStamp.setText(helpModel.getHelpTimeStamp());

    }

    @Override
    public int getItemCount() {
        return mHelpHistoryModel.size();
    }
}
