package com.eros.favmovies.view.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.eros.favmovies.view.constants.AppConstants;

public class NetworkStatus {

    private static NetworkStatus mInstance;

    public static synchronized NetworkStatus getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkStatus();
        }
        return mInstance;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Device is online
            return true;
        } else {
            // Device is not online
            Toast.makeText(context, AppConstants.NETWORK_ERROR,Toast.LENGTH_LONG).show();
            return false;
        }
    }}
