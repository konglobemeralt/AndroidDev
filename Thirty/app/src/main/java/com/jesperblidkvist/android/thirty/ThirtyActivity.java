package com.jesperblidkvist.android.thirty;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jesperblidkvist.android.thirty.model.ThirtyGame;

import java.util.ArrayList;
import java.util.List;

public class ThirtyActivity extends AppCompatActivity {

    private ThirtyGame game;
    private Button mRollDiceButton;
    private Button mEndTurnButton;

    private TextView mTotalPoints;
    private TextView mRoundPoints;
    private TextView mlowString;

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
                        game.endTurn();
                        updateDices();
                        updateStrings();

                //Log.d("ThirtyActivity", game.toString());
            }
        });


        initDices();
        game = new ThirtyGame();
        updateDices();

        mRoundPoints = (TextView) findViewById(R.id.roundPointsString);
        mTotalPoints = (TextView) findViewById(R.id.totalPointsString);
        mlowString = (TextView) findViewById(R.id.lowString);
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
}
