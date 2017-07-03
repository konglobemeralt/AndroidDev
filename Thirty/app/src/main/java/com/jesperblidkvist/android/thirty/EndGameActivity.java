package com.jesperblidkvist.android.thirty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jesperblidkvist.android.thirty.model.ThirtyGame;

import java.util.ArrayList;
import java.util.List;

public class EndGameActivity extends AppCompatActivity {

    private TextView mTotalPoints;
    private TextView mChoicesMade;
    private Button mPlayAgainButton;
    private List<String> choices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        int totalScore = intent.getIntExtra(ThirtyActivity.EXTRA_SCORE, 0);

        choices = intent.getStringArrayListExtra(ThirtyActivity.CHOICE_LIST);

        mTotalPoints = (TextView) findViewById(R.id.totalPointsStringEndGame);
        mTotalPoints.setText("Total Score: " + Integer.toString(totalScore));


        mChoicesMade = (TextView) findViewById(R.id.choicesStringEndGame);
        mChoicesMade.setText("Total Score: " + choices.toString());

        mPlayAgainButton = (Button) findViewById(R.id.playAgainButton);
        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitActivity();
            }
        });


    }

    private void exitActivity(){
        Intent intent = new Intent(this, ThirtyActivity.class);
        startActivity(intent);
        finish();
    }
}
