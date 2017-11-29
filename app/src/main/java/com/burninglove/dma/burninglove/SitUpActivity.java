package com.burninglove.dma.burninglove;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SitUpActivity extends AppCompatActivity implements SensorEventListener{

    private TextView tvCounter;
    private TextView tvSitUpLimit;
    private TextView tvTime;
    private Button b_stop;
    private CardView cv;

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;

    private Vibrator mVibrator;

    private int mCount;

    private boolean mSit = false;
    private boolean flag;
    int limit = 10;

    private long milisecondTime, startTime, timeBuff, updateTime = 0L;
    private int hours, seconds, miliSeconds, minutes;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_up);

        tvCounter = (TextView) findViewById(R.id.sit_up_counter);
        tvSitUpLimit = (TextView) findViewById(R.id.sit_up_limit);
        tvTime = (TextView) findViewById(R.id.tv_time_count);
        b_stop = (Button) findViewById(R.id.stop_button);
        cv = (CardView) findViewById(R.id.timer);

        limit = getIntent().getIntExtra("limit", 5);
        tvSitUpLimit.setText("/" + limit + " ");

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        b_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done();
            }
        });

        handler = new Handler();
    }

    protected Runnable runnable = new Runnable() {
        public void run() {

            milisecondTime = SystemClock.uptimeMillis() - startTime;

            updateTime = timeBuff + milisecondTime;


            seconds = (int) (updateTime / 1000);
            minutes = seconds / 60;
            hours = minutes / 60;
            minutes = minutes % 60;
            seconds = seconds % 60;
            miliSeconds = (int) (updateTime % 1000);

            tvTime.setText(hours + ":" + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds) + ":"
                    + String.format("%03d", miliSeconds));

            handler.postDelayed(this, 0);
        }
    };

    protected void done() {
        Intent intent = new Intent(SitUpActivity.this, HasilActivity.class);
        intent.putExtra("type", "situp");
        intent.putExtra("counter", mCount);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float xAcc = sensorEvent.values[0];
        float yAcc = sensorEvent.values[1];

        System.out.printf("xAcc: %.1f\n", xAcc);
        System.out.printf("yAcc: %.1f\n", yAcc);
        if(!mSit && xAcc > 1 && yAcc > 8){
            mCount++;
            mSit = true;
            tvCounter.setText("" + mCount);
            mVibrator.vibrate(250);
        }
        else if(xAcc < -0.5 && yAcc < 1){
            mSit = false;
        }


        if (mCount > 0 && tvSitUpLimit.getVisibility() == View.INVISIBLE)
            tvSitUpLimit.setVisibility(View.VISIBLE);

        if (mCount > 0 && b_stop.getVisibility() == View.INVISIBLE) {
            b_stop.setVisibility(View.VISIBLE);
            cv.setVisibility(View.VISIBLE);
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
        }

        if (mCount == limit){
            onPause();
            b_stop.setText("done");
            b_stop.setBackgroundColor(Color.GREEN);
            handler.removeCallbacks(runnable);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    @Override
    public void onBackPressed() {
        if (b_stop.getVisibility() == View.INVISIBLE) {
            super.onBackPressed();
        } else {
            done();
        }
    }
}
