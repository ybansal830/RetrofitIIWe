package com.myfirst.retrofitiiwe;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvName;
    private TextView mTvEmail;
    private TextView mTvBody;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mTvName = itemView.findViewById(R.id.tvName);
        mTvEmail = itemView.findViewById(R.id.tvEmail);
        mTvBody = itemView.findViewById(R.id.tvBody);
    }
    public void setData(ResponseDTO responseDTO){
        mTvName.setText(responseDTO.getName());
        mTvEmail.setText(responseDTO.getEmail());
        mTvBody.setText(responseDTO.getBody());
    }
}
