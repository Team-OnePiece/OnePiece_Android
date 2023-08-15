package com.example.onepiece_android;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TagDeleteApi extends AppCompatActivity {
    private static final String BASE_URL = "http://54.180.94.103:8080";
    private static final String ACCESS_TOKEN = "Bearer <access-token>";


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_post_modify);

        Intent intent = getIntent();
        long tagId = intent.getLongExtra("tagId", -1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        

        Call<Void> call = serverApi.deleteTag(tagId, "Bearer "+ ACCESS_TOKEN);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    showToast("태그가 성공적으로 삭제되었습니다.");
                } else {
                    showToast("태그 삭제 실패 : "+ response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                    showToast("요청 실패 :  "+ t.getMessage());
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
