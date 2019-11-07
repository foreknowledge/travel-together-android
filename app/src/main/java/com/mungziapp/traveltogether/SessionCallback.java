package com.mungziapp.traveltogether;

import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

public class SessionCallback implements ISessionCallback {
    private final String TAG = "SessionCallback :: ";

    // 로그인에 성공한 상태
    @Override
    public void onSessionOpened() {
        requestMe();
    }

    // 로그인에 실패한 상태
    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Log.e(TAG, "onSessionOpenFailed : " + exception.getMessage());
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
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

                long id = result.getId();
                Log.d(TAG, "id = " + id);
            }
        });
    }

}
