package com.burninglove.dma.burninglove;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class PushUpActivity extends AppCompatActivity implements SensorEventListener {

    private static final float SENSOR_SENSITIVITY = 9;
    TextView tv_counter;
    private SensorManager mSensorManager;
    private Sensor mProximity;
    int counter = 0;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);

        tv_counter = (TextView) findViewById(R.id.pushupcounter);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
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
                Log.d("Log", String.valueOf(flag));
            } else if (flag){
                flag = false;
                Log.d("Log", String.valueOf(flag));
                counter = counter + 1;
                tv_counter.setText("" + counter);
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
}
