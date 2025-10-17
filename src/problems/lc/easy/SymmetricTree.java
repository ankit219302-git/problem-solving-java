package problems.lc.easy;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).


Example 1:

Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:

Input: root = [1,2,2,null,3,null,3]
Output: false
*/

public class SymmetricTree {
    public static void main(String[] args) {
        SymmetricTree obj = new SymmetricTree();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(2,97,97,null,47,80,null,-7,null,null,-7)), "lo");
        System.out.print("Given tree is" + (obj.isSymmetric(tree) ? "" : " NOT") + " SYMMETRIC");
    }

    //Iterative approach (morris traversal)
    /*
    public boolean isSymmetric(TreeNode root) {
        TreeNode currLeft = root.left;
        TreeNode currRight = root.right;
        while (currRight  != null) {
            if (currLeft == null) {
                return false;
            }
            if (currRight.right == null) {
                if (currLeft.left != null) {
                    return false;
                }
                if (currRight.val != currLeft.val) {
                    return false;
                }
                currRight = currRight.left;
                currLeft = currLeft.right;
            } else {
                if (currLeft.left == null) {
                    return false;
                }
                TreeNode prevRight = currRight.right;
                TreeNode prevLeft = currLeft.left;
                if (prevRight.val != prevLeft.val) {
                    return false;
                }
                while (prevRight.left != null && prevRight.left != currRight) {
                    if (prevLeft.right == null || prevLeft.right == currLeft) {
                        return false;
                    }
                    prevRight = prevRight.left;
                    prevLeft = prevLeft.right;
                    if (prevRight.val != prevLeft.val) {
                        return false;
                    }
                }
                if (prevRight.left == null) {
                    if (prevLeft.right != null) {
                        return false;
                    }
                    prevRight.left = currRight;
                    prevLeft.right = currLeft;
                    currRight = currRight.right;
                    currLeft = currLeft.left;
                } else {
                    if (prevLeft.right != currLeft) {
                        return false;
                    }
                    prevRight.left = null;
                    prevLeft.right = null;
                    if (currRight.val != currLeft.val) {
                        return false;
                    }
                    currRight = currRight.left;
                    currLeft = currLeft.right;
                }
            }
        }
        return currLeft == null;
    }
    */

    private boolean isSymmetric(TreeNode root) {
        return isSymmetricSubTree(root.left, root.right);
    }

    private boolean isSymmetricSubTree(TreeNode leftSubTree, TreeNode rightSubTree) {
        TreeNode currLeftSubTree = leftSubTree;
        TreeNode currRightSubTree = rightSubTree;
        int currLeftVal = Integer.MIN_VALUE;
        int currRightVal = Integer.MIN_VALUE;
        if (currLeftSubTree != null) {
            currLeftVal = currLeftSubTree.val;
        }
        if (currRightSubTree != null) {
            currRightVal = currRightSubTree.val;
        }
        while (currLeftSubTree != null && currRightSubTree != null) {
            if (currLeftSubTree.left == null && currRightSubTree.right == null) {
                if (currLeftSubTree.val != currRightSubTree.val) {
                    return false;
                }
                currLeftSubTree = currLeftSubTree.right;
                currRightSubTree = currRightSubTree.left;
            } else if (currLeftSubTree.left == null || currRightSubTree.right == null) {
                return false;
            } else {
                TreeNode tempLeftSubTree = currLeftSubTree.left;
                TreeNode tempRightSubTree = currRightSubTree.right;
                while (tempLeftSubTree.right != null && tempLeftSubTree.right != currLeftSubTree) {
                    tempLeftSubTree = tempLeftSubTree.right;
                }
                while (tempRightSubTree.left != null && tempRightSubTree.left != currRightSubTree) {
                    tempRightSubTree = tempRightSubTree.left;
                }
                if (tempLeftSubTree.right == null) {
                    tempLeftSubTree.right = currLeftSubTree;
                    currLeftSubTree = currLeftSubTree.left;
                } else {
                    tempLeftSubTree.right = null;
                    currLeftVal = currLeftSubTree.val;
                    currLeftSubTree = currLeftSubTree.right;
                }
                if (tempRightSubTree.left == null) {
                    tempRightSubTree.left = currRightSubTree;
                    currRightSubTree = currRightSubTree.right;
                } else {
                    tempRightSubTree.left = null;
                    currRightVal = currRightSubTree.val;
                    currRightSubTree = currRightSubTree.left;
                }
                if (currLeftVal != currRightVal) {
                    return false;
                }
            }
        }
        return currLeftSubTree == currRightSubTree;
    }
}
