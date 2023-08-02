package com.example.onepiece_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.example.onepiece_android.databinding.ActivityAccountNickBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountNickActivity extends AppCompatActivity {
    private ActivityAccountNickBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountNickBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAccountNickNext.setOnClickListener(view -> {
            String nickname = binding.editAccountNick.getText().toString();
            checkNick(nickname);
        });

        binding.imgAccountBack.setOnClickListener(view -> finish());
    }

    private void  checkNick(String nickname) {
        if (nickname.length() == 0) {
            binding.textNickWrong.setVisibility(View.VISIBLE);
        } else {
            ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
            serverApi.nickDuplicate(nickname).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AccountNickActivity.this);
                    if (response.isSuccessful()) {
                        builder.setMessage("사용 가능한 별명입니다.");
                        builder.setPositiveButton("확인", (dialogInterface, i) -> {}).show();
                        SignUpRequest signUpRequest = new SignUpRequest();
                        signUpRequest.setNickname(nickname);
                        signUp();
                    } else {
                        builder.setMessage("이미 사용된 별명입니다.");
                        builder.setPositiveButton("확인", (dialogInterface, i) -> {}).show();
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Toast.makeText(getBaseContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void signUp() {
        SignUpRequest signUpRequest = new SignUpRequest();
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.signUp(signUpRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class); // 로그인 완료 후 변경 예정
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "회원가입에 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}