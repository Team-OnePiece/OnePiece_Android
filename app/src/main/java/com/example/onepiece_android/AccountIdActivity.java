package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onepiece_android.databinding.ActivityAccount1Binding;

public class AccountIdActivity extends AppCompatActivity {
    private ActivityAccount1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccount1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountPwActivity.class);
                startActivity(intent);
            }
        });
    }
}