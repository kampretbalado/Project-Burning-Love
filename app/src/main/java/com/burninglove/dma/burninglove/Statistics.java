package com.burninglove.dma.burninglove;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Statistics extends AppCompatActivity {

    private TextView tv_calorie_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        tv_calorie_value = (TextView) findViewById(R.id.tv_calorie_value);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

//        pref.edit().clear().commit();

        String cal = pref.getString("Calorie", "0");
        double dCal = Double.parseDouble(cal);
        tv_calorie_value.setText( String.format( "%.1f", dCal ) );
    }
}
