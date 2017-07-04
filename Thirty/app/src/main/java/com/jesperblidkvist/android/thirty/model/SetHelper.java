package com.jesperblidkvist.android.thirty.model;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *  Helper class to deal Scoring the set consisting of all dice values
 *
 * @author  Jesper Blidkvist
 * @version 1.0
 * @since   2017-06-22.
 *
 * Created by Jesper on 2017-07-03.
 */

public class SetHelper {


    /**
     * The collection for storing the unique sets that sum to a target.
     */
    private static HashSet<String> allSubsets = new HashSet<>();

    /**
     * The String token
     */
    private static final String token = " ";

    /**
     * The method for finding the subsets that sum to a target.
     *
     * @param input  The input array to be processed for subset with particular sum
     * @param target The target sum we are looking for
     * @param ramp   The Temporary String to be beefed up during recursive iterations(By default value an empty String)
     * @param index  The index used to traverse the array during recursive calls
     */
    public static void findTargetSumSubsets(int[] input, int target, String ramp, int index) {

        if(index > (input.length - 1)) {
            if(getSum(ramp) == target) {
                allSubsets.add(ramp);
            }
            return;
        }

        //First recursive call going ahead selecting the int at the currenct index value
        findTargetSumSubsets(input, target, ramp + input[index] + token, index + 1);
        //Second recursive call going ahead WITHOUT selecting the int at the currenct index value
        findTargetSumSubsets(input, target, ramp, index + 1);
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



    public int getCombinations(int[] set, int sum){
        int counter = 0;

        if(set.length < 1){
            return 0;
        }

        SetHelper.findTargetSumSubsets(set, sum, "", 0);
        for (String str: allSubsets) {
            Log.d("ThirtyActivity", counter + ") " + str);
            counter++;
        }



        counter -= 1;

        allSubsets.clear();
        allSubsets = new HashSet<>();


        return counter;



    }
}

