package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onepiece_android.databinding.ActivityPostModifyBinding;

public class PostModifyActivity extends AppCompatActivity {

    private ActivityPostModifyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostModifyBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_post_modify);

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.tvOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PostModifyActivity.this, NoticeBoardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}