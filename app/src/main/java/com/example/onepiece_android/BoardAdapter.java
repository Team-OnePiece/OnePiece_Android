package com.example.onepiece_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onepiece_android.databinding.RecyclerItemBinding;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private RecyclerItemBinding recyclerBinding;
    private final ArrayList<Board> arrayList;
    public static Long pos;
    Context context;
    String activity;
    Boolean starReaction = false;

    public BoardAdapter(ArrayList<Board> arrayList, Context context, String activity) {
        this.arrayList = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        recyclerBinding = RecyclerItemBinding.bind(view);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recyclerBinding.textItemPlace.setText(arrayList.get(position).getPlace());
        recyclerBinding.textItemDay.setText(arrayList.get(position).getCreate_at());
        recyclerBinding.textItemNick.setText(arrayList.get(position).getNickname());
        recyclerBinding.textItemCount.setText(arrayList.get(position).getStar_count());
        Glide.with(context).load(arrayList.get(position).getProfile_image_url()).into(recyclerBinding.imgItemProfile);
        Glide.with(context).load(arrayList.get(position).getBoard_image_url()).into(recyclerBinding.imgItemContent);

        int studentNumber = (arrayList.get(position).getGrade() * 1000) + (arrayList.get(position).getClass_number() * 100) + (arrayList.get(position).getNumber());
        recyclerBinding.textItemNumber.setText(String.valueOf(studentNumber));

        String[] tag = arrayList.get(position).getTags();
        for (int i = 0; tag[i] != null; i++) {
            TextView textView = new TextView(context);
            textView.setText(tag[i]);
            textView.setTextAppearance(R.style.notice_text_tag);
            recyclerBinding.layItemTag.addView(textView);
        }

        if (arrayList.get(position).getNickname().equals(MainActivity.token)) {  // MainActivity.token은 프로필 수정 병합 후 수정 예정 (프로필 수정 UserInfo() 함수 속 닉네임)
            recyclerBinding.imgItemMore.setVisibility(View.VISIBLE);
        }

        recyclerBinding.imgItemReaction.setOnClickListener(view -> {
            pos = arrayList.get(position).getId();
            if (starReaction) {
                deleteReaction(pos);
            } else {
                plusReaction(pos);
            }
        });

        recyclerBinding.imgItemMore.setOnClickListener(view -> {
            pos = arrayList.get(position).getId();
            BottomSheetFragment bottomSheet = new BottomSheetFragment();
            bottomSheet.show(((AppCompatActivity) context).getSupportFragmentManager(), bottomSheet.getTag());
        });
    }

    private void plusReaction(Long pos) {
        ReactionResponse reactionResponse = new ReactionResponse();
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.reaction(MainActivity.token, pos).enqueue(new Callback<ReactionResponse>() {
            @Override
            public void onResponse(Call<ReactionResponse> call, Response<ReactionResponse> response) {
                if (response.isSuccessful()) {
                    recyclerBinding.imgItemReaction.setImageResource(R.drawable.notice_reaction);
                    recyclerBinding.textItemCount.setText(reactionResponse.getStar_counts());
                    starReaction = true;
                } else {
                    Toast.makeText(context, "반응 등록에 실패하였습니다", Toast.LENGTH_SHORT).show();
                    Log.d("response", String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<ReactionResponse> call, Throwable t) {
                Toast.makeText(context, "서버와의 연결에 실패하였습니다", Toast.LENGTH_SHORT).show();
                Log.d("fail", t.getMessage());
            }
        });
    }

    private void deleteReaction(Long pos) {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.deleteReaction(MainActivity.token, pos).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    recyclerBinding.imgItemReaction.setImageResource(R.drawable.notice_no_reaction);
                    int count = Integer.parseInt(recyclerBinding.textItemCount.getText().toString());
                    count--;
                    recyclerBinding.textItemCount.setText(String.valueOf(count));
                    starReaction = false;
                } else {
                    Toast.makeText(context, "반응 삭제에 실패하였습니다", Toast.LENGTH_SHORT).show();
                    Log.d("response", String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "서버와의 연결에 실패하였습니다", Toast.LENGTH_SHORT).show();
                Log.d("fail", t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
