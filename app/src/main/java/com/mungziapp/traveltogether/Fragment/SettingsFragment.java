package com.mungziapp.traveltogether.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.mungziapp.traveltogether.Interface.ActivityCallback;
import com.mungziapp.traveltogether.R;

public class SettingsFragment extends Fragment {
    private ActivityCallback callback;
    private final String TAG = "SettingsFragment :: ";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof ActivityCallback)
            callback = (ActivityCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);

        Button btnLogout = rootView.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogout();
            }
        });

        Button btnRemoveAccount = rootView.findViewById(R.id.btn_remove_account);
        btnRemoveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUnlink();
            }
        });

        return rootView;
    }

    private void onClickLogout() {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                Log.d(TAG, "onCompleteLogout");
                callback.redirectLoginActivityAndFinish();
            }
        });
    }

    private void onClickUnlink() {
        final String appendMessage = getString(R.string.com_kakao_confirm_unlink);
        new AlertDialog.Builder(getContext())
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
                                        callback.redirectLoginActivityAndFinish();
                                    }

                                    @Override
                                    public void onNotSignedUp() {
                                        Log.d(TAG, "onNotSignedUp");
                                        callback.redirectLoginActivityAndFinish();
                                    }

                                    @Override
                                    public void onSuccess(Long userId) {
                                        Log.d(TAG, "onSuccess");
                                        callback.redirectLoginActivityAndFinish();
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
