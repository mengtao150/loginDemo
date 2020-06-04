package com.example.loginandsqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
private  final  int SPLASH_DISPLAY=2000;//延迟六秒
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent=new Intent(SplashActivity.this,LoginMain.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        },SPLASH_DISPLAY);
    }
}
