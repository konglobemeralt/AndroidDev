package com.jesperblidkvist.android.thirty.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *  Helper class to deal Scoring the set consisting of all dice values
 *  Implementation of algorithm suggested by Anirudh at:
 *  https://stackoverflow.com/a/30222026/6559839
 *
 */

public class SetHelper {

    private static String subsetStringSums = "";
    private static HashSet<String> sets = new HashSet<>();

    void subsetSums(int arr[], int l, int r, int sum)
    {
        // Print current subset
        if (l > r)
        {
            subsetStringSums += sum + " ";
            sets.add(Arrays.toString(arr));

            return;
        }

        // Subset including arr[l]
        subsetSums(arr, l+1, r, sum+arr[l]);

        // Subset excluding arr[l]
        subsetSums(arr, l+1, r, sum);
    }

    /**
     * A helper Method for calculating the sum from a string of integers
     *
     * @param intString the string subset
     * @return the sum of the string subset
     */
    private static int getSum(String intString, int targetSum) {
        int sum = 0;
        StringTokenizer sTokens = new StringTokenizer(intString, " ");
        while (sTokens.hasMoreElements()) {
            int number = Integer.parseInt((String) sTokens.nextElement());
            if(number == targetSum){
                sum += number;
            }

        }
        return sum;
    }


    /**
     * A method that uses the setSum funtions to find the number of sets that which have a given sum.
     */
    public int getCombinations(int[] set, int sum){

        subsetSums(set, 0, set.length-1, 0);

        Log.d("Sumz", " : " + subsetStringSums);
        Log.d("array", " Arrays: " + Arrays.toString(sets.toArray(new String[sets.size()])));
        int returnVar = getSum(subsetStringSums, sum);
        subsetStringSums = "";
        sets.clear();
        sets = new HashSet<>();
        return returnVar;
}
}

