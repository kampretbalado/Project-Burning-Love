package com.burninglove.dma.burninglove;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button storyModeButton;
    private Button freeModeButton;
    private Button statisticsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storyModeButton = (Button) findViewById(R.id.story_mode_button);
        freeModeButton = (Button) findViewById(R.id.free_mode_button);
        statisticsButton = (Button) findViewById(R.id.statistics_button);

        storyModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TabbedActivity.class);
                startActivity(intent);
            }
        });

        freeModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseExercise.class);
                startActivity(intent);
            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Statistics.class);
                startActivity(intent);
            }
        });

    }
}
