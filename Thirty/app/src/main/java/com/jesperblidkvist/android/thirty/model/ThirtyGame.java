package com.jesperblidkvist.android.thirty.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a game of Thirty.
 *
 * @author Jesper Blidkvist
 * @version 1.0
 * @since 2017-06-22.
 * <p>
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

    /**
     * New Game constructor.
     */
    public ThirtyGame() {
        dices = new Dice[6];
        for (int i = 0; i < dices.length; ++i) {
            dices[i] = new Dice();
        }
        roundScore = 0;
        totalScore = roundScore;
        redoCount = roundCount = 0;

        scoringMehtods = new ArrayList<>(
                Arrays.asList("low", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
        rollDices();
    }

    /**
     * New Game from parcel constructor.
     */
    public ThirtyGame(Parcel input) {
        this.totalScore = input.readInt();
        this.roundScore = input.readInt();
        this.roundCount = input.readInt();

        dices = new Dice[6];

        for (int i = 0; i < dices.length; ++i) {
            dices[i] = new Dice();
            dices[i].setValue(input.readInt());

            int diceBool = input.readInt();
            if (diceBool == 1) {
                dices[i].setSaved(true);
            }
        }
        this.redoCount = 0;
    }

    /**
     * Sets all the dices in the game to 1 amd resets saved and selected values.
     */
    private void resetDice() {
        for (int i = 0; i < dices.length; ++i) {
            dices[i].setSelected(false);
            dices[i].setSaved(false);
        }
        rollDices();
    }

    /**
     * Sets redo counter to 0.
     */
    private void resetRedoCounter() {
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
     * Gets the redo count.
     */
    public int getRedoCount() {
        return redoCount;
    }

    public int getRoundCount(){
        return roundCount;
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
            if (dices[i].isSaved()) {
                values.add(dices[i].getValue());
            }
        }
        return values;
    }

    /**
     * Returns the dice at a specific index
     */
    public Dice getDiceAtIndex(int i) {
        return dices[i];
    }

    /**
     * Returns the value of a dice at a specific index
     */
    public int getDiceValueAtIndex(int i) {
        return dices[i].getValue();
    }

    /**
     * Roll all the dices that are not saved or selected
     */
    private void rollDices() {
        for (int i = 0; i < dices.length; ++i) {
            if (!dices[i].isSaved()) {
                dices[i].roll();
            }
        }
    }

    /**
     * Increase the roundCounter by one
     */
    private void increaseRoundCount() {
        if (redoCount != 2) {
            redoCount++;
        } else {

        }
    }

    /**
     * Increase the turncounter by one
     */
    private void increaseTurnCount() {
        if (roundCount < 10) {
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
    public void playTurn() {
        rollDices();
        increaseRoundCount();
    }

    /**
     * Ends a turn
     */
    public void endTurn(String scoreChoice) {
        increaseTurnCount();
        scoringMehtods.remove(scoreChoice);
        if (scoreChoice != "low") {
            roundScore = calculatePoints(Integer.parseInt(scoreChoice));
            Log.d("ThirtyActivity", "RouncScoreCalc " + Integer.toString(roundScore));
        } else {
            roundScore = calculateLow();
            Log.d("ThirtyActivity", "LowScoreCalc " + Integer.toString(roundScore));
        }
        Log.d("ThirtyActivity", "CalcPoints " + Integer.toString(roundScore));
        saveChoices(scoreChoice);
        resetGame();
    }

    /**
     * Save choices made as a string
     */
    private void saveChoices(String scoreChoice) {
        choices.add("Played " + scoreChoice + " and selected: " + savedDice.toString() + " For " + roundScore + " Points " + "\n");
        for (int i = 0; i < choices.size(); i++) {
            Log.d("ThirtyActivity", "round " + (i + 1) + ": " + choices.get(i).toString() + "\n");
        }

    }

    /**
     * Reset game variables
     */
    private void resetGame() {
        resetRedoCounter();
        resetDice();
        savedDice.clear();
        totalScore += roundScore;
        roundScore = 0;
    }

    /**
     * Toggles save on a dice and adds to list of saved dices
     */
    public void saveDice(int index) {
        dices[index].toggleSaved();
        if (!dices[index].isSaved()) {
            savedDice.remove(dices[index]);
        } else {
            savedDice.add(dices[index]);
        }
    }

    /**
     * Calculates low
     */
    private int calculateLow() {
        int score = 0;
        for (int i = 0; i < dices.length; ++i) {
            if (dices[i].isSaved() && dices[i].getValue() <= 3) {
                score++;
            }
        }
        return score;
    }

    /**
     * Checks if game is over
     */
    public boolean isGameOver() {
        if (roundCount == 10) {
            gameStatus = "Game over!";
            return true;
        } else {
            return false;
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
    private int calculatePoints(int scoringMethod) {
        int[] set = new int[getAllSelectedDiceValues().size()];
        for (int i = 0; i < set.length; i++) {
            set[i] = getAllSelectedDiceValues().get(i).intValue();
        }
        SetHelper scoring = new SetHelper();
        return scoring.getCombinations(set, scoringMethod);
    }

    /**
     * Returns a list of scoring methods avaliable
     */
    public List<String> getAvaliableScoringMethods() {
        return scoringMehtods;
    }

    /**
     * Get the status of the game as a string.
     */
    public String getGameStatus() {
        return gameStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(new int[]{
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

    public int getRoundScore(String scoringMethod){
        if (scoringMethod != "low") {
            roundScore = calculatePoints(Integer.parseInt(scoringMethod));
        } else {
            roundScore = calculateLow();
        }
        return roundScore;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ThirtyGame createFromParcel(Parcel in) {
            return new ThirtyGame(in);
        }

        public ThirtyGame[] newArray(int size) {
            return new ThirtyGame[size];
        }
    };


}
