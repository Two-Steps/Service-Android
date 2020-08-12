package com.poly.lab2nc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

// class tự viết để khai báo Broadcast Receiver trong Manifests
// ở đây lấy sđt gọi đến
// dùng CALL_LOG và READ_PHONE_STATE trong manifests
public class BroadcastSub1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String number = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Toast.makeText(context, "" + number, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
