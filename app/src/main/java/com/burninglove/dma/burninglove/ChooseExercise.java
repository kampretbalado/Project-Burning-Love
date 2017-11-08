package com.burninglove.dma.burninglove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ChooseExercise extends AppCompatActivity {

    private Button pushUpButton;
    private Button sitUpButton;
    private Button runButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pushUpButton = (Button) findViewById(R.id.push_up_button);
        sitUpButton = (Button) findViewById(R.id.sit_up_button);
        runButton = (Button) findViewById(R.id.run_button);

        pushUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseExercise.this, PushUpActivity.class);
                startActivity(intent);
            }
        });

        sitUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseExercise.this, SitUpActivity.class);
                startActivity(intent);
            }
        });

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseExercise.this, RunActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
