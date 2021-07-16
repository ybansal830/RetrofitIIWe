package com.myfirst.retrofitiiwe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mEtPostId;
    private Button mBtnFetch;
    private RecyclerView mRecyclerView;
    private List<ResponseDTO> postModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void callApi() {
        ApiService apiService = Network.getInstance().create(ApiService.class);
        int postId = Integer.parseInt(mEtPostId.getText().toString());
        apiService.getPosts(postId).enqueue(new Callback<List<ResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ResponseDTO>> call, Response<List<ResponseDTO>> response) {
                if(response.body()!=null){
                    postModelList = response.body();
                    setRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseDTO>> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        mEtPostId = findViewById(R.id.etPostId);
        mBtnFetch = findViewById(R.id.btnFetch);
        mRecyclerView = findViewById(R.id.recyclerView);
        mBtnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();
            }
        });
    }
    public void setRecyclerView(){
        PostAdapter postAdapter = new PostAdapter(postModelList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setAdapter(postAdapter);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }
}