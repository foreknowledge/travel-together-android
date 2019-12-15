package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.chrisbanes.photoview.PhotoView;
import com.mungziapp.traveltogether.R;

public class PhotoViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_view);

		PhotoView photoView = findViewById(R.id.photo_view);
		photoView.setImageResource(R.drawable.usr_profile_img);

		Button btnGoBefore = findViewById(R.id.btn_go_before);
		btnGoBefore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
}
