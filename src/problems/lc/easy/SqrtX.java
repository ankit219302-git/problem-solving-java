package problems.lc.easy;

/*
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.


Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.
Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
*/

public class SqrtX {
    public static void main(String[] args) {
        SqrtX obj = new SqrtX();
        int num = 2147483647;
        System.out.println("Square root of " + num + " = " + obj.mySqrt(num));
    }
    private int mySqrt(int x) {
        //return (int)Math.sqrt(x);

        /*
        if (x == 0 || x == 1) {
            return x;
        }
        int low = 1;
        //int high = (x/2 >= 46340) ? 46340 : x/2;   // sqrt(Integer.MAX_VALUE) = 46340 , so that mid*mid doesn't overflow
        int high = x/2;
        int mid = 0;
        while (low <= high) {
            mid = (high - low)/2 + low;
            if (mid == x / mid) {
                return mid;
            }
            if (mid > x / mid) {
                high = mid - 1;
                mid--;
            } else {
                low = mid + 1;
            }
        }
        return mid;
        */

        /*if (x == 0) {
            return 0;
        }
        for (int i = 1; i <= x/2+1; i++) {
            if (i == x/i) {
                return i;
            }
            if (i > x/i) {
                return i-1;
            }
        }
        return -1;*/

        if (x == 0 || x == 1) {
            return x;
        }
        int start = 1, end = x / 2, root = 0;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (mid == x/mid) {
                return mid;
            }
            if (mid < x/mid) {
                start = mid + 1;
                root = mid;
            } else {
                end = mid - 1;
            }
        }
        return root;
    }
}
