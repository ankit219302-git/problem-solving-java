package problems.lc.easy;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.



Example 1:


Input: p = [1,2,3], q = [1,2,3]
Output: true
Example 2:


Input: p = [1,2], q = [1,null,2]
Output: false
Example 3:


Input: p = [1,2,1], q = [1,1,2]
Output: false
*/

public class SameTree {
    public static void main(String[] args) {
        SameTree obj = new SameTree();
        TreeNode tree1 = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(2,3,3,4,5,null,4)), "lo");
        TreeNode tree2 = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(2,3,3,4,5,4,4)), "lo");

        boolean isSameTree = obj.isSameTree(tree1, tree2);
        System.out.print("Given 2 trees are" + (isSameTree ? "" : " NOT") + " SAME");
    }
    //recursive check
    /*private boolean traverseAndCheck(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null || tree2 == null) {
            return tree1 == tree2;
        }
        if (!traverseAndCheck(tree1.left, tree2.left)) {
            return false;
        }
        if (tree1.val != tree2.val) {n n hv h hj
            return false;
        }
        return traverseAndCheck(tree1.right, tree2.right);
    }*/
    //iterative check
    private boolean traverseAndCheck(TreeNode tree1, TreeNode tree2) {
        //Iterative (using Morris traversal)
        /*
        TreeNode pCurr = p;
        TreeNode qCurr = q;
        while (pCurr != null) {
            if (qCurr == null) {
                return false;
            }
            if (pCurr.val != qCurr.val) {
                return false;
            }
            if (pCurr.left == null) {
                if (qCurr.left != null) {
                    return false;
                }
                pCurr = pCurr.right;
                qCurr = qCurr.right;
            } else {
                if (qCurr.left == null) {
                    return false;
                }
                TreeNode prevP = pCurr.left;
                TreeNode prevQ = qCurr.left;
                while (prevP.right != null && prevP.right != pCurr) {
                    if (prevQ.right == null || prevQ.right == qCurr) {
                        return false;
                    }
                    if (prevP.val != prevQ.val) {
                        return false;
                    }
                    prevP = prevP.right;
                    prevQ = prevQ.right;
                }
                if (prevQ.right != null && prevQ.right != qCurr) {
                    return false;
                }
                if (prevP.val != prevQ.val) {
                    return false;
                }
                if (prevP.right == null) {
                    prevP.right = pCurr;
                    prevQ.right = qCurr;
                    pCurr = pCurr.left;
                    qCurr = qCurr.left;
                } else {
                    prevP.right = null;
                    prevQ.right = null;
                    pCurr = pCurr.right;
                    qCurr = qCurr.right;
                }
            }
        }
        return qCurr == null;
        */

        TreeNode currTree1 = tree1;
        TreeNode currTree2 = tree2;
        while (currTree1 != null && currTree2 != null) {
            if (currTree1.left == null && currTree2.left == null) {
                if (currTree1.val != currTree2.val) {
                    return false;
                }
                currTree1 = currTree1.right;
                currTree2 = currTree2.right;
            } else if (currTree1.left == null || currTree2.left == null) {
                return false;
            } else {
                TreeNode tempTree1 = currTree1.left;
                TreeNode tempTree2 = currTree2.left;
                while (tempTree1.right != null && tempTree1.right != currTree1) {
                    tempTree1 = tempTree1.right;
                }
                while (tempTree2.right != null && tempTree2.right != currTree2) {
                    tempTree2 = tempTree2.right;
                }
                if (tempTree1.right == null) {
                    tempTree1.right = currTree1;
                    currTree1 = currTree1.left;
                } else {
                    tempTree1.right = null;
                    currTree1 = currTree1.right;
                }
                if (tempTree2.right == null) {
                    tempTree2.right = currTree2;
                    currTree2 = currTree2.left;
                } else {
                    tempTree2.right = null;
                    currTree2 = currTree2.right;
                }
                if (currTree1 != null && currTree2 != null) {
                    if (currTree1.val != currTree2.val) {
                        return false;
                    }
                }
            }
        }
        return currTree1 == currTree2;
    }
    private boolean isSameTree(TreeNode p, TreeNode q) {
        return traverseAndCheck(p, q);
    }
}
