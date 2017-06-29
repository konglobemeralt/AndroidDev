package com.jesperblidkvist.android.thirty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jesperblidkvist.android.thirty.model.ThirtyGame;

public class EndGameActivity extends AppCompatActivity {

    private TextView mTotalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        int totalScore = intent.getIntExtra(ThirtyActivity.EXTRA_SCORE, 0);

       mTotalPoints = (TextView) findViewById(R.id.totalPointsStringEndGame);
       mTotalPoints.setText("Total Score: " + Integer.toString(totalScore));


    }
}
