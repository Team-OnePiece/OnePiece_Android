package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.text.InputType;
import android.widget.Toast;
import com.example.onepiece_android.databinding.ActivityAccountPwBinding;

public class AccountPwActivity extends AppCompatActivity {
    private ActivityAccountPwBinding binding;
    private boolean pwEye = true;
    private boolean pwCheckEye = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountPwBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAccountPwEyePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pwEye) {
                    binding.btnAccountPwEyePw.setImageResource(R.drawable.edit_pw_open_eye);
                    binding.editAccountPwPw.setInputType(InputType.TYPE_CLASS_TEXT);
                    pwEye = false;
                } else {
                    binding.btnAccountPwEyePw.setImageResource(R.drawable.edit_pw_close_eye);
                    binding.editAccountPwPw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pwEye = true;
                }
            }
        });
        binding.btnAccountPwEyePwCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pwCheckEye) {
                    binding.btnAccountPwEyePwCheck.setImageResource(R.drawable.edit_pw_open_eye);
                    binding.editAccountPwPwCheck.setInputType(InputType.TYPE_CLASS_TEXT);
                    pwCheckEye = false;
                } else {
                    binding.btnAccountPwEyePwCheck.setImageResource(R.drawable.edit_pw_close_eye);
                    binding.editAccountPwPwCheck.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pwCheckEye = true;
                }
            }
        });
        binding.imgAccountPwArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnAccountPwNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPassword = binding.editAccountPwPw.getText().toString();
                String userPasswordCheck = binding.editAccountPwPwCheck.getText().toString();
                if (userPassword.length() == 0) {
                    Toast.makeText(getBaseContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    if(userPassword.equals(userPasswordCheck)) {
                        SignUpRequest signUpRequest = new SignUpRequest();
                        signUpRequest.setUserPassword(userPassword);
                        binding.btnAccountPwNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), AccountStudentActivity.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        binding.textAccountPwWrong.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}