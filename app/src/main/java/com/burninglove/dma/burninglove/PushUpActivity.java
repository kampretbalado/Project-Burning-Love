package com.burninglove.dma.burninglove;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PushUpActivity extends AppCompatActivity implements SensorEventListener {

    private static final float SENSOR_SENSITIVITY = 9;

    private TextView tv_counter;
    private TextView pushuplimit;
    private Button b_stop;

    private SensorManager mSensorManager;
    private Sensor mProximity;
    int counter = 0;
    int limit = 10;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);

        tv_counter = (TextView) findViewById(R.id.pushupcounter);
        pushuplimit = (TextView) findViewById(R.id.pushuplimit);
        b_stop = (Button) findViewById(R.id.stopbutton);

        pushuplimit.setText("/" + limit + " ");

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        b_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done();
            }
        });
    }

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
                counter = counter + 1;
                tv_counter.setText("" + counter);
                flag = true;
            } else if (flag && (distance < -SENSOR_SENSITIVITY || distance > SENSOR_SENSITIVITY)){
                flag = false;
            }

            if (flag && pushuplimit.getVisibility() == View.INVISIBLE)
                pushuplimit.setVisibility(View.VISIBLE);

            if (flag && b_stop.getVisibility() == View.INVISIBLE)
                b_stop.setVisibility(View.VISIBLE);

            if (counter >= limit)
                done();
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
