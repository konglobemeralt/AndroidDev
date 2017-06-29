package com.jesperblidkvist.android.thirty.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
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

    int currentLow;

    private List<Dice> savedDice = new ArrayList<>();


    String gameStatus;


    public ThirtyGame() {
        dices = new Dice[6];
        for (int i = 0; i < dices.length; ++i) {
            dices[i] = new Dice();
        }

        roundScore = 0;
        totalScore = roundScore;

        redoCount = 0;
        roundCount = 0;
        currentLow = 0;

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
        }

        //dices[0].setValue(input.readInt());
        //dices[1].setValue(input.readInt());
        //dices[2].setValue(input.readInt());
        //dices[3].setValue(input.readInt());
        //dices[4].setValue(input.readInt());
        //dices[5].setValue(input.readInt());




        this.redoCount = 0;
        this.currentLow = 0;

        //rollDices();


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
     * Gets the current low.
     */
    public int getCurrentLow(){
        return currentLow;
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
        resetGame();
    }

    /**
     * Reset game variables
     */
    private void resetGame(){
        resetRedoCounter();
        resetDice();
        savedDice.clear();
        totalScore += roundScore;
        roundScore = 0;
        currentLow = 0;
    }



    /**
     * Toggles selection on a dice
     */
    public void selectDice(int index){
        dices[index].toggleSelection();
    }

   private boolean isSavePossible(int dice){
       if(savedDice.size() < 2 ){
           return true;
       }
       else if((savedDice.size() % 2)!= 0){
           if(getCurrentLow() == dice + savedDice.get(savedDice.size() - 1).getValue()){
               return true;
           }
           else{
               return false;
           }

       }
       else{
           return true;
       }
   }

    /**
     * Toggles save on a dice
     */
    public void saveDice(int index){
        if(dices[index].isSaved()){
            dices[index].toggleSaved();
            savedDice.remove(dices[index]);
            //Log.d("ThirtyActivity", "Removed: " + Integer.toString(dices[index].getValue()));
        }
        else{
            if(isSavePossible(dices[index].getValue())){
                dices[index].toggleSaved();

                savedDice.add(dices[index]);
                //Log.d("ThirtyActivity", "Added: " + Integer.toString(dices[index].getValue()));
                }
        }

            // if(dices[index].isSaved()){
            //     roundScore += dices[index].getValue();
            // }
            // else{
            //     roundScore -= dices[index].getValue();
            // }


        calculateLow();
        calculateRoundScore();
    }

 /**
  * Calculates low
  */
private void calculateLow(){
    if(savedDice.size() == 2){
        setLow(savedDice.get(0).getValue()+ savedDice.get(1).getValue());
    }
    else if(savedDice.size() <= 1){
        setLow(0);
    }
}

    /**
     * Checks if game is over
     */
    public boolean isGameOver(){
         if(roundCount == 10){
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
     * Sets the current low value to a sum of two dices values
     */
    private void setLow(int sum){
        currentLow = sum;
    }

    /**
     * checks a pair of dice against first sum.
     */
    private boolean checkLow(int diceA, int diceB){
        if(diceA + diceB == currentLow){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * calculates the points awarded for choises made
     */
    private void calculateRoundScore() {
        Log.d("ThirtyActivity", "Calculating points....");
        int pairs = 0;
        for (int i = 0; i < dices.length; ++i) {
            if (dices[i].isSaved()) {
                pairs += 1;
            }
        }
        pairs /=  2;

        roundScore = pairs * currentLow;
        Log.d("ThirtyActivity", Integer.toString(roundScore));
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
                this.dices[1].getValue(),
                this.dices[2].getValue(),
                this.dices[3].getValue(),
                this.dices[4].getValue(),
                this.dices[5].getValue()
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
            Log.d("ThirtyActivity", "Returning that");
            return new ThirtyGame(in);
        }

        public ThirtyGame[] newArray(int size) {
            Log.d("ThirtyActivity", "Returning this");
            return new ThirtyGame[size];
        }
    };


}
