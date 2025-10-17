package util;

import ds.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class TreeUtil {
    public static boolean isNotLeaf(TreeNode node) {
        return node.left != null || node.right != null;
    }

    public static boolean isLeaf(TreeNode node) {
        return !isNotLeaf(node);
    }

    public static boolean isSkewed(TreeNode node) {
        return (isNotLeaf(node) && (node.left == null || node.right == null));
    }

    /***
     * @param nodeValues Node data to create a tree from
     * @param typeOfInput Type of input to create a tree from (lo: Level Order Input)
     * @return Binary Tree
     */
    public static TreeNode createBinaryTree(List<Integer> nodeValues, String typeOfInput) {
        if(typeOfInput.equalsIgnoreCase("lo")) {
            return createBinaryTreeFromLevelOrderInput(nodeValues);
        }
        return null;
    }

    /***
     * @param nodeValues Node data to create a tree from
     * @return Binary Search Tree
     */
    public static TreeNode createBST(List<Integer> nodeValues) {
        if (nodeValues == null || nodeValues.isEmpty()) {
            return null;
        }
        nodeValues = sanitizeListForBST(nodeValues);
        if (nodeValues.isEmpty()) {
            return null;
        }
        System.out.println("----Creating Binary Search Tree from Input----");
        TreeNode bst = createBST(0, nodeValues.size()-1, nodeValues);
        System.out.println("!!Binary Search Tree created successfully!!");
        return bst;
    }

    private static List<Integer> sanitizeListForBST(List<Integer> nodeValues) {
        nodeValues = new HashSet<>(nodeValues).stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
        //Instead of nodeValues.sort(Comparator.naturalOrder()), this can be used directly above -
        //nodeValues = new HashSet<>(nodeValues).stream().filter(Objects::nonNull).sorted().collect(Collectors.toCollection(ArrayList::new));
        nodeValues.sort(Comparator.naturalOrder());
        return nodeValues;
    }

    private static TreeNode createBST(int start, int end, List<Integer> nodeValues) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start)/2;
        TreeNode node = new TreeNode(nodeValues.get(mid));
        node.left = createBST(start, mid-1, nodeValues);
        node.right = createBST(mid+1, end, nodeValues);
        return node;
    }

    private static TreeNode createBinaryTreeFromLevelOrderInput(List<Integer> nodeValues) {
        if (nodeValues == null || nodeValues.isEmpty()) {
            return null;
        }
        TreeNode head = new TreeNode(nodeValues.getFirst());
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.add(head);
        int level = 1;
        int index = 1;
        while (index < nodeValues.size()) {
            for (int i = 1; i <= (int) Math.pow(2,level);) {
                if (index >= nodeValues.size()) {
                    break;
                }
                TreeNode left = null;
                if (nodeValues.get(index) == null) {
                    levelQueue.element().left = left;
                } else {
                    left = new TreeNode(nodeValues.get(index));
                    levelQueue.element().left = left;
                    levelQueue.add(left);
                }
                index++;
                if (index >= nodeValues.size()) {
                    break;
                }
                TreeNode right = null;
                if (nodeValues.get(index) == null) {
                    levelQueue.element().right = right;
                } else {
                    right = new TreeNode(nodeValues.get(index));
                    levelQueue.element().right = right;
                    levelQueue.add(right);
                }
                index++;
                i = i+2;
                levelQueue.remove();
            }
            level++;
        }
        return head;
    }
}
