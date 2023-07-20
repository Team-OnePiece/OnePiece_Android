package com.example.onepiece_android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApi {

    @POST("/user/signup")
    Call<Void> signUp(
            @Body SignUpRequest signUpRequest

    );

    @GET("/user/id/Duplicate")
    Call<Void> idDuplicate(
            @Query("userid") String userid
    );

    @GET("/user/nickname/Duplicate")
    Call<Void> nickDuplicate(
            @Query("nickname") String nickname
    );
}
