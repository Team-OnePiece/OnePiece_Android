package com.example.onepiece_android;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onepiece_android.databinding.ActivityPostUpBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostUpActivity extends AppCompatActivity {
    private ActivityPostUpBinding binding;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private List<String> enteredTags = new ArrayList<>();
    private TagApi tagApi;
    private ServerApi serverApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tagApi = new TagApi(this);
        serverApi = PostUpRequest.getClient().create(ServerApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityPostUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tagApi = new TagApi(this);
        ChipGroup chipGroup = binding.chipGroup;

        TextView tvPlus = binding.tvPlus;
        tvPlus.setOnClickListener(v -> {
            String tagText = binding.etTag.getText().toString().trim();
            if (!tagText.isEmpty()) {
                if (enteredTags.size() < 6 && tagText.length() <= 5) {
                    Chip chip = new Chip(PostUpActivity.this);
                    chip.setText(tagText);
                    chip.setCloseIconVisible(true);
                    chip.setOnCloseIconClickListener(v1 -> {
                        chipGroup.removeView(chip);
                        enteredTags.remove(tagText);
                    });

                    chip.setChipBackgroundColorResource(R.color.gray_000);

                    Typeface newTypeface = Typeface.create("font_orbit", Typeface.NORMAL);
                    chip.setTypeface(newTypeface);

                    chip.setTextColor(ContextCompat.getColor(PostUpActivity.this, R.color.gray_700));

                    chipGroup.addView(chip);
                    enteredTags.add(tagText);
                    binding.etTag.setText("");
                } else {
                    String toastMessage;
                    if(enteredTags.size() >= 6){
                        toastMessage = "태그는 최대 6개까지 추가할 수 있습니다.";
                    } else {
                        toastMessage = "태그는 최대 5글자까지 작성할 수 있습니다.";
                    }
                    Toast.makeText(PostUpActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
      
        ServerApi serverApi = PostUpRequest.getClient().create(ServerApi.class);

        binding.tvOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = binding.etPlace.getText().toString();

                if(!place.isEmpty()){
                    RequestBody placeRequestBody = RequestBody.create(MediaType.parse("text/plain"), place);
                    MultipartBody.Part imagePart = MultipartBody.Part.createFormData("place", place, placeRequestBody);

                    String accessToken = "Bearer <access-token>";
                    int groupId = 1;

                    Call<PostUpResponse> call = serverApi.postUp(groupId, imagePart, place, accessToken);

                    call.enqueue(new Callback<PostUpResponse>() {
                        @Override
                        public void onResponse(Call<PostUpResponse> call, Response<PostUpResponse> response) {                            
                            if(response.isSuccessful()) {
                                int feedId = response.body().getFeed_id();
                                Toast.makeText(PostUpActivity.this, "서버 응답 성공 Feed ID : " + feedId, Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(PostUpActivity.this,"통신 실패 : " + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<PostUpResponse> call, Throwable t) {
                            Toast.makeText(PostUpActivity.this, "통신 실패 : "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(PostUpActivity.this, "위치 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textView = binding.tvSpinner;
        binding.spGroup.setAdapter(ArrayAdapter.createFromResource(this, R.array.arr_group, android.R.layout.simple_spinner_item));
        binding.spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(getResources().getStringArray(R.array.arr_group)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tagText = binding.etTag.getText().toString().trim();
              
                if(!tagText.isEmpty()){
                    enteredTags.add(tagText);
                    Toast.makeText(PostUpActivity.this, "태그가 추가되었습니다." + tagText, Toast.LENGTH_SHORT).show();

                    binding.etTag.setText("");
                }
            }
        });

        tvPlus.setOnClickListener(v -> {
            String tagText = binding.etTag.getText().toString().trim();
            if (!tagText.isEmpty()) {
                Chip chip = new Chip(PostUpActivity.this);
                chip.setText(tagText);
                chip.setCloseIconVisible(true);
                chip.setOnCloseIconClickListener(v1 -> chipGroup.removeView(chip));

                chip.setChipBackgroundColorResource(R.color.gray_000);

                Typeface newTypeface = Typeface.create("font_orbit", Typeface.NORMAL);
                chip.setTypeface(newTypeface);

                chip.setTextColor(ContextCompat.getColor(PostUpActivity.this, R.color.gray_700));

                chipGroup.addView(chip);
                binding.etTag.setText("");
            }
        });

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Uri selectedImageUri = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                            binding.imgAdd.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        binding.imgAdd.setOnClickListener(v -> openGallery());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }

    private void uploadImage(File imageFile, int groupId){
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(),requestBody);

       RequestBody placeRequestBody = RequestBody.create(MediaType.parse("text/plain"),binding.etPlace.getText().toString());

       String accessToken = "Bearer <access-token>";
       ServerApi serverApi = PostUpRequest.getClient().create(ServerApi.class);
       Call<PostUpResponse> call = serverApi.postUp(groupId, imagePart, placeRequestBody.toString(), accessToken);

       call.enqueue(new Callback<PostUpResponse>() {
           @Override
           public void onResponse(Call<PostUpResponse> call, Response<PostUpResponse> response) {
               if(response.isSuccessful()){
                   PostUpResponse postUpResponse = response.body();
                   int feedId = postUpResponse.getFeed_id();
                   Toast.makeText(PostUpActivity.this, "이미지 업로드 성공. Feed ID : " + feedId, Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(PostUpActivity.this, "이미지 업로드 실패", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<PostUpResponse> call, Throwable t) {
                Toast.makeText(PostUpActivity.this,"통신 실패 : " + t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });

    }
}
