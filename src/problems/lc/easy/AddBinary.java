package problems.lc.easy;

/*
Given two binary strings a and b, return their sum as a binary string.



Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
*/

public class AddBinary {
    public static void main(String[] args) {
        AddBinary obj = new AddBinary();
        String binary1 = "1010";
        String binary2 = "1011";
        System.out.println("Binary Sum = " + obj.addBinary(binary1, binary2));
    }
    private String addBinary(String a, String b) {
        /*
        String longStr;
        String shortStr;
        StringBuilder result;
        int carry = 0;
        if (a.length() > b.length()) {
            longStr = a;
            shortStr = b;
        } else {
            longStr = b;
            shortStr = a;
        }
        result = new StringBuilder();
        int i = longStr.length() - 1, j = shortStr.length() - 1;
        for (; i >= 0; i--, j--) {
            if (j < 0) {
                break;
            }
            boolean oneAndCheck = longStr.charAt(i) == '1' && shortStr.charAt(j) == '1';
            boolean oneOrCheck = longStr.charAt(i) == '1' || shortStr.charAt(j) == '1';
            if (carry == 1) {
                if (oneAndCheck) {
                    result.insert(0, '1');
                    continue;
                }
                if (oneOrCheck) {
                    result.insert(0, '0');
                } else {
                    carry = 0;
                    result.insert(0, '1');
                }
            } else {
                if (oneAndCheck) {
                    result.insert(0, '0');
                    carry = 1;
                    continue;
                }
                if (oneOrCheck) {
                    result.insert(0, '1');
                } else {
                    result.insert(0, '0');
                }
            }
        }
        while (i >= 0) {
            if (carry == 1) {
                if (longStr.charAt(i) == '1') {
                    result.insert(0, '0');
                } else {
                    result.insert(0, '1');
                    carry = 0;
                }
            } else {
                result.insert(0, longStr.charAt(i));
            }
            i--;
        }
        if (carry == 1) {
            result.insert(0, '1');
        }
        return result.toString();
        */

        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }
        String binarySum = "";
        char carry = '0';
        char currentSum;
        char digit1;
        char digit2;
        int i = a.length()-1, j = b.length()-1;
        for (; i >= 0; i--, j--) {
            if (j < 0) {
                break;
            }
            digit1 = a.charAt(i);
            digit2 = b.charAt(j);
            currentSum = addDigits(digit1, digit2);
            if (carry == '1') {
                if (currentSum == '0') {
                    carry = '0';
                }
                currentSum = addDigits(currentSum, '1');
            }
            if (digit1 == '1' && digit2 == '1') {
                carry = '1';
            }
            binarySum = currentSum + binarySum;
        }
        if (i > -1) {
            binarySum = fillRemainingDigits(i, a, carry, binarySum);
        } else if (j > -1) {
            binarySum = fillRemainingDigits(j, b, carry, binarySum);
        } else if (carry == '1') {
            binarySum = carry + binarySum;
        }
        return binarySum;
    }

    private String fillRemainingDigits(int index, String binNum, char carry, String binarySum) {
        char currentSum;
        while (index >= 0) {
            if (carry == '1') {
                if (binNum.charAt(index) == '0') {
                    carry = 0;
                }
                currentSum = addDigits(binNum.charAt(index), '1');
                binarySum = currentSum + binarySum;
                index--;
                continue;
            }
            binarySum = binNum.charAt(index) + binarySum;
            index--;
        }
        if (carry == '1') {
            binarySum = carry + binarySum;
        }
        return binarySum;
    }

    private char addDigits(char digit1, char digit2) {
        if (digit1 == '0' && digit2 == '0') {
            return '0';
        }
        if (digit1 == '1' && digit2 == '1') {
            return '0';
        }
        return '1';
    }
}
