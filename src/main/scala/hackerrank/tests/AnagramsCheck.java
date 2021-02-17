package hackerrank.tests;

import java.util.*;

/*
https://binarysearch.com/problems/Anagram-Checks/editorials/2889839
 */
public class AnagramsCheck {
    public boolean solveFast(String s0, String s1) {
        if (s0.length() != s1.length())
            return false;

        int[] store = new int[256];

        for (int i = 0; i < s0.length(); i++) {
            store[s0.charAt(i)]++;
            store[s1.charAt(i)]--;
        }

        for (int n : store)
            if (n != 0)
                return false;

        return true;
    }

    public boolean solve(String s0, String s1) {
        if(s0.length() != s1.length())
            return false;
        char[] chars0 = s0.toCharArray();
        Arrays.sort(chars0);
        String sorted0 = new String(chars0);
        char[] chars1 = s1.toCharArray();
        Arrays.sort(chars1);
        String sorted1 = new String(chars1);
        return sorted0.equals(sorted1);

    }

    public static void main(String[] args) {
        AnagramsCheck check = new AnagramsCheck();
        System.out.println(check.solve("listen", "silent"));
        System.out.println(check.solveFast("listen", "silent"));
    }
}
