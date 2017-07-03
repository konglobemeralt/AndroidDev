package com.jesperblidkvist.android.thirty;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jesperblidkvist.android.thirty.model.ThirtyGame;

import java.util.ArrayList;
import java.util.List;

public class ThirtyActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "com.example.ThirtyActivty.SCORE";
    public static final String CHOICE_LIST = "com.example.ThirtyActivty.CHOICE_LIST";
    private static final String GAME_STATE = "scom.example.ThirtyActivty.state_game" ;

    private ThirtyGame game;
    private Button mRollDiceButton;
    private Button mEndTurnButton;

    private TextView mTotalPoints;
    private TextView mRoundPoints;
    private TextView mlowString;

    private Spinner mScoringSpinner;

    /**
     * List of each visual representation of dices.
     */
    private List<ImageButton> diceButtons = new ArrayList<>();

    /**
     * List of all the diceButton ID to enable looping through
     */
    private static final int[] DICE_BUTTON_IDS = {
            R.id.diceButton1,
            R.id.diceButton2,
            R.id.diceButton3,
            R.id.diceButton4,
            R.id.diceButton5,
            R.id.diceButton6,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirty);

        Intent intent = getIntent();

        mRollDiceButton = (Button) findViewById(R.id.rollButton);
        mRollDiceButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                if(game.playPossible()){
                    game.playTurn();
                    updateDices();
                }
                else{
                   displayToast();
                }

                //Log.d("ThirtyActivity", game.toString());
            }
        });

        mEndTurnButton = (Button) findViewById(R.id.endTurnButton);
        mEndTurnButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                        if(game.isGameOver()){
                            endGame(v);
                            finish();
                        }
                        else{
                            game.endTurn();
                            updateDices();
                            updateStrings();
                        }
                //Log.d("ThirtyActivity", game.toString());
            }
        });


        mRoundPoints = (TextView) findViewById(R.id.roundPointsString);
        mTotalPoints = (TextView) findViewById(R.id.totalPointsString);
        mlowString = (TextView) findViewById(R.id.lowString);

        mScoringSpinner = (Spinner) findViewById(R.id.ScoringSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.scoring_methods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mScoringSpinner.setAdapter(adapter);

        initDices();
        game = new ThirtyGame();

        if(savedInstanceState != null){
            game = (ThirtyGame) savedInstanceState.getParcelable(GAME_STATE);
        }

        updateDices();
        updateStrings();
    }

    /**
     * Initiate the diceButtons
     */
    private void initDices(){

        int buttonNR = 0;
        for(int id : DICE_BUTTON_IDS) {
            final int index = buttonNR++;

            ImageButton diceButton = (ImageButton) findViewById(id);
            diceButton.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    game.saveDice(index);
                    updateDices();
                    updateStrings();
                }
            });

            diceButtons.add(diceButton);
        }

    }

    /**
     * Update the diceButtons with the current values
     */
    private void updateDices() {
        int index = 0;

        for (ImageButton button : diceButtons) {
            //build string for identifier
            String identifier = "dice_";
            if(game.getDiceAtIndex(index).isSelected()){
                identifier += "gray";
            }
            else if(game.getDiceAtIndex(index).isSaved()){
                identifier += "red";
            }
            else{
                identifier += "white";
            }

            //add value of dice
            identifier += game.getDiceValueAtIndex(index++);

            button.setImageResource(getResources().getIdentifier(identifier, "mipmap", getPackageName()));
        }

    }

    /**
     * helper function that displays a toast
     */
    private void displayToast(){
        Context context = getApplicationContext();
        CharSequence text = game.getGameStatus();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * updates all the strings in the view
     */
    private void updateStrings(){
        mlowString.setText("Low: " + Integer.toString(game.getCurrentLow()));
        updateScore();
    }

    /**
     * updates the score in view
     */
    private void updateScore(){
        mRoundPoints.setText("Current selection: " + Integer.toString(game.getRoundScore()));
        mTotalPoints.setText("Total Points: " + Integer.toString(game.getTotalScore()));
    }

    /**
     * Ends game, starting end game activity
     */
    public void endGame(View view) {
        Intent intent = new Intent(this, EndGameActivity.class);
        int totalScore = this.game.getTotalScore();
        intent.putExtra(EXTRA_SCORE, totalScore);
        intent.putStringArrayListExtra(CHOICE_LIST, (ArrayList<String>) this.game.getChoices());
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(GAME_STATE, this.game);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

}
