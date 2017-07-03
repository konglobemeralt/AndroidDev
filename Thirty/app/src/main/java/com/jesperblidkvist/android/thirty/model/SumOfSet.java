package com.jesperblidkvist.android.thirty.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Helper class to deal Scoring the set consisting of all dice values
 *
 * @author  Jesper Blidkvist
 * @version 1.0
 * @since   2017-06-22.
 *
 * Created by Jesper on 2017-07-03.
 */

public class SumOfSet {
    private int count = 0;

    public static int sumOfSubset(int[] set, int sum)
    {
        // This has a complexity of O ( n lg n )
        Arrays.sort(set);

        int pairCount = 0;
        int leftIndex = 0;
        int rightIndex = set.length - 1;

        // The portion below has a complextiy of
        //  O ( n ) in the worst case.
        while (set[rightIndex] > sum + set[0])
        {
            rightIndex--;
        }

        while (leftIndex < rightIndex)
        {
            if (set[leftIndex] + set[rightIndex] == sum)
            {
                pairCount++;
                leftIndex++;
                rightIndex--;
            }
            else if(set[leftIndex] + set[rightIndex]  < sum)
            {
                leftIndex++;
            }
            else
            {
                rightIndex--;
            }
        }

        return pairCount;
    }

}

