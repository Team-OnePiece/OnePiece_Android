package com.example.onepiece_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onepiece_android.databinding.ActivityPostModifyBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostModifyActivity extends AppCompatActivity {

    private ActivityPostModifyBinding binding;
    private PostModifyApi postModifyApi;
    private ImageView imageDelete;

    private static final String BASE_URL = "http://54.180.94.103:8080";
    private static final String ACCESS_TOKEN = "Bearer <access-token>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostModifyBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_post_modify);

        postModifyApi = new PostModifyApi();
        long feedId = 123;
        String accessToken = "Bearer <access-token>";
        String place = "대마고";

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageDelete = findViewById(R.id.img_delete);
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTag();
            }
        });



        postModifyApi.postModify(feedId, accessToken, place, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    showToast("성공적으로 업데이트 되었습니다.");
                } else {
                    showToast("업데이트에 실패하였습니다. 다시 시도해주세요.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("오류가 발생하였습니다. 네트워크 상태를 확인해주세요.");
            }
        });


        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.tvOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PostModifyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void deleteTag(){
        long tagId = getTagIdFromIntent();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi serverApi = retrofit.create(ServerApi.class);

        Call<Void> call = serverApi.deleteTag(tagId, "Bearer "+ ACCESS_TOKEN);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    showToast("태그가 성공적으로 삭제되었습니다.");
                } else {
                    showToast("태그 삭제 실패 : "+ response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("요청 실패 : "+ t.getMessage());
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(PostModifyActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private long getTagIdFromIntent(){
        Intent intent = getIntent();
        return intent.getLongExtra("tagId", -1);
    }
}