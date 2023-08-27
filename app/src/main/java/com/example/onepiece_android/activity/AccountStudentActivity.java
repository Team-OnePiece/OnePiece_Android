package com.example.onepiece_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onepiece_android.ApiProvider;
import com.example.onepiece_android.ServerApi;
import com.example.onepiece_android.SignUpRequest;
import com.example.onepiece_android.databinding.ActivityAccountStudentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountStudentActivity extends AppCompatActivity {
    private ActivityAccountStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAccountStudentNext.setOnClickListener(view -> {
            int grade = Integer.parseInt(binding.editStudentGrade.getText().toString());
            int classNumber = Integer.parseInt(binding.editStudentClass.getText().toString());
            int number = Integer.parseInt(binding.editStudentNumber.getText().toString());
            clickNext(grade, classNumber, number);
        });
        binding.imgAccountBack.setOnClickListener(view -> finish());
    }

    private void clickNext(int grade, int classNumber, int number) {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.studentIdDuplicate(grade, classNumber, number).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    SignUpRequest signUpRequest = new SignUpRequest();
                    signUpRequest.setGrade(grade);
                    signUpRequest.setClass_number(classNumber);
                    signUpRequest.setNumber(number);
                    Intent intent = new Intent(getBaseContext(), AccountNickActivity.class);
                    startActivity(intent);
                } else {
                    binding.textStudentWrong.setVisibility(View.VISIBLE);
                    Log.d("response", String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
                Log.d("fail", t.getMessage());
            }
        });
    }
}