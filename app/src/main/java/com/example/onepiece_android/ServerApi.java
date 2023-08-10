package com.example.onepiece_android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ServerApi {

    @POST("user/login")
    Call<Void> login(
            @Body LoginRequest loginRequest
    );
}
