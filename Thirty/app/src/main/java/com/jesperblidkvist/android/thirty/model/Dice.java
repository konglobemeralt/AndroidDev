package com.jesperblidkvist.android.thirty.model;

/**
 *  This class represents a single dice.
 *
 * @author  Jesper Blidkvist
 * @version 1.0
 * @since   2017-06-21.
 *
 * Created by Jesper on 2017-06-21.
 */

public class Dice {
    private int value;

    /**
     * Constructor that initializes a dice to 1
     */
    public Dice(){
        this.value = 1;
    }

    /**
     * This function rolls the dice assignings and returns a
     * random integer in the range 1-6 as the dices value
     */
    public int roll(){
        this.value = (int)(Math.random()*6) + 1;
        return this.value;
    }

    /**
     * Gets the current value of a dice
     */
    public int getValue(){
        return value;
    }

    /**
     * Get the value as a string
     */
    @Override
    public String toString() {
        return "This dice has the value " + value;
    }
}
