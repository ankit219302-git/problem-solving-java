package problems.lc.easy;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
*/

public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        MinimumDepthOfBinaryTree obj = new MinimumDepthOfBinaryTree();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(3,9,20,13,null,15,7,null,8,null,11,16,17,19,10,null,null,18,null,null,19)), "lo");
        System.out.print("Minimum depth of given tree = " + obj.minDepth(tree));
    }
    private int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSubtreeDepth = minDepth(root.left);
        int rightSubtreeDepth = minDepth(root.right);
        if (leftSubtreeDepth == 0) {
            return 1 + rightSubtreeDepth;
        }
        if (rightSubtreeDepth == 0) {
            return 1 + leftSubtreeDepth;
        }
        return 1 + Math.min(leftSubtreeDepth, rightSubtreeDepth);

        /*TreeNode current = root;
        Map<TreeNode, TreeNode> treeMap = new HashMap<>();
        treeMap.put(current, null);
        int currMinDepth = 1, minDepth = 0;
        while (current != null) {
            if (current.left == null && current.right == null) {
                if (minDepth == 0 && currMinDepth >= 1) {
                    minDepth = currMinDepth;
                } else if (minDepth > 0) {
                    minDepth = Math.min(minDepth, currMinDepth);
                }
            }
            if (current.left != null && !treeMap.containsKey(current.left)) {
                treeMap.put(current.left, current);
                current = current.left;
                currMinDepth++;
            } else if (current.right != null && !treeMap.containsKey(current.right)) {
                treeMap.put(current.right, current);
                current = current.right;
                currMinDepth++;
            } else {
                current = treeMap.get(current);
                currMinDepth--;
            }
        }
        return minDepth;*/
    }
}
