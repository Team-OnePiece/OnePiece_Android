package com.example.onepiece_android;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostModifyApi {
    private static final String BASE_URL = "http://54.180.94.103:8080";
    private Retrofit retrofit;
    private ServerApi serverApi;

    public PostModifyApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);
    }

    public  void postModify(long feedId, String accessToken, String place, Callback<Void> callback){
        PostModifyRequest request = new PostModifyRequest(place);

        Call<Void> call = serverApi.postModify(feedId, request);
        call.enqueue(callback);
    }
}
