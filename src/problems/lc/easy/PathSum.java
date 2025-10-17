package problems.lc.easy;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;

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
