package com.example.onepiece_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.onepiece_android.databinding.ActivityAccountIdBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountIdActivity extends AppCompatActivity {
    private ActivityAccountIdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAccountIdNext.setOnClickListener(view -> {
            String userId = binding.editAccountId.getText().toString();
            checkId(userId);
        });

        binding.imgAccountBack.setOnClickListener(view -> finish());
    }

    private void checkId(String userId) {
        if (userId.length() == 0) {
            binding.textIdWrong.setVisibility(View.VISIBLE);
        } else {
            ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
            serverApi.idDuplicate(userId).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AccountIdActivity.this);
                    if (response.isSuccessful()) {
                        builder.setMessage("사용 가능한 아이디입니다.");
                        builder.setPositiveButton("확인", (dialogInterface, i) -> {
                        }).show();
                        SignUpRequest signUpRequest = new SignUpRequest();
                        signUpRequest.setAccount_id(userId);
                        Intent intent = new Intent(getBaseContext(), AccountPwActivity.class);
                        startActivity(intent);
                    } else if (response.code() == 409) {
                        builder.setMessage("이미 사용된 아이디입니다.");
                        builder.setPositiveButton("확인", (dialogInterface, i) -> {}).show();
                    } else {
                        binding.textIdWrong.setVisibility(View.VISIBLE);
                        Log.d("response", String.valueOf(response.code()));
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Toast.makeText(AccountIdActivity.this, "서버와의 연결에 실패하였습니다", Toast.LENGTH_SHORT).show();
                    Log.d("fail", t.getMessage());
                }
            });
        }
    }
}