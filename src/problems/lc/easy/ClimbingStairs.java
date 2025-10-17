package problems.lc.easy;

/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/

public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs obj = new ClimbingStairs();
        int steps = 45;
        System.out.println("Number of ways to reach the top = " + obj.climbStairs(steps));
    }
    private int climbStairs(int n) {
        /*
        //   The value for n stairs would be the fibonacci number starting from 2nd value in the series

        //Fibonacci : 1 , 1 , 2 , 3 , 5 , 8 , 13 , 21 , 34 , 55 ....
        //        n :     1 , 2 , 3 , 4 , 5 ,  6 ,  7 ,  8 ,  9 ....
        int prevNum = 1;
        int currentNum = 1;
        for (int i = 2; i <= n; i++) {
            int tempNum = currentNum;
            currentNum += prevNum;
            prevNum = tempNum;
        }
        return currentNum;
        */

        long numOfWays = 0;
        for (int i = 0, j = n; i <= j ; i++, j--) {
            int R = i, N = j, diff = j-i;
            java.math.BigInteger nFact = java.math.BigInteger.ONE;
            java.math.BigInteger rFact = java.math.BigInteger.ONE;
            java.math.BigInteger diffFact = java.math.BigInteger.ONE;
            int endNum = Math.max(R, diff);
            if (diff < R) {
                R = 0;
            } else {
                diff = 0;
            }
            while (N > endNum) {
                if (diff == N) {
                    break;
                }
                if (diff > 0) {
                    diffFact = diffFact.multiply(java.math.BigInteger.valueOf(diff));
                    diff--;
                }
                if (R > 0) {
                    rFact = rFact.multiply(java.math.BigInteger.valueOf(R));
                    R--;
                }
                nFact = nFact.multiply(java.math.BigInteger.valueOf(N));
                N--;
            }
            numOfWays += nFact.divide(rFact.multiply(diffFact)).longValue();
        }
        return (int) numOfWays;

        /*long numOfWays = 0;
        for (int i = 0, j = n; i <= j ; i++, j--) {
            int R = i, N = j, diff = j-i;
            long nFact = 1, rFact = 1, diffFact = 1;
            int endNum = Math.max(R, diff);
            if (diff < R) {
                R = 0;
            } else {
                diff = 0;
            }
            while (N > endNum) {
                if (diff == N) {
                    break;
                }
                if (diff > 0) {
                    diffFact *= diff--;
                }
                if (R > 0) {
                    rFact *= R--;
                }
                nFact *= N--;
            }
            numOfWays += (nFact) / (rFact * diffFact);
        }
        return (int) numOfWays;*/
    }
}
