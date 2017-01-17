package com.example.sandeep.sampleglogin;

import android.content.Context;

/**
 * Created by sandeep on 1/12/2017.
 */

public interface LoginView {
    public static final int RC_SIGN_IN = 9001;

    Context getViewContext();

    void showProgressDialog(boolean show);
    void showToast(String msg);
}

