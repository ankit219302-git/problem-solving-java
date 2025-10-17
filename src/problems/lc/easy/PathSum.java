package problems.lc.easy;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;


/*
Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.



Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There are two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.
Example 3:

Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
*/


public class PathSum {
    public static void main(String[] args) {
        PathSum obj = new PathSum();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(3,9,20,13,null,15,7,null,8,null,11,16,17,19,10,null,null,18,null,null,19)), "lo");
        System.out.print("Has target path : " + obj.hasPathSum(tree, 43));
    }
    private boolean hasPathSum(TreeNode root, int targetSum) {
        try {
            hasPathSumRecursive(root, targetSum, 0);
        } catch (BrokenRecursion ex) {
            return true;
        }
        return false;
    }

    private void hasPathSumRecursive(TreeNode root, int targetSum, int sum) throws BrokenRecursion {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (sum+root.val == targetSum) {
                throw new BrokenRecursion();
            }
            return;
        }
        hasPathSumRecursive(root.left, targetSum, sum + root.val);
        hasPathSumRecursive(root.right, targetSum, sum + root.val);
    }

    static class BrokenRecursion extends Exception {}
}
