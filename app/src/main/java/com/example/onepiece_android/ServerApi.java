package com.example.onepiece_android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ServerApi {

    @POST("/user/signup")
    Call<Void> signUp(
            @Body SignUpRequest signUpRequest
    );

    @GET("/user/id/duplicate")
    Call<Void> idDuplicate(
            @Query("userid") String userid
    );

    @GET("/user/nickname/duplicate")
    Call<Void> nickDuplicate(
            @Query("nickname") String nickname
    );

    @GET("/user/student/id/duplicate")
    Call<Void> studentIdDuplicate(
            @Query("grade") int grade,
            @Query("class_number") int classNumber,
            @Query("number") int number
    );

    @GET("/board/read")
    Call<Void> readBoard(
            @Query("tag") String[] tag,
            @Query("place") String place,
            @Query("nickname") String nickname,
            @Query("profile") String profile,
            @Query("number") String number,
            @Query("created_at") String createdAt
    );
}
