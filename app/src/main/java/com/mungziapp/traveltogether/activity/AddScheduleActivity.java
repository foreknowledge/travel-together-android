package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.model.DateObject;

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
		setTimePicker();
	}

	private void init() {
		whiteColor = Color.parseColor("#FFFFFF");
		normalColor = ContextCompat.getColor(getApplicationContext(), R.color.colorGrayLight);

		TextView textDayN = findViewById(R.id.text_day_n);

		Intent intent = getIntent();
		String strDayN = "DAY " + intent.getStringExtra("day_n");
		textDayN.setText(strDayN);
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

	private void setTimePicker() {
		final Button btnScheduleTime = findViewById(R.id.schedule_time);
		btnScheduleTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int hour, minute;

				String[] time = btnScheduleTime.getText().toString().split(":");
				if (time.length == 2) {
					hour = Integer.valueOf(time[0]);
					minute = Integer.valueOf(time[1]);
				} else {
					DateObject now = new DateObject();
					hour = now.getHour();
					minute = now.getMinute();
				}
				new TimePickerDialog(AddScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker timePicker, int i, int i1) {
						String strTime;
						if (i1 < 10)
							strTime = i + ":0" + i1;
						else
							strTime = i + ":" + i1;

						btnScheduleTime.setText(strTime);
					}
				}, hour, minute, false).show();
			}
		});
	}
}
