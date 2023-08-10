package com.example.onepiece_android;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.onepiece_android.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        buttonIsSelected();
    }


    public void buttonIsSelected() {
        binding.editId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    binding.btnLogin.setClickable(true);
                    binding.btnLogin.setBackgroundResource(R.drawable.main_btn_selected);
                } else {
                    binding.btnLogin.setClickable(false);
                    binding.btnLogin.setBackgroundResource(R.drawable.main_btn_unselected);
                }
            }

        });

        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    binding.btnLogin.setClickable(true);
                    binding.btnLogin.setBackgroundResource(R.drawable.main_btn_selected);
                } else {
                    binding.btnLogin.setClickable(false);
                    binding.btnLogin.setBackgroundResource(R.drawable.main_btn_unselected);
                }
            }
        });
    }




    private void LoginCheck(){
        String userId = binding.editId.getText().toString();
        String userPassword = binding.etPassword.getText().toString();

        if(userId.length()==0){
            Toast.makeText(LoginActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else if (userPassword.length()==0){
            Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            login();
            Log.d(TAG, "userId : " + userId);
            Log.d(TAG, "userPassword : " + userPassword);
        }
    }


    public void login() {
        String userId = binding.editId.getText().toString();
        String userPassword = binding.etPassword.getText().toString();

        LoginRequest loginRequest = new LoginRequest(userId, userPassword);
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}