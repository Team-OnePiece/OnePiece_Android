package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
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

        binding.imgAccountBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnAccountPwNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPassword = binding.editAccountPw.getText().toString();
                String userPasswordCheck = binding.editAccountPwCheck.getText().toString();
                if (userPassword.length() == 0) {
                    Toast.makeText(getBaseContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    clickNext(userPassword, userPasswordCheck);
                }
            }
        });
    }
    private void clickNext(String userPassword, String userPasswordCheck) {
        if(userPassword.equals(userPasswordCheck)) {
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setUserPassword(userPassword);
            Intent intent = new Intent(getApplicationContext(), AccountStudentActivity.class);
            startActivity(intent);
        } else {
            binding.textPwWrong.setVisibility(View.VISIBLE);
        }
    }
}