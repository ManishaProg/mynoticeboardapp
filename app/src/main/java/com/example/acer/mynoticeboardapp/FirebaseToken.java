package com.example.acer.mynoticeboardapp;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseToken extends FirebaseInstanceIdService {
    public static final String TOKEN_BROADCAST = "mytoken";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("mytoken", "Refreshed token: " + refreshedToken);
        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
        //saving the token on shared preferences
        SharedprefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }

}
