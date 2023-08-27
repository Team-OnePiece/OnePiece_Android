package com.example.onepiece_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.onepiece_android.R;
import com.example.onepiece_android.databinding.ActivityPostModifyBinding;

public class PostModifyActivity extends AppCompatActivity {

    private ActivityPostModifyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostModifyBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_post_modify);

        binding.imgBack.setOnClickListener(v -> finish());

        binding.tvOkay.setOnClickListener(v -> {
            Intent intent = new Intent(PostModifyActivity.this, NoticeBoardActivity.class);
            startActivity(intent);
            finish();
        });
    }
}