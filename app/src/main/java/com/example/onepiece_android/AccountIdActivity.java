package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
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

        binding.btnAccountIdNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountPwActivity.class);
                startActivity(intent);
                binding = ActivityAccountIdBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());

                binding.btnAccountIdCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userId = binding.editAccountIdId.getText().toString();
                        if (userId.length() == 0) {
                            Toast.makeText(getBaseContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            checkId(userId);
                        }
                    }
                });
                binding.imgAccountIdArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }
        });
    }
    private void checkId(String userId) {
        DuplicateIdRequest duplicateIdRequest = new DuplicateIdRequest(userId);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.idDuplicate(duplicateIdRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountIdActivity.this);
                if (response.isSuccessful()) {
                    builder.setMessage("사용 가능한 아이디입니다.");
                    SignUpRequest signUpRequest = new SignUpRequest();
                    signUpRequest.setUserId(userId);
                    binding.btnAccountIdNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), AccountPwActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    builder.setMessage("이미 사용된 아이디입니다.");
                }
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
