package com.example.saturday2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static java.lang.System.currentTimeMillis;

public class StopWatchActivity extends AppCompatActivity {

    private TextView mTimeTextView;

    private long mStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        mTimeTextView = (TextView) findViewById(R.id.time_text);
    }

    public void start(View view) {
        mStartTime = currentTimeMillis();

        // UI 스레드
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long delta = System.currentTimeMillis() - mStartTime;
                mTimeTextView.setText(String.valueOf(delta / 1000));

                mTimeTextView.postDelayed(this, 1000);
            }
        };
        mTimeTextView.postDelayed(runnable, 1000);
    }

    public void stop(View view) {

    }
}
