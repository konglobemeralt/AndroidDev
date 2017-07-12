package com.jesperblidkvist.android.thirty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EndGameActivity extends AppCompatActivity {

    private TextView mTotalPoints;
    private TextView mChoicesMade;
    private Button mPlayAgainButton;
    private List<String> listChoices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        int totalScore = intent.getIntExtra(ThirtyActivity.EXTRA_SCORE, 0);
        listChoices = intent.getStringArrayListExtra(ThirtyActivity.CHOICE_LIST);

        initLayout(totalScore);

    }

    /**
     * Initiates the layout of end Game activity
     *
     */
    private void initLayout(int totalScore){
        mTotalPoints = (TextView) findViewById(R.id.totalPointsStringEndGame);
        mTotalPoints.setText("Total Score: " + Integer.toString(totalScore));

        mChoicesMade = (TextView) findViewById(R.id.choicesStringEndGame);
        mChoicesMade.setText(formatChoicesStrings());

        mPlayAgainButton = (Button) findViewById(R.id.playAgainButton);
        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitActivity();
            }
        });
    }

    /**
     * format choice strings before display
     */
    private String formatChoicesStrings(){
        String choices = "";
        for(int i = 0; i < listChoices.size(); i++){
            choices += "round: " + listChoices.get(i) + "\n";
        }
        return choices;
    }

    private void exitActivity(){
        Intent intent = new Intent(this, ThirtyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        exitActivity();
    }
}
