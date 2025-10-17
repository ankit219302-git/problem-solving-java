package problems.lc.easy;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
*/

public class TwoSum {
    public static void main(String[] args) {
        int[] result = twoSum(new int[]{3,2,4}, 6);
        if (result != null) {
            System.out.print("Two Sum Indices : {" + result[0] + ", " + result[1] + "}");
        } else {
            System.out.println("No two sum present");
        }
    }

    private static int[] twoSum(int[] nums, int target) {
        int[] indices = null;
        Map<Integer, String> numMap = createNumMap(nums);
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            if (numMap.containsKey(num2)) {
                indices = new int[2];
                String[] indexArray = numMap.get(num2).split(",");
                indices[0] = i;
                indices[1] = Integer.parseInt(indexArray[0]);
                if (indexArray.length > 1 && num1 == num2) {
                    indices[0] = Integer.parseInt(indexArray[0]);
                    indices[1] = Integer.parseInt(indexArray[1]);
                }
                if (indices[0] == indices[1]) {
                    indices = null;
                    continue;
                }
                break;
            }
        }

        return indices;
    }

    private static Map<Integer, String> createNumMap(int[] nums) {
        Map<Integer, String> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(nums[i])) {
                numMap.put(nums[i], numMap.get(nums[i]).concat(",").concat(String.valueOf(i)));
                continue;
            }
            numMap.put(nums[i], String.valueOf(i));
        }

        return numMap;
    }
}