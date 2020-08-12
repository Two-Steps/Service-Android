package com.poly.lab2nc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sub3 extends AppCompatActivity {
    EditText edt_km;
    Button btn_kt;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);
        edt_km = findViewById(R.id.edt_km);
        btn_kt = findViewById(R.id.kt);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String makm = intent.getStringExtra("makm");
//                          MEM537128 : Khuyến mãi 10%
//                          MEM537129 : Khuyến mãi 20%
//                          VIP537128 : Khuyến mãi 30%
//                          VIP537129 : Khuyến mãi 50%

                if (makm.isEmpty()) {
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                } else if (makm.length() != 9) {
                    Toast.makeText(context, "Mã khuyến mại có 9 ký tự, kiểm tra lại", Toast.LENGTH_SHORT).show();
                } else {
                    String madau = makm.substring(0, 3);
                    if (madau != null) {
                        if (madau.equals("MEM") || madau.equals("VIP")) {
                            switch (makm) {
                                case "MEM537128":
                                    Toast.makeText(context, "Khuyến mại 10%", Toast.LENGTH_SHORT).show();
                                    break;
                                case "MEM537129":
                                    Toast.makeText(context, "Khuyến mại 20%", Toast.LENGTH_SHORT).show();
                                    break;
                                case "VIP537128":
                                    Toast.makeText(context, "Khuyến mại 30%", Toast.LENGTH_SHORT).show();
                                    break;
                                case "VIP537129":
                                    Toast.makeText(context, "Khuyến mại 50%", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(context, "Không có mã khuyến mại này!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Mã bắt đầu là MEM hay VIP, hãy xem lại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("kiemtra");

        registerReceiver(broadcastReceiver, intentFilter);
        btn_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("kiemtra");
                intent.putExtra("makm", edt_km.getText().toString());
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
