package com.jesperblidkvist.android.thirty.model;

/**
 * Created by Jesper on 2017-07-14.
 * Class that compares the length of two strings to
 * enable use of java Comparator class
 */

public class StringComparator implements java.util.Comparator<String> {

    private int referenceLength;

    public StringComparator(String reference) {
        super();
        this.referenceLength = reference.length();
    }

    public int compare(String s1, String s2) {
        int dist1 = Math.abs(s1.length() - referenceLength);
        int dist2 = Math.abs(s2.length() - referenceLength);

        return dist1 - dist2;
    }
}
