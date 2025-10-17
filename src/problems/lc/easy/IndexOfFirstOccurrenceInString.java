package problems.lc.easy;

/*
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.



Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
Example 2:

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
*/

public class IndexOfFirstOccurrenceInString {
    public static void main(String[] args) {
        IndexOfFirstOccurrenceInString obj = new IndexOfFirstOccurrenceInString();
        String needle = "mississippi";
        String haystack = "issipi";
        System.out.println("Index of first occurrence of substring: " + obj.strStr(needle, haystack));
    }
    public int strStr(String haystack, String needle) {
        //return haystack.indexOf(needle);

        /*
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int fromIndex = -1;
        int needleIndex = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (needleIndex == needle.length()) {
                return fromIndex;
            }
            if (haystack.charAt(i) == needle.charAt(needleIndex)) {
                if (fromIndex == -1) {
                    if (haystack.length() - i < needle.length()) {
                        return -1;
                    }
                    fromIndex = i;
                }
                needleIndex++;
                continue;
            }
            if (fromIndex != -1) {
                i = fromIndex;
            }
            needleIndex = 0;
            fromIndex = -1;
        }
        return fromIndex;
        */

        if (needle.length() > haystack.length()) {
            return -1;
        }
        if (needle.equals(haystack)) {
            return 0;
        }
        int index = -1;
        int subStrLen = needle.length();
        int tempIndex = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (subStrLen == 0) {
                return index;
            }
            if (haystack.charAt(i) == needle.charAt(tempIndex)) {
                if (index == -1) {
                    index = i;
                }
                subStrLen--;
                tempIndex++;
                continue;
            }
            if (tempIndex != 0) {
                i = index;
                index = -1;
                tempIndex = 0;
                subStrLen = needle.length();
            }
        }
        if (subStrLen != 0) {
            return -1;
        }
        return index;
    }
}
