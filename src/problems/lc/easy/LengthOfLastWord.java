package problems.lc.easy;

/*
Given a string s consisting of words and spaces, return the length of the last word in the string.

A word is a maximal
substring
 consisting of non-space characters only.



Example 1:

Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
Example 2:

Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
Example 3:

Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.
*/

public class LengthOfLastWord {
    public static void main(String[] args) {
        LengthOfLastWord obj = new LengthOfLastWord();
        String sentence = "   fly me   to   the moon  ";
        System.out.println("Length of last word = " + obj.lengthOfLastWord(sentence));
    }
    private int lengthOfLastWord(String s) {
        /*
        String[] words = s.split(" ");
        return words[words.length - 1].length();
        */

        /*
        int i = s.length() - 1;
        int len = 0;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                if (len != 0) {
                    return len;
                }
                i--;
                continue;
            }
            i--;
            len++;
        }
        return len;
        */

        int length = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (length > 0) {
                    break;
                }
                continue;
            }
            length++;
        }
        return length;
    }
}
