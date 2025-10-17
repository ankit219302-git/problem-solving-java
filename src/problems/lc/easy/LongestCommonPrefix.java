package problems.lc.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".



Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
*/

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] words = {"flower","flow","flight"};
        System.out.print("Longest common prefix in the given input - " + longestCommonPrefix(words));
    }

    private static String longestCommonPrefix(String[] strs) {
        /*int i = 0, j = 1;
        for (; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (; j < strs.length; j++) {
                if (i == strs[j].length()) {
                    return strs[0].substring(0,i);
                }
                if (strs[j].charAt(i) != ch) {
                    if (i == 0) {
                        return "";
                    }
                    return strs[0].substring(0,i);
                }
            }
            j = 1;
        }
        return strs[0].substring(0,i);*/

        String result = "";
        List<String> sortedWords = Arrays.asList(strs);
        Collections.sort(sortedWords);
        String firstWord = sortedWords.getFirst();
        String lastWord = sortedWords.getLast();
        int length = Math.min(firstWord.length(), lastWord.length());
        if (length == 0) {
            return result;
        }
        for (int i = 0; i < length; i++) {
            if (firstWord.charAt(i) != lastWord.charAt(i)) {
                break;
            }
            result += String.valueOf(firstWord.charAt(i));
        }
        return result;
    }
}