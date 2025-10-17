package problems.self.tree.binarytree.traversal;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTreePostOrderTraversal {
    public static void main(String[] args) {
        BinaryTreePostOrderTraversal obj = new BinaryTreePostOrderTraversal();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(5,4,8,11,null,13,4,7,2,null,null,null,1)), "lo");
        List<Integer> postOrderTreeList = obj.postorderTraversal(tree);
        System.out.print("Postorder traversed tree: ");
        for (int i = 0; i < postOrderTreeList.size(); i++) {
            System.out.print(postOrderTreeList.get(i));
            if (i != postOrderTreeList.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    //Recursive approach [Time - O(n), Space - O(h)]
    /*public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversePostOrder(root, result);
        return result;
    }

    private void traversePostOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        traversePostOrder(root.left, result);
        traversePostOrder(root.right, result);
        result.add(root.val);
    }*/

    //Iterative approach (Morris Traversal) [Time - O(n), Space - O(1) / O(n) since list will be needed to reverse the order]
    /*public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if (current.right == null) {
                result.add(current.val);
                current = current.left;
            } else {
                TreeNode previous = current.right;
                while (previous.left != null && previous.left != current) {
                    previous = previous.left;
                }
                if (previous.left == null) {
                    previous.left = current;
                    result.add(current.val);
                    current = current.right;
                } else {
                    previous.left = null;
                    current = current.left;
                }
            }
        }
        return result.reversed();
    }*/

    //Iterative approach (Updated Morris Traversal) [Time - O(3n) or O(n), Space - O(1)]
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        TreeNode initialCurrent = new TreeNode(-1);
        TreeNode current = initialCurrent;
        current.left = root;
        while (current != null) {
            if (current.left == null) {
                current = current.right;
            } else {
                TreeNode previous = current.left;
                while (previous.right != null && previous.right != current) {
                    previous = previous.right;
                }
                if (previous.right == null) {
                    previous.right = current;
                    current = current.left;
                } else {
                    previous.right = null;
                    TreeNode successor = current, prev = null, temp;
                    current = current.left;
                    while (current != null) {
                        temp = current.right;
                        current.right = prev;
                        prev = current;
                        current = temp;
                    }
                    while (prev != null) {
                        result.add(prev.val);
                        temp = prev.right;
                        prev.right = current;
                        current = prev;
                        prev = temp;
                    }
                    current = successor;
                    current = current.right;
                }
            }
        }
        initialCurrent.left = null;
        return result;
    }

    //Using map. Iterative approach [Time - O(n), Space - O(n)]
    /*public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        HashMap<TreeNode, TreeNode> treeMap = new HashMap<>();
        treeMap.put(root, null);
        TreeNode current = root;
        while (current != null) {
            if (current.left != null && !treeMap.containsKey(current.left)) {
                treeMap.put(current.left, current);
                current = current.left;
            } else if (current.right != null && !treeMap.containsKey(current.right)) {
                treeMap.put(current.right, current);
                current = current.right;
            } else {
                result.add(current.val);
                current = treeMap.get(current);
            }
        }
        return result;
    }*/
}
