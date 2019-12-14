package com.mungziapp.traveltogether.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;
import com.mungziapp.traveltogether.app.ApolloConnector;
import com.mungziapp.traveltogether.app.FirstMutation;
import com.mungziapp.traveltogether.R;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button btnLogin = findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				redirectMainActivity();
			}
		});

		Button btnKakaoLogin = findViewById(R.id.btn_kakao_login);
		btnKakaoLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Session session = Session.getCurrentSession();
				session.addCallback(new SessionCallback(getApplicationContext()));
				session.open(AuthType.KAKAO_TALK, LoginActivity.this);
			}
		});

		Button btnForgotPwd = findViewById(R.id.btn_forgot_pwd);
		btnForgotPwd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		Button btnGoSignUp = findViewById(R.id.btn_go_sign_up);
		btnGoSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
			return;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public class SessionCallback implements ISessionCallback {
		private static final String TAG = "SessionCallback :: ";
		private Context mContext;

		SessionCallback(Context mContext) {
			this.mContext = mContext;
		}

		// 로그인에 성공한 상태
		@Override
		public void onSessionOpened() {
			requestMe();
			Log.d(TAG, "onSessionOpened");
		}

		// 로그인에 실패한 상태
		@Override
		public void onSessionOpenFailed(KakaoException exception) {
			Log.e(TAG, "onSessionOpenFailed : " + exception.getMessage());
		}

		/**
		 * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
		 */
		private void requestMe() {
			UserManagement.getInstance().me(new MeV2ResponseCallback() {
				@Override
				public void onFailure(ErrorResult errorResult) {
					Log.e(TAG, "onFailure : " + errorResult.getErrorMessage());
				}

				// 세션 오픈 실패. 세션이 삭제된 경우,
				@Override
				public void onSessionClosed(ErrorResult errorResult) {
					Log.e(TAG, "onSessionClosed : " + errorResult.getErrorMessage());
				}

				// 사용자정보 요청에 성공한 경우,
				@Override
				public void onSuccess(MeV2Response result) {
					Log.d(TAG, "onSuccess");

					if (result.hasSignedUp() == OptionalBoolean.FALSE) {
						Log.e(TAG, "result has not signed up.");
						Toast.makeText(mContext, "카카오톡에 가입되지 않은 유저입니다.", Toast.LENGTH_SHORT).show();
					} else {
						long id = result.getId();
						Log.d(TAG, "id = " + id);

						requestAccessTokenInfo();
						redirectMainActivity();
						Toast.makeText(mContext, "로그인 성공~!", Toast.LENGTH_SHORT).show();
					}
				}
			});
		}

		// 로그인을 통해 얻은 사용자 토큰의 정보를 얻는 기능
		private void requestAccessTokenInfo() {
			AuthService.getInstance().requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {

				// 세션이 닫혔을때 불리는 callback
				@Override
				public void onSessionClosed(ErrorResult errorResult) {
					Log.d(TAG, "onSessionClosed : " + errorResult.getErrorMessage());
				}

				// 세션 오픈은 성공했으나 사용자 정보 요청 결과 사용자 가입이 안된 상태로 자동 가입 앱이 아닌 경우에만 호출
				@Override
				public void onNotSignedUp() {
					Log.d(TAG, "onNotSignedUp.");
				}

				// API 요청이 실패했을 경우
				@Override
				public void onFailure(ErrorResult errorResult) {
					Log.e(TAG, "failed to get access token info. msg=" + errorResult);
				}

				// request에 대한 result
				@Override
				public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {
					AccessToken accessToken = AccessToken.Factory.getInstance();
					final String token = accessToken.getAccessToken();
					Log.d(TAG, "Access token is " + token);

					long userId = accessTokenInfoResponse.getUserId();
					Log.d(TAG, "this access token is for userId=" + userId);

					// 서버로 access token & user id 전송
					getOauthLogin();
				}
			});
		}

		private void getOauthLogin() {
			ApolloConnector.setupApollo().mutate(
					FirstMutation
							.builder()
							.build())
					.enqueue(new ApolloCall.Callback<FirstMutation.Data>() {
						@Override
						public void onResponse(@NotNull Response<FirstMutation.Data> response) {
							Log.d(TAG, "Response: " + response.data());
						}

						@Override
						public void onFailure(@NotNull ApolloException e) {
							Log.d(TAG, "Exception " + e.getMessage(), e);
						}
					});
		}

	}
}
