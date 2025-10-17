package problems.self.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumSubArraySum {
    public static void main(String[] args) {
        MaximumSubArraySum obj = new MaximumSubArraySum();
        List<Integer> arr = new ArrayList<>(Arrays.asList(2, 3, -8, 7, -1, 1, -1));
        System.out.println(obj.maxSubArraySum(arr));
    }

    //Kadane's algorithm [Time complexity - O(n), Space complexity - O(1)]
    private int maxSubArraySum(List<Integer> arr) {
        if (arr == null || arr.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int result = arr.getFirst();
        int currentMaxSum = arr.getFirst();
        for (int i = 1; i < arr.size(); i++) {
            currentMaxSum = Math.max(currentMaxSum + arr.get(i), arr.get(i));
            result = Math.max(currentMaxSum, result);
        }
        return result;
    }
}
