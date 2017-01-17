package com.example.sandeep.sampleglogin;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;

/**
 * Created by sandeep on 1/12/2017.
 */

public class LoginPresenterImpl implements LoginPresenter, GoogleApiClient.OnConnectionFailedListener {

    private LoginView view;
    private GoogleApiClient mGoogleApiClient;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
    }

    @Override
    public void initilizeGoogleSdk() {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            // Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            view.showProgressDialog(true);
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    view.showProgressDialog(false);
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    public void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            UserDetail userDetail = new UserDetail();
            userDetail.setPersonName(acct.getDisplayName());
            userDetail.setFirstName(acct.getGivenName());
            userDetail.setLastName(acct.getFamilyName());
            userDetail.setEmail(acct.getEmail());
            userDetail.setId(acct.getId());
            userDetail.setIdToken(acct.getIdToken());
            userDetail.setImage(acct.getPhotoUrl());
            userDetail.setAuth(acct.getServerAuthCode());
        } else {
            view.showToast(result.getStatus().zzuU());
        }
    }

    @Override
    public Intent getIntent() {
        return Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    }

    @Override
    public void logout() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                        }
                    });
        }
    }

    @Override
    public void attachView() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken("914577052995-i69bjg2njb0mgd07uocjffnsk43averj.apps.googleusercontent.com")
                .requestServerAuthCode("914577052995-i69bjg2njb0mgd07uocjffnsk43averj.apps.googleusercontent.com")
                .requestScopes(new Scope(Scopes.PLUS_ME))
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(view.getViewContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void detachView() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
