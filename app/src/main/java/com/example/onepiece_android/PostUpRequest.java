package com.example.onepiece_android;

import java.io.File;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostUpRequest {
    private static final String BASE_URL = "http://54.180.94.103:8080";

    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    ServerApi serverApi = PostUpRequest.getClient().create(ServerApi.class);


}
