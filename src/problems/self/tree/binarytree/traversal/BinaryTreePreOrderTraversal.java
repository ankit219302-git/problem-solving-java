package problems.self.tree.binarytree.traversal;

import ds.TreeNode;
import util.TreeUtil;

import java.util.*;

public class BinaryTreePreOrderTraversal {
    public static void main(String[] args) {
        BinaryTreePreOrderTraversal obj = new BinaryTreePreOrderTraversal();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(1,4,2,6,5,3,null,8,7,9,10,12,13)), "lo");
        List<Integer> preOrderTreeList = obj.preorderTraversal(tree);
        System.out.print("Preorder traversed tree: ");
        for (int i = 0; i < preOrderTreeList.size(); i++) {
            System.out.print(preOrderTreeList.get(i));
            if (i != preOrderTreeList.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    //Recursive approach [Time - O(n), Space - O(h)]
    /*public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversePreOrder(root, result);
        return result;
    }

    private void traversePreOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        traversePreOrder(root.left, result);
        traversePreOrder(root.right, result);
    }*/

    //Iterative approach (Morris Traversal) [Time - O(n), Space - O(1)]
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                TreeNode prev = current.left;
                while (prev.right != null && prev.right != current) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = current;
                    result.add(current.val);
                    current = current.left;
                } else {
                    prev.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }

    //Using map. Iterative approach [Time - O(n), Space - O(n)]
    /*public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        HashMap<TreeNode, TreeNode> treeMap = new HashMap<>();
        treeMap.put(root, null);
        TreeNode current = root;
        while (current != null) {
            if (!treeMap.containsKey(current.left) && !treeMap.containsKey(current.right)) {
                result.add(current.val);
            }
            if (current.left != null && !treeMap.containsKey(current.left)) {
                treeMap.put(current.left, current);
                current = current.left;
            } else if (current.right != null && !treeMap.containsKey(current.right)) {
                treeMap.put(current.right, current);
                current = current.right;
            } else {
                current = treeMap.get(current);
            }
        }
        return result;
    }*/
}
