package problems.lc.easy;

import ds.TreeNode;
import util.TreeUtil;

import java.util.Arrays;

/*
Given a binary tree, determine if it is
height-balanced.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true

Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Example 3:
Input: root = []
Output: true
*/

public class BalancedBinaryTree {
    public static void main(String[] args) {
        BalancedBinaryTree obj = new BalancedBinaryTree();
        System.out.print("Is binary tree balanced : " + obj.isBalanced(TreeUtil.createBinaryTree(Arrays.asList(1,2,2,3,null,null,3,4,null,null,4), "lo")));
    }
    private boolean isBalanced(TreeNode root) {
        return getBalancedDepthOfTree(root) != -1;
    }

    private int getBalancedDepthOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSubtreeDepth = getBalancedDepthOfTree(root.left);
        if (leftSubtreeDepth == -1) {
            return -1;
        }
        int rightSubtreeDepth = getBalancedDepthOfTree(root.right);
        if (rightSubtreeDepth == -1) {
            return -1;
        }
        if (Math.abs(leftSubtreeDepth - rightSubtreeDepth) > 1) {
            return -1;
        }
        return 1 + Math.max(leftSubtreeDepth, rightSubtreeDepth);
    }
}
