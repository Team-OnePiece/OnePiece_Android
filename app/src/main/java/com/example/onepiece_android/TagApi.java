package com.example.onepiece_android;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TagApi {
    private static final String BASE_URL = "http://54.180.94.103:8080";

    private ServerApi serverApi;
    private Context context;

    public TagApi(Context context){
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);
    }

    public void createTag(Long boardId, String accessToken, String tagName){
        TagRequest tagRequest = new TagRequest(tagName);
        Call<TagResponse> call = serverApi.createTag(boardId, "Bearer " + accessToken, tagRequest);
        call.enqueue(new Callback<TagResponse>() {
            @Override
            public void onResponse(Call<TagResponse> call, Response<TagResponse> response) {
                if(response.isSuccessful()) {
                    if(response.isSuccessful()){
                        TagResponse tagResponse = response.body();
                        long tagId = tagResponse.getTagId();
                        showToast("태그 생성 성공 Tag ID : " + tagId);
                    }
                } else{
                    showToast("태그 생성 실패");
                }
            }

            @Override
            public void onFailure(Call<TagResponse> call, Throwable t) {
               showToast("네트워크 오류 : "+ t.getMessage());
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
