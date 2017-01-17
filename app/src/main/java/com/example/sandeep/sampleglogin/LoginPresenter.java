package com.example.sandeep.sampleglogin;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;

/**
 * Created by sandeep on 1/12/2017.
 */

public interface LoginPresenter {
    void initilizeGoogleSdk();
    void attachView();
    void detachView();
    void handleSignInResult(GoogleSignInResult result);
    Intent getIntent();
    void logout();
}
