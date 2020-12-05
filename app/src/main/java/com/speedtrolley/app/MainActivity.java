package com.speedtrolley.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 4000;
    public ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.bringToFront();
                Intent in = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(in);
                progressBar.setVisibility(View.GONE);
                finish();
            }
        }, SPLASH_TIME);
    }
}