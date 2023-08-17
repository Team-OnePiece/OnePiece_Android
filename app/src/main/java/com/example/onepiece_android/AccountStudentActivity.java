package com.example.onepiece_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        if ((grade >= 1 && grade <= 3) && (classNumber >= 1 && classNumber <= 4) && (number >= 1 && number <= 18)) {
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
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Toast.makeText(getBaseContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            binding.textStudentWrong.setVisibility(View.VISIBLE);
        }
    }
}