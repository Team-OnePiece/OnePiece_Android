package com.example.onepiece_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.onepiece_android.ApiProvider;
import com.example.onepiece_android.LoginRequest;
import com.example.onepiece_android.LoginResponse;
import com.example.onepiece_android.R;
import com.example.onepiece_android.ServerApi;
import com.example.onepiece_android.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    public static String token;

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
                    binding.btnLogin.setBackgroundResource(R.drawable.login_btn_selected);
                } else {
                    binding.btnLogin.setClickable(false);
                    binding.btnLogin.setBackgroundResource(R.drawable.login_btn_unselected);
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
                updateViewVisibility();
                if(s.length() > 0 && binding.etPassword.getText().length() > 0) {
                    binding.btnLogin.setClickable(true);
                    binding.btnLogin.setBackgroundResource(R.drawable.login_btn_selected);
                } else {
                    binding.btnLogin.setClickable(false);
                    binding.btnLogin.setBackgroundResource(R.drawable.login_btn_unselected);
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
                updateViewVisibility();
                if(s.length() > 0 && binding.editId.getText().length() > 0){
                    binding.btnLogin.setClickable(true);
                    binding.btnLogin.setBackgroundResource(R.drawable.login_btn_selected);
                } else {
                    binding.btnLogin.setClickable(false);
                    binding.btnLogin.setBackgroundResource(R.drawable.login_btn_unselected);
                }
            }
        });

        binding.btnLogin.setOnClickListener(v -> {
            login();
            Intent intent = new Intent(getApplicationContext(), AccountIdActivity.class);
            startActivity(intent);
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
                    binding.btnLogin.setBackgroundResource(R.drawable.login_btn_selected);
                } else {
                    binding.btnLogin.setClickable(false);
                    binding.btnLogin.setBackgroundResource(R.drawable.login_btn_unselected);
                }
            }
        });
    }

    private void updateViewVisibility() {
        if(binding.editId.getText().length() == 0) {
            binding.tvCheckid.setVisibility(View.VISIBLE);
        } else {
            binding.tvCheckid.setVisibility(View.INVISIBLE);
        }

        if(binding.etPassword.getText().length() == 0){
            binding.tvCheckpassword.setVisibility(View.VISIBLE);
        } else {
            binding.tvCheckpassword.setVisibility(View.INVISIBLE);
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
                    token = response.body().getAccessToken();
                } else {
                    binding.tvCheck.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "통신 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
