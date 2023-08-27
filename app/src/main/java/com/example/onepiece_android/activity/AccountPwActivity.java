package com.example.onepiece_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onepiece_android.SignUpRequest;
import com.example.onepiece_android.databinding.ActivityAccountPwBinding;

public class AccountPwActivity extends AppCompatActivity {
    private ActivityAccountPwBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountPwBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editAccountPw.setSelection(binding.editAccountPw.length());
        binding.editAccountPwCheck.setSelection(binding.editAccountPwCheck.length());

        binding.imgAccountBack.setOnClickListener(view -> finish());
        
        binding.btnAccountPwNext.setOnClickListener(view -> {
            String userPassword = binding.editAccountPw.getText().toString();
            String userPasswordCheck = binding.editAccountPwCheck.getText().toString();
            clickNext(userPassword, userPasswordCheck);
        });
    }
    
    private void clickNext(String userPassword, String userPasswordCheck) {
        if (userPassword.length() == 0) {
            binding.textPwWrong.setVisibility(View.VISIBLE);
        } else if (userPassword.equals(userPasswordCheck)) {
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setPassword(userPassword);
            signUpRequest.setPassword_valid(userPasswordCheck);
            Intent intent = new Intent(getBaseContext(), AccountStudentActivity.class);
            startActivity(intent);
        } else {
            binding.textPwWrong.setVisibility(View.VISIBLE);
        }
    }
}