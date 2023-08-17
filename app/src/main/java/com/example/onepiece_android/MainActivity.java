package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // 토큰 임시 저장
    public static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW9ldW4yNDIyIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY5MTk1MDgyMywiZXhwIjoxNjkxOTU4MDIzfQ.URp1S0MQwovzeQ2vfxbm6gn5o4MwDD4dvCkFjRSxKIc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}