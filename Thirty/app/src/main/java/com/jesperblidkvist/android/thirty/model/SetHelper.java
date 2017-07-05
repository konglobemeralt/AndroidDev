package com.jesperblidkvist.android.thirty.model;

import android.util.Log;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *  Helper class to deal Scoring the set consisting of all dice values
 *  Implementation of algorithm suggested by Anirudh at:
 *  https://stackoverflow.com/a/30222026/6559839
 *
 */

public class SetHelper {

    private static HashSet<String> subsetWithSum = new HashSet<>();

    private static final String token = " ";

    /**
     * The method for finding the subsets that sum to a target.
     */
    public static void findSumSubsets(int[] inputSet, int targetSum, String ramp, int index) {

        if(index > (inputSet.length - 1)) {
            if(getSum(ramp) == targetSum) {
                subsetWithSum.add(ramp);
            }
            return;
        }

        findSumSubsets(inputSet, targetSum, ramp + inputSet[index] + token, index + 1);
        findSumSubsets(inputSet, targetSum, ramp, index + 1);
    }

    /**
     * A helper Method for calculating the sum from a string of integers
     *
     * @param intString the string subset
     * @return the sum of the string subset
     */
    private static int getSum(String intString) {
        int sum = 0;
        StringTokenizer sTokens = new StringTokenizer(intString, token);
        while (sTokens.hasMoreElements()) {
            sum += Integer.parseInt((String) sTokens.nextElement());
        }
        return sum;
    }


    /**
     * A method that uses the setSum funtions to find the number of sets that which have a given sum.
     */
    public int getCombinations(int[] set, int sum){
        int counter = 0;

        if(set.length < 1){
            return 0;
        }

        for(int i = 0; i<set.length ; i++){
            if(set[i]==sum){
                counter++;
            }
        }
        if(counter != 0){
            counter--;
        }

        SetHelper.findSumSubsets(set, sum, "", 0);
        for (String str: subsetWithSum) {
            Log.d("ThirtyActivity", counter + ") " + str);
            counter++;
        }

        subsetWithSum.clear();
        subsetWithSum = new HashSet<>();


        return counter;



    }
}

