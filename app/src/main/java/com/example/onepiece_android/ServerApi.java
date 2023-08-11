package com.example.onepiece_android;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApi {

    @POST("/user/signup")
    Call<Void> signUp(
            @Body SignUpRequest signUpRequest
    );

    @GET("/user/id/duplicate")
    Call<Void> idDuplicate(
            @Query("account_id") String userid
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

    @GET("/user/info")
    Call<UserInfoResponse> userInfo(
            @Header("Authorization") String token
    );

    @PATCH("/user/update")
    Call<Void> nicknameModify(
            @Header("Authorization") String token,
            @Body String nickname
    );

    @Multipart
    @POST("/user/image/upload")
    Call<Void> profileImageUpload(
            @Header("Authorization") String token,
            @Body MultipartBody.Part image
    );
}
