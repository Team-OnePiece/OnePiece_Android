package com.example.onepiece_android;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;

import com.example.onepiece_android.databinding.ActivityPostUpBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class PostUpActivity extends AppCompatActivity {


    private ActivityPostUpBinding binding;

    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityPostUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ChipGroup chipGroup = binding.chipGroup;
        TextView tvPlus = binding.tvPlus;
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
}