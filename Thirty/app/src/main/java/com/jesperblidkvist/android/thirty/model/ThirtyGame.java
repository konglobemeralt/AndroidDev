package com.jesperblidkvist.android.thirty.model;

import android.content.Context;
import android.widget.Toast;

/**
 *  This class represents a game of Thirty.
 *
 * @author  Jesper Blidkvist
 * @version 1.0
 * @since   2017-06-22.
 *
 * Created by Jesper on 2017-06-22.
 */

public class ThirtyGame {

    private Dice[] dices;
    int redoCount;
    int roundCount;

    int totalScore;
    int roundScore;

    String gameStatus;


    public ThirtyGame() {
        dices = new Dice[6];
        for (int i = 0; i < dices.length; ++i) {
            dices[i] = new Dice();
        }

        int roundScore = 0;
        int totalScore = roundScore;

        int redoCount = 0;
        int roundCount = 0;
    }

    /**
     * Sets all the dices in the game to 1.
     */
    private void resetDice(){
        for (int i = 0; i < dices.length; ++i) {
            dices[i].setValue(1);
            dices[i].setSelected(false);
            dices[i].setSaved(false);
        }
    }

    /**
     * Sets redo counter to 0.
     */
    private void resetRedoCounter(){
        redoCount = 0;
    }

    /**
     * Gets the score of the current round.
     */
    public int getRoundScore() {
        return roundScore;
    }

    /**
     * Gets the total score.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Returns all the dices in the game.
     */
    public Dice[] getAllDices() {
        return dices;
    }

    /**
     * Returns all the dices in the game.
     */
    public int[] getAllDiceValues() {
        int[] values = new int[6];
        for (int i = 0; i < dices.length; ++i) {
            values[i] = dices[i].getValue();
        }
        return values;
    }

    /**
     * Returns the dice at a specific index
     */
    public Dice getDiceAtIndex(int i){
        return dices[i];
    }

    /**
     * Returns the value of a dice at a specific index
     */
    public int getDiceValueAtIndex(int i){
        return dices[i].getValue();
    }

    /**
     * Roll all the dices that are not saved or selected
     */
    private void rollDices(){
        for (int i = 0; i < dices.length; ++i) {
            if(!dices[i].isSaved()){
                dices[i].roll();
            }
        }
    }

    private void increaseRoundCount(){
        if(redoCount !=2 ){
            redoCount++;
        }else
        {

        }
    }

    private void increaseTurnCount(){
        if(roundCount !=10 ){
            roundCount++;
        }else
        {

        }
    }

    /**
     * Plays a turn
     */
    public void playTurn(){
        rollDices();
        increaseRoundCount();
    }

    /**
     * Ends a turn
     */
    public void endTurn(){
        increaseTurnCount();
        resetRedoCounter();
        resetDice();

        totalScore = roundScore;
        roundScore = 0;
    }



    /**
     * Toggles selection on a dice
     */
    public void selectDice(int index){
        dices[index].toggleSelection();

    }

    /**
     * Toggles save on a dice
     */
    public void saveDice(int index){
        dices[index].toggleSaved();
        if(dices[index].isSaved()){
            roundScore += dices[index].getValue();
        }
        else{
            roundScore -= dices[index].getValue();
        }
    }

    /**
     * Checks if all dices are selected
     */
    public boolean playPossible() {
        int selectedCount = 0;
        for (int i = 0; i < dices.length; ++i) {
            if (dices[i].isSelected()) {
                selectedCount += 1;
            }
        }
        if(selectedCount == 6 && redoCount < 3){
            gameStatus = "Please deselect one or more dice...";
            return false;
        }
        else if(selectedCount != 6 && redoCount == 2){
            gameStatus = "Only two redos per turn!";
            return false;
        }
        else{
            gameStatus = "Playing as normal";
            return true;
        }
    }

    /**
     * Get the values of dices as a string
     */
    @Override
    public String toString() {
        String diceString = "";

        for (int i = 0; i < dices.length; ++i) {
            diceString += dices[i].toString() + "\n";
        }
        return diceString;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
