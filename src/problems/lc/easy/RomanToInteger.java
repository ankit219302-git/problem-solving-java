package problems.lc.easy;

/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.



Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

public class RomanToInteger {
    public static void main(String[] args) {
        String roman = "MCMXCIV";
        System.out.print("Roman to integer conversion of " + roman + " = " + romanToInt(roman));
    }

    private static int romanToInt(String s) {
        /*
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char c1 = 'a';
            if (i+1 < s.length()) {
                c1 = s.charAt(i+1);
            }
            switch (c) {
                case 'I': {
                    if (c1 == 'V') {
                        result += 4;
                        i++;
                        continue;
                    } else if (c1 == 'X') {
                        result += 9;
                        i++;
                        continue;
                    } else {
                        result += 1;
                    }
                    break;
                }
                case 'X': {
                    if (c1 == 'L') {
                        result += 40;
                        i++;
                        continue;
                    } else if (c1 == 'C') {
                        result += 90;
                        i++;
                        continue;
                    } else {
                        result += 10;
                    }
                    break;
                }
                case 'C': {
                    if (c1 == 'D') {
                        result += 400;
                        i++;
                        continue;
                    } else if (c1 == 'M') {
                        result += 900;
                        i++;
                        continue;
                    } else {
                        result += 100;
                    }
                    break;
                }
                case 'V': {
                    result += 5;
                    break;
                }
                case 'L': {
                    result += 50;
                    break;
                }
                case 'D': {
                    result += 500;
                    break;
                }
                case 'M': {
                    result += 1000;
                    break;
                }
            }
        }
        return result;
         */

        if (s == null || s.isEmpty()) {
            return -1;
        }
        int result = 0;
        int tempResult;
        //Map<String, Integer> conversionMap = getConversionMap();
        for (int i = 0; i < s.length(); i++) {
            tempResult = result;
            if (i+1 < s.length()) {
                result += getIntegerValue(String.valueOf(s.charAt(i)) + s.charAt(i + 1));
            }
            if (tempResult == result) {
                result += getIntegerValue(String.valueOf(s.charAt(i)));
            } else {
                i++;
            }

            if (result == 0) {
                return -1;
            }
        }
        return result;
    }

    private static int getIntegerValue(String str) {
        switch (str) {
            case "I": return 1;
            case "V": return 5;
            case "X": return 10;
            case "L": return 50;
            case "C": return 100;
            case "D": return 500;
            case "M": return 1000;
            case "IV": return 4;
            case "IX": return 9;
            case "XL": return 40;
            case "XC": return 90;
            case "CD": return 400;
            case "CM": return 900;
            default: return 0;
        }
    }

    /*private static Map<String, Integer> getConversionMap() {
        Map<String, Integer> conversionMap = new HashMap<>();
        conversionMap.put("I", 1);
        conversionMap.put("V", 5);
        conversionMap.put("X", 10);
        conversionMap.put("L", 50);
        conversionMap.put("C", 100);
        conversionMap.put("D", 500);
        conversionMap.put("M", 1000);
        conversionMap.put("IV", 4);
        conversionMap.put("IX", 9);
        conversionMap.put("XL", 40);
        conversionMap.put("XC", 90);
        conversionMap.put("CD", 400);
        conversionMap.put("CM", 900);
        return conversionMap;
    }*/
}
