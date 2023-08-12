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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.onepiece_android.databinding.ActivityPostupBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class PostUpActivity extends AppCompatActivity {
    private ActivityPostupBinding binding;

    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityPostupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
