package com.mungziapp.traveltogether.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.SessionCallback;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnKakaoLogin = findViewById(R.id.btn_kakao_login);
        btnKakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session session = Session.getCurrentSession();

                session.addCallback(new SessionCallback());

                session.open(AuthType.KAKAO_TALK, LoginActivity.this);
            }
        });
    }
}
