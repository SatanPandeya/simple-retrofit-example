package com.dhabs.www.fakeretrofitapi.Service;

import com.dhabs.www.fakeretrofitapi.model.RetrofitPhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitPhotoService {
    @GET("photos")
    Call<List<RetrofitPhotoModel>> getAllPhotos();
}
