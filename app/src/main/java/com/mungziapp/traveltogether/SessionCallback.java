package com.mungziapp.traveltogether;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;
import com.mungziapp.traveltogether.Activity.LoginActivity;
import com.mungziapp.traveltogether.Activity.MainActivity;

public class SessionCallback implements ISessionCallback {
    private final String TAG = "SessionCallback :: ";
    private Context mContext;

    public SessionCallback(Context mContext) { this.mContext = mContext; }

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
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(intent);

                    Toast.makeText(mContext, "카카오톡에 가입되지 않은 유저입니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    long id = result.getId();
                    Log.d(TAG, "id = " + id);

                    requestAccessTokenInfo();

                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(intent);

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
                String token = accessToken.getAccessToken();
                Log.d(TAG, "Access token is " + token);

                long userId = accessTokenInfoResponse.getUserId();
                Log.d(TAG, "this access token is for userId=" + userId);

                // 서버로 access token & user id 전송
            }
        });
    }

}
