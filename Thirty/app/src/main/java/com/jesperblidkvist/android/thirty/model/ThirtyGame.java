package com.jesperblidkvist.android.thirty.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  This class represents a game of Thirty.
 *
 * @author  Jesper Blidkvist
 * @version 1.0
 * @since   2017-06-22.
 *
 * Created by Jesper on 2017-06-22.
 */

public class ThirtyGame implements Parcelable {

    private Dice[] dices;
    int redoCount;
    int roundCount;

    int totalScore;
    int roundScore;

    private List<Dice> savedDice = new ArrayList<>();
    private List<String> choices = new ArrayList<>();
    private List<String> scoringMehtods = new ArrayList<>();

    String gameStatus;

    private SumOfSet scoring = new SumOfSet();

    public ThirtyGame() {
        dices = new Dice[6];
        for (int i = 0; i < dices.length; ++i) {
            dices[i] = new Dice();
        }

        roundScore = 0;
        totalScore = roundScore;

        redoCount = 0;
        roundCount = 0;

        scoringMehtods = new ArrayList<>(
                Arrays.asList("low", "4", "5", "6", "7", "8", "9", "10", "11", "12"));

        rollDices();
    }

    public ThirtyGame(Parcel input ){


        this.totalScore = input.readInt();
        this.roundScore = input.readInt();
        this.roundCount = input.readInt();

        dices = new Dice[6];
        for (int i = 0; i < dices.length; ++i) {

            dices[i] = new Dice();
            dices[i].setValue(input.readInt());

            int diceBool = input.readInt();
            if(diceBool == 1){
                dices[i].setSaved(true);
            }
        }
        this.redoCount = 0;

    }

    /**
     * Sets all the dices in the game to 1.
     */
    private void resetDice(){
        for (int i = 0; i < dices.length; ++i) {
            dices[i].setSelected(false);
            dices[i].setSaved(false);
        }
        rollDices();
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
    public ArrayList<Integer> getAllDiceValues() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < dices.length; ++i) {
            values.add(dices[i].getValue());
        }
        return values;
    }

    /**
     * Returns all the selected dices in the game.
     */
    public ArrayList<Integer> getAllSelectedDiceValues() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < dices.length; ++i) {
            values.add(dices[i].getValue());
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

    /**
     * Increase the roundCounter by one
     */
    private void increaseRoundCount(){
        if(redoCount !=2 ){
            redoCount++;
        }else
        {

        }
    }

    /**
     * Increase the turncounter by one
     */
    private void increaseTurnCount(){
        if(roundCount <10 ){
            roundCount++;
        }
    }

    /**
     * Gets the choices made during gameplay as a list of strings.
     */
    public List<String> getChoices() {
        return choices;
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
    public void endTurn(String scoreChoice){
        increaseTurnCount();
        scoringMehtods.remove(scoreChoice);
        if(scoreChoice != "low"){
            roundScore += calculatePoints(Integer.parseInt(scoreChoice));
        }else
        {
            roundScore += calculateLow();
        }

        saveChoices(scoreChoice);
        resetGame();
    }

    private void saveChoices(String scoreChoice){
        choices.add("Plaved " + scoreChoice + " and selected: " + savedDice.toString() + " For " + roundScore + " Points " + "\n");
        for(int i = 0; i < choices.size(); i++){
            Log.d("ThirtyActivity", "round " + (i + 1) + ": " + choices.get(i).toString() + "\n");
        }

    }

    /**
     * Reset game variables
     */
    private void resetGame(){
        resetRedoCounter();
        resetDice();
        savedDice.clear();
        totalScore += roundScore;
        roundScore = 0;}



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
        if(dices[index].isSaved()){
            dices[index].toggleSaved();
            savedDice.remove(dices[index]);
        }
        else{
                dices[index].toggleSaved();
                savedDice.add(dices[index]);
        }
    }

 /**
  * Calculates low
  */
private int calculateLow(){
    int score = 0;
    for (int i = 0; i < dices.length; ++i) {
        if(dices[i].isSaved() && dices[i].getValue() <= 3){
            score++;
        }
    }
    return score;
}

    /**
     * Checks if game is over
     */
    public boolean isGameOver(){
         if(roundCount == 9){
            gameStatus = "Game over!";
            return true;
        }
        else{
             return false;
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

    /**
     * Calculate score from set of selected dices
     */
    private int calculatePoints(int scoringMethod){
        int[] set = new int[getAllSelectedDiceValues().size()];
        for (int i=0; i < set.length; i++) {
            set[i] = getAllSelectedDiceValues().get(i).intValue();
        }

        return scoring.sumOfSubset(set, scoringMethod);
    }

    public List<String> getAvaliableScoringMethods(){
        return scoringMehtods;

    }

    public String getGameStatus() {
        return gameStatus;
    }


    // Parcelling part
    //TODO: Perhaps create a parcelData class that handles this?
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(new int[] {
                this.totalScore,
                this.roundScore,
                this.roundCount,
                this.dices[0].getValue(),
                this.dices[0].isSaved() ? 1 : 0,
                this.dices[1].getValue(),
                this.dices[1].isSaved() ? 1 : 0,
                this.dices[2].getValue(),
                this.dices[2].isSaved() ? 1 : 0,
                this.dices[3].getValue(),
                this.dices[3].isSaved() ? 1 : 0,
                this.dices[4].getValue(),
                this.dices[4].isSaved() ? 1 : 0,
                this.dices[5].getValue(),
                this.dices[5].isSaved() ? 1 : 0,
        });


    }

 //  public ThirtyGame(Parcel in){
 //      int[] data = new int[3];
 //      in.readIntArray(data);
 //      // the order needs to be the same as in writeToParcel() method
 //      this.totalScore = data[0];
 //      this.roundScore = data[1];
 //      this.roundCount = data[2];
 //  }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ThirtyGame createFromParcel(Parcel in) {
            return new ThirtyGame(in);
        }

        public ThirtyGame[] newArray(int size) {
            return new ThirtyGame[size];
        }
    };


}
