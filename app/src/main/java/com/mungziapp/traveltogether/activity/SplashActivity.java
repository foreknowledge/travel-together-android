package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.app.TravelHelper;

public class SplashActivity extends AppCompatActivity {
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		TravelHelper.init(getApplicationContext());
		final Intent intent = new Intent(getApplicationContext(), MainActivity.class);

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				//Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}, 1000);
	}
}
