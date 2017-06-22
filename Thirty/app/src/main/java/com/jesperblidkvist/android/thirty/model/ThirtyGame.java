package com.jesperblidkvist.android.thirty.model;

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


    public ThirtyGame() {
        dices = new Dice[6];
        for (int i = 0; i < dices.length; ++i) {
            dices[i] = new Dice();
        }
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
     * Roll all the dices
     */
    public void rollDices(){
        for (int i = 0; i < dices.length; ++i) {
            if(!dices[i].isSelected()){
                dices[i].roll();
            }

        }

    }

    /**
     * Toggles selection on a dice
     */
    public void selectDice(int index){
        dices[index].toggleSelection();
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

        if(selectedCount == 6){
            return false;
        }
        else{
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

}
