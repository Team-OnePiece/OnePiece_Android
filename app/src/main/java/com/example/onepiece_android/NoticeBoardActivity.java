package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.onepiece_android.databinding.ActivityNoticeBoardBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeBoardActivity extends AppCompatActivity {

    private ActivityNoticeBoardBinding binding;
    private ArrayList<Board> boardList;
    BoardAdapter boardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        boardAdapter = new BoardAdapter();
        boardList = new ArrayList<>();

        binding.recyclerNotice.setAdapter(boardAdapter);
        binding.recyclerNotice.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }
}