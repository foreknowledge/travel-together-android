package com.mungziapp.traveltogether;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button writeButton = findViewById(R.id.write_button);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateNoticeActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.notify_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NoticeAdapter noticeAdapter = new NoticeAdapter(this);
        noticeAdapter.addItem(new NoticeItem("준비물 챙겨오세요~~", "준비물은 뭐시기 뭐시기"));
        noticeAdapter.addItem(new NoticeItem("한라산 등반 시 고산병 유의!", "고산병이란..."));
        noticeAdapter.addItem(new NoticeItem("모임 시간 & 장소", "9시까지 서울역으로 모여라!!"));

        recyclerView.setAdapter(noticeAdapter);
    }

}
