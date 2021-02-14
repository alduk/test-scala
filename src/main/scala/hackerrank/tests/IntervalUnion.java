package hackerrank.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
https://binarysearch.com/problems/Interval-Union
Given a two-dimensional integer list intervals representing unsorted inclusive intervals, return their union in sorted order.

Constraints

n ≤ 100,000 where n is the length of intervals
 */

public class IntervalUnion {
    public static int[][] solve(int[][] intervals) {
        List<int[]> ret = new ArrayList<>();
        System.out.println(Arrays.deepToString(intervals));
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        ret.add(intervals[0]);
        int[] prev = intervals[0];
        for(int i = 1; i<intervals.length;i++){
            if(prev[1] >= intervals[i][0]){
                prev[1] = Math.max(prev[1], intervals[i][1]);
            }else{
                prev = intervals[i];
                ret.add(prev);
            }
        }
        return ret.toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        int[][] x1 = {
                {0, 5},
                {4, 6}
        };

        int[][] x2 = {
                {1, 2},
                {3, 4}
        };

        int[][] x3 = {
                {5, 6},
                {1, 2}
        };

        System.out.println(Arrays.deepToString(solve(x1)));
        System.out.println(Arrays.deepToString(solve(x2)));
        System.out.println(Arrays.deepToString(solve(x3)));
    }
}
