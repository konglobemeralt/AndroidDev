package com.jesperblidkvist.android.thirty;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        game = new ThirtyGame();

        initView();

        if(savedInstanceState != null){
            game = savedInstanceState.getParcelable(GAME_STATE);
        }
        updateView();
    }

    /**
     * initiates elements in view
     */
    private void initView(){
        initDices();
        initRollDiceButton();
        initEndTurnButton();
        initTextElements();
    }

    /**
     * updates graphical elements
     */
    private void updateView(){
        updateSpinner();
        updateDices();
        updateStrings();
    }

    /**
     * Initiate the text Elements
     */
    private void initTextElements(){

        mRoundPoints = (TextView) findViewById(R.id.roundPointsString);
        mTotalPoints = (TextView) findViewById(R.id.totalPointsString);
        mScoringSpinner = (Spinner) findViewById(R.id.ScoringSpinner);

    }


    /**
     * Initiate the RollDiceButton
     */
    private void initRollDiceButton(){
        mRollDiceButton = (Button) findViewById(R.id.rollButton);
        mRollDiceButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int redoCount = game.getRedoCount();
                if(redoCount < 2){
                    game.playTurn();
                    updateDices();
                    if(redoCount == 1){
                       disableButton(mRollDiceButton);
                    }
                }
                else{
                    displayToast();
                }
            }

        });
    }

    /**
     * Disable a Button
     */
    private void disableButton(Button button){
        button.setEnabled(false);
        button.setBackgroundColor(Color.GRAY);
    }

    /**
     * Enable a Button
     */
    private void enableButton(Button button){
        button.setEnabled(true);
        button.setBackgroundColor(Color.parseColor("#00bfff"));
    }


    /**
     * Initiate the RollDiceButton
     */
    private void initEndTurnButton(){
        mEndTurnButton = (Button) findViewById(R.id.endTurnButton);
        mEndTurnButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                game.endTurn(mScoringSpinner.getSelectedItem().toString());
                if(game.isGameOver()){
                    endGame();
                }
                else{
                    updateView();
                    enableButton(mRollDiceButton);
                }
            }
        });
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
            String identifier = generateDiceString(index);
            identifier +=  game.getDiceValueAtIndex(index++);
            button.setImageResource(getResources().getIdentifier(identifier, "drawable", getPackageName()));
        }
    }

    /**
     * Generates a String to be used when setting the current imageResource on one of the buttons representing a dice
     * @param  index integer index for specific dice
     * @return A string formatted to find relevant drawable for specific dice.
     */
    private String generateDiceString(int index){
        String identifier = "drawable/";
        if(game.getDiceAtIndex(index).isSaved()){
            identifier += "grey";
        }
        else{
            identifier += "white";
        }
        return identifier;
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
        updateScore();
        updateButtons();
    }

    private void updateButtons(){
        mEndTurnButton.setText(getResources().getString(R.string.end_turn_nr_string) + " " + (game.getRoundCount()+ 1));
        if(game.getRoundCount() == 9){
            mEndTurnButton.setText(getResources().getString(R.string.last_turn_string));
        }
    }


    /**
     * updates the spinner
     */
    private void updateSpinner(){
        List<String> spinnerArray =  new ArrayList<>(game.getAvaliableScoringMethods());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mScoringSpinner.setAdapter(spinnerArrayAdapter);
        //game.getRoundScore(mScoringSpinner.getSelectedItem().toString());
    }

    /**
     * updates the score in view
     */
    private void updateScore(){
        mRoundPoints.setText("Current selection: " + Integer.toString(game.getRoundScore(mScoringSpinner.getSelectedItem().toString())));
        mTotalPoints.setText("Total Points: " + Integer.toString(game.getTotalScore()));
    }

    /**
     * Ends game, starting end game activity
     */
    private void endGame() {
        Intent intent = new Intent(this, EndGameActivity.class);
        int totalScore = this.game.getTotalScore();
        intent.putExtra(EXTRA_SCORE, totalScore);
        intent.putStringArrayListExtra(CHOICE_LIST, (ArrayList<String>) this.game.getChoices());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to stop Playing?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}
