package com.burninglove.dma.burninglove;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.burninglove.dma.burninglove.R;
import com.burninglove.dma.burninglove.StepDetector;
import com.burninglove.dma.burninglove.StepListener;

public class RunActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView tvSteps;
    private TextView tvCalorie;
    private TextView tvDistance;
    private TextView tvTime;
    private TextView tvSpeed;

    private Button startButton;
    private Button stopButton;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private int numSteps;
    private final float AVG_STEP_DISTANCE = 70.0f; // in cm
    private final float AVG_MET = 11.0f; // in MET/hour

    private float bodyWeight = 55f; // in kg
    private float bodyHeight = 170f; // in cm
    private float age = 25f; // in year
    private boolean male = true; // true = male
    private float BMR;

    private long milisecondTime, startTime, timeBuff, updateTime = 0L ;
    private int hours, seconds, miliSeconds, minutes;
    private Handler handler;

    private float calorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);


        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        tvSteps = (TextView) findViewById(R.id.tv_steps_count);
        tvTime = (TextView) findViewById(R.id.tv_time_count);
        tvDistance = (TextView) findViewById(R.id.tv_distance_count);
        tvCalorie = (TextView) findViewById(R.id.tv_calorie_count);
        tvSpeed = (TextView) findViewById(R.id.tv_speed_count);
        //startButton = (Button) findViewById(R.id.btn_start);
        stopButton = (Button) findViewById(R.id.btn_stop);

        numSteps = 0;
        calorie = 0f;
        tvSteps.setText(numSteps + " steps");

        handler = new Handler();

        //startButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View arg0) {
        sensorManager.registerListener(RunActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);

                //startButton.setEnabled(false);
        //    }
        //});
        BMR = 0f;
        if (male) {
            BMR = 66.47f + (13.7f * bodyWeight) + (5f * bodyHeight) - (6.8f * age);
        } else {
            BMR = 65.1f + (9.6f * bodyWeight) + (1.8f * bodyHeight) - (4.7f * age);
        }

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sensorManager.unregisterListener(RunActivity.this);
                handler.removeCallbacks(runnable);
                done();
            }
        });
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
        Intent intent = new Intent(RunActivity.this, HasilActivity.class);
        intent.putExtra("type", "run");
        intent.putExtra("counter", numSteps);
        intent.putExtra("calorie", calorie);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        tvSteps.setText("" + numSteps);

        float distance = ((float) numSteps * AVG_STEP_DISTANCE / 100.f); // in m
        tvDistance.setText("" + distance + " m");

        float realtimeMinutes = updateTime / 1000f / 60f;
        float speed = 1f;
        if (updateTime > 0f) {
            speed = (distance / 1000f) / (realtimeMinutes/ 60f); // in km/h;
        }

        tvSpeed.setText(String.format("%.2f", speed) + " km/h");

        calorie = ( BMR * (speed / 24f) * (realtimeMinutes / 60) ) / ( 24f );
        tvCalorie.setText("" + String.format("%.2f", (calorie)) + " cal");

    }

}