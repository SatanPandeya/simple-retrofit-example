package com.dhabs.www.fakeretrofitapi.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(HttpsConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
