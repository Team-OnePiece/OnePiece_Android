package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.onepiece_android.databinding.ActivityPostModifyBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostModifyActivity extends AppCompatActivity {

    private ActivityPostModifyBinding binding;
    private PostModifyApi postModifyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostModifyBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_post_modify);

        postModifyApi = new PostModifyApi();
        long feedId = 123;
        String accessToken = "Bearer <access-token>";
        String place = "대마고";

        postModifyApi.postModify(feedId, accessToken, place, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    showToast("성공적으로 업데이트 되었습니다.");
                } else {
                    showToast("업데이트에 실패하였습니다. 다시 시도해주세요.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("오류가 발생하였습니다. 네트워크 상태를 확인해주세요.");
            }
        });


        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.tvOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PostModifyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(PostModifyActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}