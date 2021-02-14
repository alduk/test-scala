package hackerrank.tests;

import java.util.HashMap;
import java.util.Map;

public class SumPairsToTarget {
    public int solve(int[] nums, int target) {
        if (nums.length < 2)
            return 0;
        int count = 0;
        Map<Integer, Integer> m = new HashMap<>();
        for (int num: nums) {
            int diff = target - num;

            Integer diffVal = m.getOrDefault(diff, 0);
            if (diffVal > 0) {
                count++;
                m.put(diff, diffVal - 1);
            } else {
                m.put(num, m.getOrDefault(num, 0) + 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SumPairsToTarget obj = new SumPairsToTarget();
        System.out.println(obj.solve(new int[]{1, 3, 5, 3, 7}, 6));
        System.out.println(obj.solve(new int[]{6}, 6));
        System.out.println(obj.solve(new int[]{2, 2, 2}, 4));
        System.out.println(obj.solve(new int[]{1, 1, 0, 0}, 1));
        System.out.println(obj.solve(new int[]{0, 1, 1}, 1));
    }
}
