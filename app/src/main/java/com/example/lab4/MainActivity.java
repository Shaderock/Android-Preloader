package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable preloaderAnimation;
    ImageView preloader;
    SomeLoadTask waiter;
    Button start, stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preloader = findViewById(R.id.iv_preloader);
        preloader.setBackgroundResource(R.drawable.preloader);
        preloaderAnimation = (AnimationDrawable) preloader.getBackground();

        start = findViewById(R.id.btnStart);
        stop = findViewById(R.id.btnStop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preloaderAnimation.start();
                waiter = new SomeLoadTask();
                waiter.execute();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preloaderAnimation.stop();
                waiter.cancel(true);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private class SomeLoadTask extends AsyncTask <Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            SystemClock.sleep(7000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            preloaderAnimation.stop();
        }
    }
}
