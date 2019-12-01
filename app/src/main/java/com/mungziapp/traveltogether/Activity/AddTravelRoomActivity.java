package com.mungziapp.traveltogether.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.mungziapp.traveltogether.R;

import java.util.Calendar;

public class AddTravelRoomActivity extends AppCompatActivity {
    private Button btnStartDate;
    private Button btnEndDate;
    private Button btnPickCoverImg;
    private Button btnRePickCoverImg;
    private int flag;

    final private int SET_START_DATE = 1;
    final private int SET_END_DATE = 2;

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String text = i + ". " + i1 + ". " + i2;

            if (flag == SET_START_DATE)
                btnStartDate.setText(text);
            else if (flag == SET_END_DATE)
                btnEndDate.setText(text);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_room);

        Button btnSave = findViewById(R.id.btn_save);
        Button btnCancel = findViewById(R.id.btn_cancel);

        //EditText editRoomTitle = findViewById(R.id.edit_travel_title);
        btnStartDate = findViewById(R.id.btn_pick_start_date);
        btnEndDate = findViewById(R.id.btn_pick_end_date);
        btnPickCoverImg = findViewById(R.id.btn_pick_cover_img);
        btnRePickCoverImg = findViewById(R.id.btn_re_pick_cover_img);

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int date = Calendar.getInstance().get(Calendar.DATE);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, year, month, date);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = SET_START_DATE;
                datePickerDialog.show();
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = SET_END_DATE;
                datePickerDialog.show();
            }
        });

        btnPickCoverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPickCoverImg.setVisibility(View.INVISIBLE);
                btnRePickCoverImg.setVisibility(View.VISIBLE);
                // 갤러리 이미지 선택해서 가져오기

            }
        });

        btnRePickCoverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 갤러리 이미지 선택해서 가져오기

            }
        });

    }
}
