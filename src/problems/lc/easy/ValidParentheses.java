package problems.lc.easy;

import java.util.Stack;

/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.


Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true
*/

public class ValidParentheses {
    public static void main(String[] args) {
        String str = "({}{}{}{()()()(){}})[]{()}";
        System.out.print("String " + str + " is " + ((isValid(str)) ? "VALID" : "INVALID"));
    }

    private static boolean isValid(String s) {
        /*
        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }
        char[] braces = new char[len];
        int lastIndex = -1;
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (currentChar == ')') {
                if (lastIndex != -1 && braces[lastIndex] == '(') {
                    lastIndex--;
                    continue;
                }
                return false;
            }
            if (currentChar == '}') {
                if (lastIndex != -1 && braces[lastIndex] == '{') {
                    lastIndex--;
                    continue;
                }
                return false;
            }
            if (currentChar == ']') {
                if (lastIndex != -1 && braces[lastIndex] == '[') {
                    lastIndex--;
                    continue;
                }
                return false;
            }
            lastIndex++;
            braces[lastIndex] = currentChar;
        }
        return (lastIndex == -1);
        */

        if (s.isEmpty()) {
            return false;
        }
        Stack<Character> charStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!charStack.isEmpty() && isOppositeParentheses(charStack.getLast(), s.charAt(i))) {
                charStack.pop();
                continue;
            }
            charStack.push(s.charAt(i));
        }
        return charStack.isEmpty();
    }

    private static boolean isOppositeParentheses(Character char1, char char2) {
        if (char1.equals('(') && char2 == ')') {
            return true;
        }
        if (char1.equals('{') && char2 == '}') {
            return true;
        }
        return char1.equals('[') && char2 == ']';
    }
}