package problems.self.tree.binarytree.traversal;

import ds.TreeNode;
import util.TreeUtil;

import java.util.*;

/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.



Example 1:

Input: root = [1,null,2,3]

Output: [1,3,2]


Example 2:

Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]

Output: [4,2,6,5,7,1,3,9,8]


Example 3:

Input: root = []

Output: []

Example 4:

Input: root = [1]

Output: [1]
*/

public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        BinaryTreeInorderTraversal obj = new BinaryTreeInorderTraversal();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(1,4,2,6,5,3,null,8,7,9,10,12,13)), "lo");
        List<Integer> inOrderTreeList = obj.inorderTraversal(tree);
        System.out.print("Inorder traversed tree: ");
        for (int i = 0; i < inOrderTreeList.size(); i++) {
            System.out.print(inOrderTreeList.get(i));
            if (i != inOrderTreeList.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    //Recursive approach [Time - O(n), Space - O(h)]
    /*private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverseInOrder(root, result);
        return result;
    }

    private void traverseInOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        traverseInOrder(root.left, result);
        result.add(root.val);
        traverseInOrder(root.right, result);
    }*/

    //Iterative approach (Morris Traversal) [Time - O(n), Space - O(1)]
    public List<Integer> inorderTraversal(TreeNode root) {
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
                    current = current.left;
                } else {
                    prev.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }
        return result;
    }

    //Iterative approach [Time - O(n), Space - O(h)]
    /*
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> treeStack = new Stack<>();
        treeStack.push(root);
        TreeNode poppedElem = null;
        HashSet<TreeNode> nodesToAvoid = new HashSet<>();
        while (!treeStack.isEmpty()) {
            if (nodesToAvoid.contains(treeStack.peek().left)) {
                if (treeStack.peek().right != null) {
                    nodesToAvoid.add(treeStack.peek());
                    TreeNode temp = treeStack.peek().right;
                    poppedElem = treeStack.pop();
                    result.add(poppedElem.val);
                    treeStack.push(temp);
                } else {
                    poppedElem = treeStack.pop();
                    result.add(poppedElem.val);
                    continue;
                }
            }
            if (treeStack.peek().left != poppedElem) {
                if (treeStack.peek().left != null) {
                    treeStack.push(treeStack.peek().left);
                    continue;
                }
            }
            if (treeStack.peek().right != poppedElem) {
                if (treeStack.peek().right != null) {
                    nodesToAvoid.add(treeStack.peek());
                    TreeNode temp = treeStack.peek().right;
                    poppedElem = treeStack.pop();
                    result.add(poppedElem.val);
                    treeStack.push(temp);
                    continue;
                }
            }
            poppedElem = treeStack.pop();
            result.add(poppedElem.val);
        }
        return result;
    }
    */

    //Using Map. Iterative approach [Time - O(n), Space - O(n)]
    /*public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Map<TreeNode, TreeNode> treeMap = new HashMap<>();
        TreeNode current = root;
        treeMap.put(current, null);
        while (current != null) {
            if (current.left == null || (treeMap.containsKey(current.left) && !treeMap.containsKey(current.right))) {
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
