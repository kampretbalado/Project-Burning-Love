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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pushUpButton = (Button) findViewById(R.id.push_up_button);

        pushUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseExercise.this, PushUpActivity.class);
                startActivity(intent);
            }
        });

        sitUpButton = (Button) findViewById(R.id.sit_up_button);

        sitUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseExercise.this, SitUpActivity.class);
                startActivity(intent);
            }
        });
    }

}
