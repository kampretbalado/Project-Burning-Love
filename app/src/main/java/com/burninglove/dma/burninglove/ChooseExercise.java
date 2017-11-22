package com.burninglove.dma.burninglove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.burninglove.dma.burninglove.util.ImageUtility;

public class ChooseExercise extends AppCompatActivity {

    private Button pushUpButton;
    private Button sitUpButton;
    private Button runButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView background = (ImageView) findViewById(R.id.iv_background);
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

        background.setImageBitmap(ImageUtility.decodeSampledBitmapFromResource(getResources(), R.drawable.cat_sporty, 640, 480));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
