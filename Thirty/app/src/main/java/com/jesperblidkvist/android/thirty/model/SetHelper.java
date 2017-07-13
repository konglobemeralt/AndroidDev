package com.jesperblidkvist.android.thirty.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *  Helper class to deal Scoring the set consisting of all dice values
 *  Implementation of algorithm suggested by Anirudh at:
 *  https://stackoverflow.com/a/30222026/6559839
 *
 */

public class SetHelper {

        // Returns true if there is a subset of set[] with sun equal to given sum
        static boolean isSubsetSum(int set[], int n, int sum)
        {
            // The value of subset[i][j] will be true if there
            // is a subset of set[0..j-1] with sum equal to i
            boolean subset[][] = new boolean[sum+1][n+1];

            // If sum is 0, then answer is true
            for (int i = 0; i <= n; i++)
                subset[0][i] = true;

            // If sum is not 0 and set is empty, then answer is false
            for (int i = 1; i <= sum; i++)
                subset[i][0] = false;

            // Fill the subset table in botton up manner
            for (int i = 1; i <= sum; i++)
            {
                for (int j = 1; j <= n; j++)
                {
                    subset[i][j] = subset[i][j-1];
                    if (i >= set[j-1])
                        subset[i][j] = subset[i][j] ||
                                subset[i - set[j-1]][j-1];
                }
            }

        /* // uncomment this code to print table
         for (int i = 0; i <= sum; i++)
         {
           for (int j = 0; j <= n; j++)
              printf ("%4d", subset[i][j]);
           printf("\n");
         } */

            return subset[sum][n];
        }


    /**
     * A method that uses the setSum funtions to find the number of sets that which have a given sum.
     */
    public int getCombinations(int[] set, int sum){

        if (isSubsetSum(set, set.length, sum) == true)
            System.out.println("Found a subset with given sum");
        else
            return 0;

}
