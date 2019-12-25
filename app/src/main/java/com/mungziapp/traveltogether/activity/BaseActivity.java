package com.mungziapp.traveltogether.activity;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
	protected void redirectLoginActivity() {
		final Intent intent = new Intent(this, LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

		finish();
	}

	protected void redirectMainActivity() {
		final Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

		finish();
	}
}
