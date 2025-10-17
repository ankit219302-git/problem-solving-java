package problems.self.tree.binarytree.traversal;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.TreeUtil.isNotLeaf;

public class BinaryTreeBorderTraversal {
    public static void main(String[] args) {
        BinaryTreeBorderTraversal obj = new BinaryTreeBorderTraversal();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(1,null,4,13,5,null,3,null,7,null,10,null,13)), "lo");
        List<Integer> borderTraversedTreeList = obj.borderTraversal(tree);
        System.out.print("Border traversed tree: ");
        for (int i = 0; i < borderTraversedTreeList.size(); i++) {
            System.out.print(borderTraversedTreeList.get(i));
            if (i != borderTraversedTreeList.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public List<Integer> borderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (isNotLeaf(root)) {
            result.add(root.val);
        }
        collectLeftBoundary(getLeftStartNode(root), result);
        collectLeaves(root, result);
        collectRightBoundary(getRightStartNode(root), result);
        return result;
    }

    private TreeNode getLeftStartNode(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        return root.left;
    }

    private TreeNode getRightStartNode(TreeNode root) {
        while (isNotLeaf(root)) {
            if (root.right == null) {
                root = root.left;
                continue;
            }
            if (root.left == null) {
                root = root.right;
                continue;
            }
            break;
        }
        return root.right;
    }

    private void collectLeftBoundary(TreeNode curr, List<Integer> result) {
        if (curr == null) {
            return;
        }
        while (isNotLeaf(curr)) {
            result.add(curr.val);
            if (curr.left == null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
    }

    private void collectLeaves(TreeNode curr, List<Integer> result) {
        while (curr != null) {
            if (curr.left == null) {
                if (curr.right == null) {
                    result.add(curr.val);
                }
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    prev.right = null;
                    if (prev.left == null) {
                        result.add(prev.val);
                    }
                    curr = curr.right;
                }
            }
        }
    }

    private void collectRightBoundary(TreeNode curr, List<Integer> result) {
        if (curr == null) {
            return;
        }
        List<Integer> temp = new ArrayList<>();
        while (isNotLeaf(curr)) {
            temp.add(curr.val);
            if (curr.right == null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (temp.isEmpty()) {
            return;
        }
        result.addAll(temp.reversed());
    }
}
