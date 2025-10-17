package problems.lc.easy;

/*
Given an integer x, return true if x is a
palindrome
, and false otherwise.



Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
*/

public class PalindromeNumber {
    public static void main(String[] args) {
        int num = 120020021;
        System.out.print("Is " + num + " a palindrome: " + (isPalindrome(num) ? "Yes" : "No"));
    }

    private static boolean isPalindrome(int x) {
        String numOriginal = String.valueOf(x);
        int numOriginalSize = numOriginal.length();
        for (int i = 0, j = numOriginalSize - 1; i <= j; i++, j--) {
            if (numOriginal.charAt(i) != numOriginal.charAt(j)) {
                return false;
            }
        }

        return true;

        /*if (x < 0) {
            return false;
        }
        int len = String.valueOf(x).length() - 1;
        while (x > 0) {
            int tenPow = (int) Math.pow(10, len);
            int first = x / tenPow;
            int last = x % 10;
            if (first != last) {
                return false;
            }
            x = (x % tenPow) / 10;
            len -= 2;
        }
        return true;*/

        /*String num = String.valueOf(x);
        return num.contentEquals(new StringBuilder(num).reverse());*/
    }
}