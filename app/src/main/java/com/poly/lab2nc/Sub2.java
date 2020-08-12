package com.poly.lab2nc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sub2 extends AppCompatActivity {
    TextView tv_data;
    Button btn_send;
    EditText edt_input;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        tv_data = findViewById(R.id.tv_data);
        btn_send = findViewById(R.id.btn_send);
        edt_input = findViewById(R.id.edt_input);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String data = intent.getStringExtra("data");
                tv_data.setText(data);
            }
        };
        IntentFilter intentFilter = new IntentFilter("sendtodata");
        // tự khai báo Broadcast Receiver
        registerReceiver(broadcastReceiver, intentFilter);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("sendtodata");
                intent.putExtra("data", edt_input.getText().toString());
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
