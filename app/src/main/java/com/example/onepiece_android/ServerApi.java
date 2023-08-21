package com.example.onepiece_android;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ServerApi {

    @POST("/user/signup")
    Call<Void> signUp(
            @Body SignUpRequest signUpRequest
    );

    @GET("/user/id/duplicate")
    Call<Void> idDuplicate(
            @Query("account_id") String account_id
    );

    @GET("/user/nickname/duplicate")
    Call<Void> nickDuplicate(
            @Query("nickname") String nickname
    );

    @GET("/user/student/id/duplicate")
    Call<Void> studentIdDuplicate(
            @Query("grade") int grade,
            @Query("class_number") int class_number,
            @Query("number") int number
    );

    @GET("/feed")
    Call<ReadBoardResponse> readBoard(
    );

    @DELETE("/feed/{feedId}")
    Call<Void> deleteBoard(
            @Header("Authorization") String token,
            @Path("feedId") Long feedId
    );

    @POST("/star{feedId}")
    Call<ReactionResponse> reaction(
            @Header("Authorization") String token,
            @Path("feedId") Long feedId
    );

    @DELETE("/star/delete/{feedId}")
    Call<Void> deleteReaction(
            @Header("Authorization") String token,
            @Path("feedId") Long feedId
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

    @POST("user/login")
    Call<LoginResponse> login(
            @Body LoginRequest loginRequest
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
