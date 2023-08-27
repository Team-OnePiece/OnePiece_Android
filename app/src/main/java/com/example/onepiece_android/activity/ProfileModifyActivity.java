package com.example.onepiece_android.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onepiece_android.ApiProvider;
import com.example.onepiece_android.ServerApi;
import com.example.onepiece_android.UserInfoResponse;
import com.example.onepiece_android.databinding.ActivityProfileModifyBinding;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileModifyActivity extends AppCompatActivity {

    private ActivityProfileModifyBinding binding;

    private String nickname;
    private String profile;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileModifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userInfo();

        binding.textProfileCheck.setOnClickListener(view -> {
            binding.textProfileWrong.setVisibility(View.INVISIBLE);
            if (nickname.equals(binding.editProfileNickname.getText().toString())) {
                finish();
            } else {
                nickname = binding.editProfileNickname.getText().toString();
                checkNick(nickname);
            }
        });

        binding.textProfileModify.setOnClickListener(view -> changePicture());
        binding.cardProfilePicture.setOnClickListener(view -> changePicture());
    }

    public void userInfo() {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.userInfo(LoginActivity.token).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()) {
                    UserInfoResponse userInfo = response.body();
                    nickname = userInfo.getNickname();
                    profile = userInfo.getProfileImageUrl();

                    binding.editProfileNickname.setText(nickname);
                    Glide.with(ProfileModifyActivity.this).load(profile).into(binding.imgProfilePicture);
                } else {
                    Toast.makeText(getBaseContext(), "정보를 불러오는데 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 연동에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkNick(String nickname) {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.nickDuplicate(nickname).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileModifyActivity.this);
                if (response.isSuccessful()) {
                    changeNick(nickname);
                } else if (response.code() == 409) {
                    builder.setMessage("이미 사용된 별명입니다.");
                    builder.setPositiveButton("확인", (dialogInterface, i) -> {}).show();
                } else {
                    binding.textProfileWrong.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeNick(String nickname) {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.nicknameModify(LoginActivity.token, nickname).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileModifyActivity.this);
                if (response.isSuccessful()) {
                    builder.setMessage("별명 변경이 완료되었습니다.");
                    builder.setPositiveButton("확인", (dialogInterface, i) -> finish()).show();
                } else {
                    builder.setMessage("별명 변경에 실패하였습니다.");
                    builder.setPositiveButton("확인", (dialogInterface, i) -> {}).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 연결에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_PICK);
        activityResultLauncher.launch(intent);

        String imagePath = getPathFromURI(imageUri);
        File imageFile = new File(imagePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.profileImageUpload(LoginActivity.token, imagePart).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Glide.with(ProfileModifyActivity.this).load(imageUri).into(binding.imgProfilePicture);
                } else {
                    Toast.makeText(getBaseContext(), "이미지를 로드하는데 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();
                    imageUri = intent.getData();
                }
            }
    );

    private String getPathFromURI(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        }
    }
}