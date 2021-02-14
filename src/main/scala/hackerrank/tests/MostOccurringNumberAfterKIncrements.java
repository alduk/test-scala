package hackerrank.tests;

/*
You are given a list of integers nums and an integer k.
Consider an operation where you increment some element by one.
Given that you can perform this at most k times, return the value of the most frequently occurring number you can obtain.
If there's more than one solution, pick the smallest possible number.

Constraints

n ≤ 100,000 where n is the length of nums
k < 2 ** 31
*/

import java.util.*;

public class MostOccurringNumberAfterKIncrements {

    public static int solve(int[] nums, int k) {
        int start = 0;
        int maxOcc = 0;
        Arrays.sort(nums);
        int currM = 1;
        int minN = nums[0];
        for (int i = start; i < nums.length-1; i++) {
            if(nums[i] == nums[i+1]){
                currM++;
                if(currM > maxOcc ){
                    maxOcc = currM;
                    minN = nums[i];
                }else if(currM == maxOcc){
                    minN = Math.min(minN, nums[i]);
                }
            }else{
                currM = 1;
            }
        }
        System.out.println("currM=" + currM + ", minN="+minN);
        System.out.println(Arrays.toString(nums));
        start = 0;
        int end = 0;
        while (start <= end && end <= nums.length - 1) {
            if (nums[start] == nums[end]) {
                end++;
            } else {
                int len = end - start + 1;
                int diff = 0;
                for (int i = start; i < end; i++) {
                    diff += nums[end] - nums[i];
                }
                if (diff <= k) {
                    end += 1;
                    if(end> nums.length - 1 && len > maxOcc) {
                        maxOcc = len;
                        minN = nums[end - 1];
                    }
                } else {
                    if (len - 1 > maxOcc) {
                        maxOcc = len - 1;
                        minN = nums[end - 1];
                    }
                    if (len - 1 == maxOcc) {
                        minN = Math.min(minN, nums[end - 1]);
                    }
                    start++;
                }
            }
        }
        return minN;
    }

    public static void main(String[] args) {
        int[] x0 = {0, 3, 1, 3};
        int[] x = {1, 0};
        int[] x1 = {2, 0, 0, 0, 9, 9, 9, 9};
        int[] x2 = {2, 0, 0, 0, 0, 9, 9, 9, 9};
        int[] x3 = {1, 9, 9, 9, 9};
        System.out.println(solve(x0, 2));
        System.out.println(solve(x, 1));
        System.out.println(solve(x1, 6));
        System.out.println(solve(x2, 6));
        System.out.println(solve(x3, 6));
    }
}
