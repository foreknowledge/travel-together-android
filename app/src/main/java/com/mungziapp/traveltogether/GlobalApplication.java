package com.mungziapp.traveltogether;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application {
    private static GlobalApplication instance;

    public static final GlobalApplication getGlobalApplicationContext() {
        if (instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    /** 카카오 로그인 어댑터
     *
     * 로그인을 위해 Session을 생성하기 위해 필요한 옵션을 얻기위한 abstract class.
     * 기본 설정은 KakaoAdapter에 정의되어있으며, 설정 변경이 필요한 경우 상속해서 사용할 수 있다.
     * */

    private static class KakaoSDKAdapter extends KakaoAdapter {
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[]{AuthType.KAKAO_TALK, AuthType.KAKAO_ACCOUNT};
                }

                @Override public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override public boolean isSecureMode() {
                    return false;
                }

                @Nullable
                @Override public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override public Context getApplicationContext() {
                    return GlobalApplication.getGlobalApplicationContext();
                }
            };
        }
    }

    @Override public void onCreate() {
        super.onCreate();
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }
}
