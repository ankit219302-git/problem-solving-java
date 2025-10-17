package problems.ib.complexity.time;

import java.util.ArrayList;
import java.util.Arrays;

/* Given an integer array A of length N. Where Ai is the cost of stepping on the ith stair.
From ith stair, you can go to i+1th or i+2th numbered stair.
Initially, you are at 1st stair find the minimum cost to reach Nth stair.

Problem Constraints
2 <= N <= 105
1 <= Ai <= 104 */

public class ClimbingStairs {
    public static void main(String[] args) {
        ArrayList<Integer> stairs = new ArrayList<>(Arrays.asList(1, 2, 1, 3));
        System.out.println("Minimum cost to climb " + stairs.size() + (stairs.size() == 1 ? " stair = " : " stairs = ") + solve(stairs));
    }

    static int solve(ArrayList<Integer> A) {
        int result = 0;
        return result;
    }
}
