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
    private boolean selected;
    private boolean saved;

    /**
     * Constructor that initializes a dice to 1
     */
    public Dice(){
        this.value = 1;
        this.selected = false;
        this.saved = false;
    }

    /**
     * This function rolls the dice assigning and returns a
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
     * Sets the current value of a dice
     */
    public void setValue(int value){
        this.value = value;
    }

    /**
     * Checks if a dice is selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Checks if a dice is saved
     */
    public boolean isSaved() {
        return saved;
    }

    /**
     * Sets a dice as selected or unselected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Sets a dice as saved or unsaved
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    /**
     * toggle dice saved
     */
    public void toggleSaved() {
        this.saved = !saved;
    }

    /**
     * toggle dice seletion
     */
    public void toggleSelection() {
        this.selected = !selected;
    }

    /**
     * Get the value as a string
     */
    @Override
    public String toString() {
        return "This dice has the value " + value;
    }
}
