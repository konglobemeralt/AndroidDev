package com.jesperblidkvist.android.thirty.model;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 *  Helper class to deal Scoring the set consisting of all dice values
 *  Implementation of algorithm suggested by Anirudh at:
 *  https://stackoverflow.com/a/30222026/6559839
 *
 */

public class SetHelper {

    /**
     * The collection for storing the unique sets that sum to a target.
     */
    private static ArrayList<String> allSubsets = new ArrayList<>();

    /**
     * An array keeping track of how many instances of a given dice value is used.
     */
    private int[] values;
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

        if (index > (input.length - 1)) {
            if (getSum(ramp) == target) {
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

    /**
     * A method that uses the setSum funtions to find the number of sets that which have a given sum.
     */
    public int getCombinations(int[] set, int sum) {
        System.out.println("****START****");

        values = new int[]{0, 0, 0 ,0 ,0 ,0};
        countOccurences(set);
       // System.out.println("****DICE COUNT****");
       // for(int j=0; j<values.length; j++){
       //     System.out.println("Amount of " + (j+1) + "s: "+ values[j]);
       // }

        int counter = 0;
        SetHelper.findTargetSumSubsets(set, sum, "", 0);

        java.util.Collections.sort(allSubsets, new StringComparator(""));

        for (String str : allSubsets) {
            System.out.println(counter + ") " + str);
                if(dicesAvaliable(str)){
                    counter++;
                }
        }

        System.out.println("****DICE LEFT****");
        for(int j=0; j<values.length; j++){
            System.out.println("Amount of " + (j+1) + "s: "+ values[j]);
        }


        System.out.println("****END****");
        allSubsets = new ArrayList<>();
        return counter;
    }


    private void countOccurences(int[] array){
        for(int i=0; i < array.length; i++ ){
            values[array[i]-1]++; //Add one to the place in the array;
            System.out.println("Added a " + array[i]);
        }
    }

    private boolean dicesAvaliable(String str){
       String[] array = str.split(" ");

       for(int i=0; i < array.length; i++){
           if(values[Integer.parseInt(array[i])-1]-1 < 0 ){
               return false;
           }
           values[Integer.parseInt(array[i])-1]--;
           System.out.println("Removed a " + array[i]);
       }

        return true;
}

}




