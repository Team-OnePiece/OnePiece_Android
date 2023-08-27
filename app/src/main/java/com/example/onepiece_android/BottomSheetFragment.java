package com.example.onepiece_android;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onepiece_android.activity.LoginActivity;
import com.example.onepiece_android.activity.PostModifyActivity;
import com.example.onepiece_android.databinding.FragmentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentBottomSheetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBottomSheetBinding.inflate(getLayoutInflater());

        binding.bottomSheetModify.setOnClickListener(view -> {
            Intent intent = new Intent(binding.getRoot().getContext(), PostModifyActivity.class);
            dismiss();
            startActivity(intent);
        });

        binding.bottomSheetDelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("삭제하시겠습니까?").setMessage("다시 돌이킬 수 없습니다");

            builder.setPositiveButton("확인", (dialogInterface, i) -> {
                ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
                serverApi.deleteBoard(LoginActivity.token, BoardAdapter.pos).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        AlertDialog.Builder check = new AlertDialog.Builder(view.getContext());
                        if (response.isSuccessful()) {
                            check.setMessage("게시물이 삭제되었습니다.");
                            check.setPositiveButton("확인", (dialogInterface1, i1) -> {});
                        } else {
                            check.setMessage("게시물 삭제에 실패하였습니다.");
                            check.setPositiveButton("확인", (dialogInterface1, i1) -> {});
                        }
                        check.show();
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(binding.getRoot().getContext(), "서버와의 통신에 실패하였습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                dismiss();
            });
            builder.setNegativeButton("취소", (dialogInterface, i) -> dismiss());
            builder.show();
        });

        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
    }
}