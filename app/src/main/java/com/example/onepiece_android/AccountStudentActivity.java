package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.onepiece_android.databinding.ActivityAccountStudentBinding;

public class AccountStudentActivity extends AppCompatActivity {
    private ActivityAccountStudentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAccountStudentNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int schoolNumber = Integer.parseInt(binding.editStudentGrade.getText().toString());
                int classNumber = Integer.parseInt(binding.editStudentClass.getText().toString());
                int studentNumber = Integer.parseInt(binding.editStudentNumber.getText().toString());
                clickNext(schoolNumber, classNumber, studentNumber);
            }
        });
        binding.imgAccountBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void clickNext(int schoolNumber, int classNumber, int studentNumber) {
        if((schoolNumber >= 1 && schoolNumber <= 3) && (classNumber >= 1 && classNumber <= 4) && (classNumber >= 1 && classNumber <= 18)) {
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setSchoolNumber(schoolNumber);
            signUpRequest.setClassNumber(classNumber);
            signUpRequest.setStudentNumber(studentNumber);
            Intent intent = new Intent(getApplicationContext(), AccountNickActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "학년, 반, 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }
    }
}