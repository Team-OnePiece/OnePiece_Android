package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.onepiece_android.databinding.ActivityNoticeBoardBinding;
import com.example.onepiece_android.databinding.BottomSheetBinding;
import com.example.onepiece_android.databinding.RecyclerItemBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;


public class NoticeBoardActivity extends AppCompatActivity {

    private ActivityNoticeBoardBinding binding;
    private BottomSheetBinding bottomBinding;
    private RecyclerItemBinding recyclerBinding;
    private ArrayList<Board> boardList;
    BoardAdapter boardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerBinding = RecyclerItemBinding.inflate(getLayoutInflater());

        bottomBinding = BottomSheetBinding.inflate(getLayoutInflater());
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottomSheetView = inflater.inflate(R.layout.bottom_sheet, null, false);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getApplicationContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        boardAdapter = new BoardAdapter();
        boardList = new ArrayList<>();

        binding.recyclerNotice.setAdapter(boardAdapter);
        binding.recyclerNotice.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        binding.imgNoticeAdd.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);  // 게시글 추가 완료 후 변경 예정
            startActivity(intent);
        });

        binding.imgNoticeMore.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class); // 프로필 수정 완료 후 변경 예정
            startActivity(intent);
        });

        recyclerBinding.imgItemMore.setOnClickListener(view -> bottomSheetDialog.show());

        bottomBinding.bottomSheetModify.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class); // 게시글 수정 완료 수 수정 예정
            startActivity(intent);
        });

        bottomBinding.bottomSheetDelete.setOnClickListener(view -> boardDelete());

        recyclerBinding.imgItemReaction.setOnClickListener(view -> reaction());
    }

    public void boardDelete() {
    }

    public void reaction() {
    }
}