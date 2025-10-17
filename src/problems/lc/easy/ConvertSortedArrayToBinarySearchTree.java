package problems.lc.easy;

import ds.TreeNode;
import problems.self.tree.binarytree.traversal.BinaryTreeInorderTraversal;

import java.util.List;

/*
Given an integer array nums where the elements are sorted in ascending order, convert it to a
height-balanced binary search tree.



Example 1:

Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:

Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
*/

public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree obj = new ConvertSortedArrayToBinarySearchTree();
        TreeNode tree = obj.sortedArrayToBST(new int[]{-10,-3,0,5,9});
        System.out.print("Converted binary search tree : ");
        BinaryTreeInorderTraversal treeTraversalObj = new BinaryTreeInorderTraversal();
        List<Integer> inOrderTreeList = treeTraversalObj.inorderTraversal(tree);
        for (int i = 0; i < inOrderTreeList.size(); i++) {
            System.out.print(inOrderTreeList.get(i));
            if (i != inOrderTreeList.size()-1) {
                System.out.print(", ");
            }
        }
    }
    private TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }
        return createBST(nums, 0, nums.length-1);
    }

    private TreeNode createBST(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(nums[mid]);
        root.left = createBST(nums, start, mid-1);
        root.right = createBST(nums, mid+1, end);
        return root;
    }
}
