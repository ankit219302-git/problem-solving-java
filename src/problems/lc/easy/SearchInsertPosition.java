package problems.lc.easy;

/*
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4
*/

public class SearchInsertPosition {
    public static void main(String[] args) {
        SearchInsertPosition obj = new SearchInsertPosition();
        int[] sortedDistinctNums = {1,3,5,6};
        int targetNum = 9;
        System.out.println("Target index = " + obj.searchInsert(sortedDistinctNums, targetNum));
    }
    private int searchInsert(int[] nums, int target) {
        /*
        int low = 0, mid = 0;
        int high = nums.length - 1;
        while (high >= low) {
            mid = (high - low)/2 + low;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
                mid++;
            }
        }
        return mid;
        */

        int i = 0, j = nums.length-1, mid = -1;
        while (i <= j) {
            mid = (j-i)/2 + i;
            if (nums[mid] == target) {
                return mid;
            }
            if (target < nums[mid]) {
                j = mid-1;
            } else {
                i = mid+1;
            }
        }
        if (nums[mid] < target) {
            return mid+1;
        }
        return mid;
    }
}
