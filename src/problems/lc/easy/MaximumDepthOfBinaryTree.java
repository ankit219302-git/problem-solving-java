package problems.lc.easy;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:

Input: root = [1,null,2]
Output: 2
*/

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        MaximumDepthOfBinaryTree obj = new MaximumDepthOfBinaryTree();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(2,97,97,null,47,80,null,-7,null,null,-7)), "lo");
        System.out.print("Maximum depth of given tree = " + obj.maxDepth(tree));
    }
    private int maxDepth(TreeNode root) {
        //with recursion
        if(root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));

        /*
        if (root == null) {
            return 0;
        }
        HashMap<TreeNode, Integer> treeMap = new HashMap<>();
        TreeNode curr = root;
        treeMap.put(curr, 1);
        int maxDepth = 1;
        while (curr != null) {
            if (curr.left == null) {
                if (curr.right != null && !treeMap.containsKey(curr.right) && treeMap.containsKey(curr)) {
                    treeMap.put(curr.right, treeMap.get(curr)+1);
                }
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                TreeNode temp = curr;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    prev.right = null;
                    curr = curr.right;
                }
                if (!treeMap.containsKey(curr) && treeMap.containsKey(temp)) {
                    treeMap.put(curr, treeMap.get(temp)+1);
                }
            }
            if (curr != null && treeMap.get(curr) > maxDepth) {
                maxDepth = treeMap.get(curr);
            }
        }
        return maxDepth;
        */

        //without recursion
        /*HashMap<TreeNode, Integer> nodeDepthMap = new HashMap<>();
        TreeNode curr = root;
        int depth = 0;
        while (curr != null) {
            depth++;
            if (!nodeDepthMap.containsKey(curr)) {
                nodeDepthMap.put(curr, depth);
            } else {
                depth = nodeDepthMap.get(curr);
            }
            if (curr.left == null) {
                curr = curr.right;
            } else {
                TreeNode temp = curr.left;
                depth++;
                if (!nodeDepthMap.containsKey(temp)) {
                    nodeDepthMap.put(temp, depth);
                } else {
                    depth = nodeDepthMap.get(temp);
                }
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                    depth++;
                    if (!nodeDepthMap.containsKey(temp)) {
                        nodeDepthMap.put(temp, depth);
                    } else {
                        depth = nodeDepthMap.get(temp);
                    }
                }
                if (temp.right == null) {
                    temp.right = curr;
                    depth = nodeDepthMap.get(curr);
                    curr = curr.left;
                } else {
                    temp.right = null;
                    depth = nodeDepthMap.get(curr);
                    curr = curr.right;
                }
            }
        }
        for (Map.Entry<TreeNode, Integer> treeNodeDepthMap : nodeDepthMap.entrySet()) {
            if (treeNodeDepthMap.getValue() > depth) {
                depth = treeNodeDepthMap.getValue();
            }
        }
        return depth;*/
    }
}
