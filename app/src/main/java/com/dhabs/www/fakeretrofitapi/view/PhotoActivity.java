package com.dhabs.www.fakeretrofitapi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dhabs.www.fakeretrofitapi.R;
import com.dhabs.www.fakeretrofitapi.Service.RetrofitPhotoService;
import com.dhabs.www.fakeretrofitapi.Service.RetrofitServiceClient;
import com.dhabs.www.fakeretrofitapi.model.RetrofitPhotoModel;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {
    private RecyclerView photoRecyclerView;
    private PhotoAdapter photoAdapter;
    private CircularProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        progressBar = findViewById(R.id.circularProgressBarId);
        RetrofitPhotoService retrofitPhotoService = RetrofitServiceClient.getRetrofitClient().create(RetrofitPhotoService.class);
        retrofit2.Call<List<RetrofitPhotoModel>> call = retrofitPhotoService.getAllPhotos();
        ((CircularProgressDrawable) progressBar.getIndeterminateDrawable()).start();
        call.enqueue(new Callback<List<RetrofitPhotoModel>>() {
            @Override
            public void onResponse(retrofit2.Call<List<RetrofitPhotoModel>> call, Response<List<RetrofitPhotoModel>> response) {
                generateList(response.body());
                ((CircularProgressDrawable) progressBar.getIndeterminateDrawable()).progressiveStop();
            }

            @Override
            public void onFailure(retrofit2.Call<List<RetrofitPhotoModel>> call, Throwable t) {
                ((CircularProgressDrawable) progressBar.getIndeterminateDrawable()).progressiveStop();
            }
        });
    }

    private void generateList(List<RetrofitPhotoModel> photoModelList) {
        photoRecyclerView = findViewById(R.id.photoRecyclerViewId);
        photoAdapter = new PhotoAdapter(PhotoActivity.this, photoModelList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PhotoActivity.this);
        photoRecyclerView.setLayoutManager(layoutManager);
        photoRecyclerView.setAdapter(photoAdapter);
    }
}
