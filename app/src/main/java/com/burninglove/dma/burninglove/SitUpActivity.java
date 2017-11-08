package com.burninglove.dma.burninglove;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SitUpActivity extends AppCompatActivity implements SensorEventListener{

    private TextView tv_counter;
    private TextView situplimit;
    private Button b_stop;

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;

    private Vibrator mVibrator;

    private int mCount;

    private boolean mSit = false;
    private boolean flag;
    int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_up);

        tv_counter = (TextView) findViewById(R.id.situpcounter);
        situplimit = (TextView) findViewById(R.id.situplimit);
        b_stop = (Button) findViewById(R.id.stopbutton);

        limit = getIntent().getIntExtra("limit", 5);
        situplimit.setText("/" + limit + " ");

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        b_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done();
            }
        });
    }

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
            tv_counter.setText("" + mCount);
            mVibrator.vibrate(250);
        }
        else if(xAcc < -0.5 && yAcc < 1){
            mSit = false;
        }


        if (mCount > 0 && situplimit.getVisibility() == View.INVISIBLE)
            situplimit.setVisibility(View.VISIBLE);

        if (mCount > 0 && b_stop.getVisibility() == View.INVISIBLE)
            b_stop.setVisibility(View.VISIBLE);

        if (mCount == limit){
            onPause();
            b_stop.setText("done");
            b_stop.setBackgroundColor(Color.GREEN);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
