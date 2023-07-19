package com.example.onepiece_android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerApi {

    @POST("/user/signup")
    Call<Void> signUp(
            @Body SignUpRequest signUpRequest
    );

    @GET("/user/id/Duplicate")
    Call<Void> idDuplicate(
            @Body DuplicateIdRequest duplicateIdRequest
    );

    @GET("/user/nickname/Duplicate")
    Call<Void> nickDuplicate(
            @Body DuplicateNicknameRequest duplicateNicknameRequest
    );
}
