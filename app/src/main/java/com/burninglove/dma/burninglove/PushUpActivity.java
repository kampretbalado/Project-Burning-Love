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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PushUpActivity extends AppCompatActivity implements SensorEventListener {

    private static final float SENSOR_SENSITIVITY = 9;

    private TextView tvCounter;
    private TextView tvPushUpLimit;
    private TextView tvTime;
    private Button b_stop;
    private CardView cv;

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private boolean flag;
    int counter = 0;
    int limit = 0;

    private long milisecondTime, startTime, timeBuff, updateTime = 0L;
    private int hours, seconds, miliSeconds, minutes;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);

        tvCounter = (TextView) findViewById(R.id.push_up_counter);
        tvPushUpLimit = (TextView) findViewById(R.id.push_up_limit);
        tvTime = (TextView) findViewById(R.id.tv_time_count);
        b_stop = (Button) findViewById(R.id.stop_button);
        cv = (CardView) findViewById(R.id.timer);

        limit = getIntent().getIntExtra("limit", 5);
        tvPushUpLimit.setText("/" + limit + " ");

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

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
        Intent intent = new Intent(PushUpActivity.this, HasilActivity.class);
        intent.putExtra("type", "pushup");
        intent.putExtra("counter", counter);
        startActivity(intent);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float distance = event.values[0];
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (!flag && distance >= -SENSOR_SENSITIVITY && distance <= SENSOR_SENSITIVITY) {
                flag = true;
            } else if (flag) {
                counter = counter + 1;
                tvCounter.setText("" + counter);
                flag = false;
            }

            if (!flag && tvPushUpLimit.getVisibility() == View.INVISIBLE)
                tvPushUpLimit.setVisibility(View.VISIBLE);

            if (!flag && b_stop.getVisibility() == View.INVISIBLE) {
                b_stop.setVisibility(View.VISIBLE);
                cv.setVisibility(View.VISIBLE);
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
            }

            if (counter == limit) {
                onPause();
                b_stop.setText("done");
                b_stop.setBackgroundColor(Color.GREEN);
                handler.removeCallbacks(runnable);
            }
        }
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
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
