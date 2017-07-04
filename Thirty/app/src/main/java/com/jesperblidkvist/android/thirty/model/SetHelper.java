package com.jesperblidkvist.android.thirty.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static void permute(int[] arr){
        permuteHelper(arr, 0);
    }

    private static void permuteHelper(int[] arr, int index){
        if(index >= arr.length - 1){ //If we are at the last element - nothing left to permute
            //System.out.println(Arrays.toString(arr));
            //Print the array
            System.out.print("[");
            for(int i = 0; i < arr.length - 1; i++){
                System.out.print(arr[i] + ", ");
            }
            if(arr.length > 0)
                System.out.print(arr[arr.length - 1]);
            System.out.println("]");
            return;
        }

        for(int i = index; i < arr.length; i++){ //For each index in the sub array arr[index...end]

            //Swap the elements at indices index and i
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;

            //Recurse on the sub array arr[index+1...end]
            permuteHelper(arr, index+1);

            //Swap the elements back
            t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
        }

    }

    public int getCombinations(int[] set, int sum){
        permute(set);
        return 5;



    }
}

