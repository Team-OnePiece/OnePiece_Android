package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onepiece_android.databinding.ActivityMyPageBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {

    private ActivityMyPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userInfo();

        binding.btnMyModify.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), ProfileModifyActivity.class);
            startActivity(intent);
        });

        binding.imgMyBack.setOnClickListener(view -> finish());
    }

    public void userInfo() {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.userInfo(LoginActivity.token).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()) {
                    UserInfoResponse userInfo = response.body();
                    String nickname = userInfo.getNickname() + "님";
                    String profile = userInfo.getProfileImageUrl();

                    binding.textMyNickname.setText(nickname);
                    Glide.with(MyPageActivity.this).load(profile).into(binding.imgMyProfile);
                } else {
                    Toast.makeText(getBaseContext(), "정보를 불러오는데 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 연동에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}