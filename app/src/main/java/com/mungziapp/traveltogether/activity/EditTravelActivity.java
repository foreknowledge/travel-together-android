package com.mungziapp.traveltogether.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.mungziapp.traveltogether.app.ConnectionStatus;
import com.mungziapp.traveltogether.app.GalleryImageSetter;
import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.TravelInfoSetter;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditTravelActivity extends AppCompatActivity {
	private static final String TAG = "EditTravelActivity ::";
	private static final int PICK_FROM_ALBUM = 101;
	private TravelInfoSetter infoSetter;

	private String travelId;

	private ImageView btnRePickCoverImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_travel);

		travelId = getIntent().getStringExtra("travel_id");

		infoSetter = new TravelInfoSetter(EditTravelActivity.this, TAG);
		setViewsAndActions();
	}

	private void setViewsAndActions() {
		// set views
		EditText editTitle = findViewById(R.id.edit_travel_title);
		EditText editSearch = findViewById(R.id.search_country);
		Button btnStartDate = findViewById(R.id.btn_pick_start_date);
		Button btnEndDate = findViewById(R.id.btn_pick_end_date);
		ChipGroup chipGroup = findViewById(R.id.chip_group);

		infoSetter.setViews(editTitle, editSearch, btnStartDate, btnEndDate, chipGroup);

		infoSetter.setTitleText();

		RecyclerView countrySearchRecycler = findViewById(R.id.country_search_recycler);
		Button btnClear = findViewById(R.id.btn_clear);
		FrameLayout btnClearOut = findViewById(R.id.btn_clear_out);
		infoSetter.setSearchBar(countrySearchRecycler, btnClear, btnClearOut);

		ScrollView scrollView = findViewById(R.id.scroll_view);
		infoSetter.setCountryList(countrySearchRecycler, scrollView);

		infoSetter.setDateButtons();

		setCoverImg();

		// set save and cancel buttons
		setSaveAndCancelButtons(editTitle);

		infoSetter.setDefaultValue(travelId);
	}

	private void setSaveAndCancelButtons(final EditText editTitle) {
		Button btnSave = findViewById(R.id.btn_save);
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (ConnectionStatus.getConnected()) {
					if (editTitle.getText().toString().equals(""))
						Snackbar.make(view, "제목은 필수 입력 사항입니다.", Snackbar.LENGTH_SHORT).show();
					else {
						requestToServer();
					}
				} else
					Snackbar.make(view, "네트워크가 연결되어 있지 않습니다.", Snackbar.LENGTH_SHORT).show();
			}
		});

		Button btnCancel = findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				infoSetter.clearFocus();
				new AlertDialog.Builder(EditTravelActivity.this)
						.setMessage(getString(R.string.cancel_message))
						.setPositiveButton(getString(R.string.btn_ok_text), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								finish();
							}
						})
						.setNegativeButton(getString(R.string.btn_cancel_text), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								dialogInterface.dismiss();
							}
						}).show();
			}
		});
	}

	private void requestToServer() {
		infoSetter.requestToServer(Request.Method.PATCH, RequestHelper.HOST + "/travel-rooms/" + travelId,
				new OnResponseListener.OnJsonObjectListener() {
					@Override
					public void onResponse(JSONObject jsonObject) {
						Toast.makeText(EditTravelActivity.this, "여행 정보가 변경되었습니다.", Toast.LENGTH_SHORT).show();
						finish();
					}

					@Override
					public Map<String, String> getHeaders() {
						Map<String, String> headers = new HashMap<>();
						headers.put("Authorization", TokenManager.getInstance().getAuthorization());

						return headers;
					}

					@Override
					public void onErrorResponse(VolleyError error) {
						RequestHelper.processError(error, TAG);
					}
				});
	}

	private void setCoverImg() {
		btnRePickCoverImg = findViewById(R.id.btn_re_pick_cover_img);
		btnRePickCoverImg.setOnClickListener(new View.OnClickListener() {
			String[] options = getResources().getStringArray(R.array.option_profile_img);

			@Override
			public void onClick(View view) {
				infoSetter.clearFocus();
				new AlertDialog.Builder(EditTravelActivity.this)
						.setTitle(getString(R.string.cover_img))
						.setItems(options, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								switch (i) {
									case 0:
										Toast.makeText(EditTravelActivity.this, "기본 이미지로 변경", Toast.LENGTH_SHORT).show();
										break;
									case 1:
										Intent intent = new Intent(Intent.ACTION_PICK);
										intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
										startActivityForResult(intent, PICK_FROM_ALBUM);
										break;
								}
							}
						}).show();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_FROM_ALBUM && data != null)
			new GalleryImageSetter().setImageInImgView(data, EditTravelActivity.this, btnRePickCoverImg);
	}
}
