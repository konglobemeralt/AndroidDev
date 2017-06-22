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

    public void selectDice(int index){
        dices[index].toggleSelection();
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