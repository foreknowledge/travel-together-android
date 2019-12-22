package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mungziapp.traveltogether.R;

public class AddScheduleActivity extends AppCompatActivity {
	private boolean btnNormalChecked = true;
	private int whiteColor;
	private int normalColor;

	private RadioButton btnNormalSchedule;
	private RadioButton btnMoveSchedule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_schedule);

		init();
		setButtons();
		TextView DayN = findViewById(R.id.text_day_n);
		String strDayN = "DAY 1";
		DayN.setText(strDayN);
	}

	private void init() {
		whiteColor = Color.parseColor("#FFFFFF");
		normalColor = ContextCompat.getColor(getApplicationContext(), R.color.colorGrayLight);
	}

	private void setButtons() {
		Button btnSave = findViewById(R.id.btn_save);
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		Button btnCancel = findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		btnNormalSchedule = findViewById(R.id.btn_normal_schedule);
		btnNormalSchedule.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!btnNormalChecked) {
					btnNormalSchedule.setChecked(true);
					btnNormalSchedule.setTextColor(whiteColor);
					btnMoveSchedule.setChecked(false);
					btnMoveSchedule.setTextColor(normalColor);

					btnNormalChecked = true;
				}
			}
		});

		btnMoveSchedule = findViewById(R.id.btn_move_schedule);
		btnMoveSchedule.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (btnNormalChecked) {
					btnMoveSchedule.setChecked(true);
					btnMoveSchedule.setTextColor(whiteColor);
					btnNormalSchedule.setChecked(false);
					btnNormalSchedule.setTextColor(normalColor);

					btnNormalChecked = false;
				}
			}
		});
	}
}
