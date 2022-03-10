package com.edu.unipiloto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity<pubic> extends AppCompatActivity {

    private Chronometer cronometro;
    private long pauseOffset;
    private boolean running;
    //private TextView mTvTime;
    private EditText mEtLaps;
    private Button mBtnLap;
    private ScrollView mSvLaps;

    private int mLaps = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cronometro = findViewById(R.id.chronometer);

        //mTvTime = findViewById(R.id.tv_time);

        mEtLaps = (EditText) findViewById(R.id.et_laps);

        mBtnLap = (Button) findViewById(R.id.btn_lap);

        mSvLaps = (ScrollView) findViewById(R.id.sv_laps);

        mEtLaps.setEnabled(false);

        mBtnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cronometro == null){
                    return;
                }

                mEtLaps.append("LAP " + String.valueOf(mLaps) + " " +String.valueOf(cronometro.getText()) + "\n");

                mSvLaps.post(new Runnable() {
                    @Override
                    public void run() {
                        mSvLaps.smoothScrollTo(0, mEtLaps.getBottom());
                    }
                });
            }
        });
    }

    public void startChronometer(View v) {
        if (!running){
            cronometro.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            cronometro.start();
            running=true;

            mLaps =1;
            mEtLaps.setText("");
        }
    }

    public void pauseChronometer(View v) {
        if (running){
            cronometro.stop();
            pauseOffset = SystemClock.elapsedRealtime() - cronometro.getBase();
            running=false;
        }
    }

    public void resetChronometer(View v) {
        cronometro.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

}