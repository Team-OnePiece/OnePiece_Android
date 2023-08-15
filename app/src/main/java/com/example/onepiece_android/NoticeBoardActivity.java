package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onepiece_android.databinding.ActivityNoticeBoardBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoticeBoardActivity extends AppCompatActivity {

    private ActivityNoticeBoardBinding binding;
    BoardAdapter boardAdapter;
    ArrayList<Board> arrayList;
    ReadBoardResponse boardResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerNotice.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        boardAdapter = new BoardAdapter(arrayList, getApplicationContext(), "NoticeBoardActivity");
        binding.recyclerNotice.setAdapter(boardAdapter);

        startPage();

        binding.imgNoticeAdd.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class); // 게시물 생성 완료 후 수정
            startActivity(intent);
        });

        binding.imgNoticeMore.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class); // 프로필 수정 완료 후 수정 예정
            startActivity(intent);
        });
    }

    private void startSetPost(ReadBoardResponse response) {
        int total = response.boardInfo.size();
        for (int i = 0; i < total; i++) {
            JsonObject jsonObject = response.getBoardInfo().get(i);

            Long id = jsonObject.get("id").getAsLong();
            String place = jsonObject.get("place").toString();
            String boardImageUrl = jsonObject.get("board_image_url").toString();
            String createAt = jsonObject.get("create_at").toString();
            String nickname = jsonObject.get("nickname").toString();
            String profileImageUrl = jsonObject.get("profile_image_url").toString();
            int grade = jsonObject.get("grade").getAsInt();
            int classNumber = jsonObject.get("class_number").getAsInt();
            int number = jsonObject.get("number").getAsInt();
            int starCount = jsonObject.get("star_count").getAsInt();

            JsonArray tagArray = jsonObject.getAsJsonArray("tags");
            String[] tag = new String[tagArray.size()];
            for (int j = 0; j < tagArray.size(); j++) {
                tag[j] = tagArray.get(j).getAsString();
            }

            Board board = new Board(id, place, boardImageUrl, createAt, nickname, profileImageUrl, tag, grade, classNumber, number, starCount);
            arrayList.add(board);

            boardAdapter.notifyDataSetChanged();
        }
    }

    private void startPage() {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.readBoard().enqueue(new Callback<ReadBoardResponse>() {  // 여기 수정
            @Override
            public void onResponse(Call<ReadBoardResponse> call, Response<ReadBoardResponse> response) {
                boardResponse = response.body();
                startSetPost(boardResponse);
            }
            @Override
            public void onFailure(Call<ReadBoardResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}