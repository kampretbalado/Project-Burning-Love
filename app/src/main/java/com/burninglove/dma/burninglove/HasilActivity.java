package com.burninglove.dma.burninglove;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HasilActivity extends AppCompatActivity {

    private TextView tv_hasil;
    private TextView tv_totalCal;
    private Button btn_back;

    private double burned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        tv_hasil = (TextView) findViewById(R.id.tv_hasil);
        tv_totalCal = (TextView) findViewById(R.id.tv_totalCal);
        btn_back = (Button) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initHasil();
        initSharedPreferences();
        initTotal();
    }

    private void initHasil() {
        String type = getIntent().getStringExtra("type");
        int counter = getIntent().getIntExtra("counter", 0);

        if (type.equals("pushup")) {
            burned = counter * 0.3;
        } else if (type.equals("situp")) {
            burned = counter * 0.2;
        }

        tv_hasil.setText("" + burned + " Cal");
    }

    private void initSharedPreferences() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        String cal = pref.getString("Calorie", "0");
        double dCal = Double.parseDouble(cal);
        cal = dCal + burned + "";

        editor.putString("Calorie", "" + cal);
        editor.apply();
    }

    private void initTotal() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String cal = pref.getString("Calorie", "0");
        double dCal = Double.parseDouble(cal);
        tv_totalCal.setText(String.format( "%.1f", dCal ) + " Cal");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HasilActivity.this, ChooseExercise.class);
        startActivity(intent);
    }
}
