package com.porfirio.myshoppingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.porfirio.myshoppingmall.R;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // into the MainActivity after 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // run in the mainThread.
                // launch the MainActivity.
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                // close the current Activity.
                finish();
            }
        }, 2000);
    }
}
