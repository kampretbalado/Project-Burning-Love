package com.burninglove.dma.burninglove;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HasilActivity extends AppCompatActivity {

    private TextView tv_hasil;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        tv_hasil = (TextView) findViewById(R.id.tv_hasil);
        btn_back = (Button) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initHasil();
    }

    protected void initHasil() {
        String type = getIntent().getStringExtra("type");
        int counter = getIntent().getIntExtra("counter", 0);
        double burned = 0;

        if (type.equals("pushup")) {
            burned = counter * 0.3;
        } else if (type.equals("situp")) {
            burned = counter * 0.2;
        }

        tv_hasil.setText("" + burned + " Cal");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HasilActivity.this, ChooseExercise.class);
        startActivity(intent);
    }
}
