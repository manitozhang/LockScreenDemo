package com.lock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCheckPermission = findViewById(R.id.btn_check_permission);
        btnCheckPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WindowPermissionCheck.checkPermission(MainActivity.this)){
                    Toast.makeText(MainActivity.this, "你已开启权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
