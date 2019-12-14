package com.mungziapp.traveltogether.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.mungziapp.traveltogether.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends BaseActivity {
	private static final String TAG = "Settings :: ";
	private static final int NORMAL_MODE = 0;
	private static final int EDIT_MODE = 1;

	private int mode = NORMAL_MODE;

	private Button btnEdit;
	private Button btnGoBefore;
	private Button btnCancel;
	private LinearLayout buttons;

	private TextView profileName;
	private TextView profileMessage;
	private EditText editName;
	private EditText editMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		setButtons();
		init();
	}

	private void init() {
		profileName.setText("김예지");
		profileMessage.setText("상태 메시지");
	}

	private void setButtons() {
		btnEdit = findViewById(R.id.btn_edit);
		btnEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mode == NORMAL_MODE) {
					setEditMode();

					editName.setText(profileName.getText().toString());
					editMessage.setText(profileMessage.getText().toString());
				}
				else {
					setNormalMode();

					profileName.setText(editName.getText().toString());
					profileMessage.setText(editMessage.getText().toString());
				}
			}
		});

		btnGoBefore = findViewById(R.id.btn_go_before);
		btnGoBefore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		Button btnLogout = findViewById(R.id.btn_logout);
		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onClickLogout();
			}
		});

		Button btnRemoveAccount = findViewById(R.id.btn_remove_account);
		btnRemoveAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onClickUnlink();
			}
		});

		btnCancel = findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// dialog 보여주기
				setNormalMode();
			}
		});

		CircleImageView profileImg = findViewById(R.id.profile_img);
		profileImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		profileName = findViewById(R.id.text_name);
		profileMessage = findViewById(R.id.text_message);
		editName = findViewById(R.id.edit_name);
		editMessage = findViewById(R.id.edit_message);
		buttons = findViewById(R.id.buttons);
	}

	private void setNormalMode() {
		mode = NORMAL_MODE;

		btnGoBefore.setVisibility(View.VISIBLE);
		btnCancel.setVisibility(View.INVISIBLE);

		String strEdit = "Edit";
		btnEdit.setText(strEdit);

		buttons.setVisibility(View.VISIBLE);

		profileName.setVisibility(View.VISIBLE);
		profileMessage.setVisibility(View.VISIBLE);
		editName.setVisibility(View.GONE);
		editMessage.setVisibility(View.GONE);
	}

	private void setEditMode() {
		mode = EDIT_MODE;

		btnGoBefore.setVisibility(View.INVISIBLE);
		btnCancel.setVisibility(View.VISIBLE);

		String strDone = "Done";
		btnEdit.setText(strDone);

		buttons.setVisibility(View.INVISIBLE);

		profileName.setVisibility(View.GONE);
		profileMessage.setVisibility(View.GONE);
		editName.setVisibility(View.VISIBLE);
		editMessage.setVisibility(View.VISIBLE);
	}

	private void onClickLogout() {
		UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
			@Override
			public void onCompleteLogout() {
				Log.d(TAG, "onCompleteLogout");
				redirectLoginActivity();
			}
		});
	}

	private void onClickUnlink() {
		final String appendMessage = getString(R.string.com_kakao_confirm_unlink);
		new AlertDialog.Builder(SettingsActivity.this)
				.setMessage(appendMessage)
				.setPositiveButton(getString(R.string.com_kakao_ok_button),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
									@Override
									public void onFailure(ErrorResult errorResult) {
										Log.e(TAG, errorResult.toString());
									}

									@Override
									public void onSessionClosed(ErrorResult errorResult) {
										Log.d(TAG, "onSessionClosed");
										redirectLoginActivity();
									}

									@Override
									public void onNotSignedUp() {
										Log.d(TAG, "onNotSignedUp");
										redirectLoginActivity();
									}

									@Override
									public void onSuccess(Long userId) {
										Log.d(TAG, "onSuccess");
										redirectLoginActivity();
									}
								});
								dialog.dismiss();
							}
						})
				.setNegativeButton(getString(R.string.com_kakao_cancel_button),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						}).show();
	}
}
