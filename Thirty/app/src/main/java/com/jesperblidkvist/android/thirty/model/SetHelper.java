package com.jesperblidkvist.android.thirty.model;

import android.util.Log;

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

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        Arrays.sort(candidates);
        System.out.println("****Candidates****");
        System.out.println(Arrays.toString(candidates));
        System.out.println("****Candidates****");
        helper(result, curr, 0, target, candidates);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> curr, int start, int target, int[] candidates){
        if(target==0){
            result.add(new ArrayList<Integer>(curr));
            return;
        }
        if(target<0){
            return;
        }

        int prev=-1;
        for(int i=start; i<candidates.length; i++){
            if(prev!=candidates[i]){ // each time start from different element
                curr.add(candidates[i]);
                helper(result, curr, i+1, target-candidates[i], candidates); // and use next element only
                curr.remove(curr.size()-1);
                prev=candidates[i];
            }
        }
    }


    /**
     * A method that uses the setSum funtions to find the number of sets that which have a given sum.
     */
    public int getCombinations(int[] set, int sum){
        System.out.println("****START****");
        for(List<Integer> a: combinationSum(set, sum)){
            System.out.print("[");
            for(int b: a){
                System.out.print(b + ", ");
            }
            System.out.println("]");
        }
        System.out.println(combinationSum(set, sum).size());
        System.out.println("****END****");
        return combinationSum(set, sum).size();
}
}

