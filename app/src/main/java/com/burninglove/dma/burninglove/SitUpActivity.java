package com.burninglove.dma.burninglove;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class SitUpActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;

    private Vibrator mVibrator;

    private int mCount;

    private boolean mSit = false;

    TextView tv_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_up_counter);

        tv_counter = (TextView) findViewById(R.id.situpcounter);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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
            tv_counter.setText("" + mCount);
            mVibrator.vibrate(250);
        }
        else if(xAcc < -0.5 && yAcc < 1){
            mSit = false;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
