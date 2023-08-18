package com.example.onepiece_android;

import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @Multipart
    @POST("/feed/{groupId}")
    Call<PostUpResponse> postUp(
            @Path("groupId") int groupId,
      @Part MultipartBody.Part image,
      @Header("Authorization") String authorization,
            @Query("place") String place
    );



    @POST("/tag/{feedId}")
    Call<TagResponse> createTag(
            @Path("boardId") long feedId,
            @Header("Authorization") String authorization,
            @Body TagRequest tagRequest
    );

}
