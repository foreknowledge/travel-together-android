package com.mungziapp.traveltogether.adapter;

import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.mungziapp.traveltogether.app.GlobalApplication;

/**
 * @author leoshin on 15. 9. 15.
 */
public class KakaoSDKAdapter extends KakaoAdapter {
	/**
	 * Session Config에 대해서는 default값들이 존재한다.
	 * 필요한 상황에서만 override해서 사용하면 됨.
	 *
	 * @return Session의 설정값.
	 */
	@Override
	public ISessionConfig getSessionConfig() {
		return new ISessionConfig() {
			// 로그인 시에 인증 타입을 지정
			@Override
			public AuthType[] getAuthTypes() {
				return new AuthType[]{AuthType.KAKAO_TALK, AuthType.KAKAO_ACCOUNT};
			}

			// 로그인 시 토큰을 저장할 때의 암호화 여부를 지정
			@Override
			public boolean isSecureMode() {
				return false;
			}

			// 로그인 웹뷰에서 pause와 resume시에 타이머를 설정하여, CPU의 소모를 절약 할 지의 여부를 지정
			// true로 지정할 경우, 로그인 웹뷰의 onPuase()와 onResume()에 타이머를 설정해야 함
			@Override
			public boolean isUsingWebviewTimer() {
				return false;
			}

			// 일반 사용자가 아닌 Kakao와 제휴 된 앱에서 사용되는 값
			// 값을 지정하지 않을 경우, ApprovalType.INDIVIDUAL 값으로 사용됨
			@Override
			public ApprovalType getApprovalType() {
				return ApprovalType.INDIVIDUAL;
			}

			// 로그인 웹뷰에서 email 입력 폼의 데이터를 저장할 지 여부를 지정
			@Override
			public boolean isSaveFormData() {
				return true;
			}
		};
	}

	@Override
	public IApplicationConfig getApplicationConfig() {
		return new IApplicationConfig() {
			@Override
			public Context getApplicationContext() {
				return GlobalApplication.getGlobalApplicationContext();
			}
		};
	}
}
