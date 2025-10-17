package problems.self.tree.binarytree.traversal;

import ds.TreeNode;
import util.TreeUtil;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal obj = new BinaryTreeLevelOrderTraversal();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(3,9,20,13,null,15,7,null,8,null,11,16,17)), "lo");
        List<Integer> levelOrderTreeList = obj.levelOrderTraversal(tree);
        System.out.print("Level order traversed tree: ");
        for (int i = 0; i < levelOrderTreeList.size(); i++) {
            System.out.print(levelOrderTreeList.get(i));
            if (i != levelOrderTreeList.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    //Iterative approach (Morris Traversal) [Time - O(n), Space - O(n)]
    /*public List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        Map<TreeNode, Integer> levelMap = new LinkedHashMap<>();
        levelMap.put(root, 0);
        while (current != null) {
            if (current.left == null) {
                if (current.right != null && !levelMap.containsKey(current.right)) {
                    levelMap.put(current.right, levelMap.get(current)+1);
                }
                current = current.right;
            } else {
                TreeNode prev = current.left;
                while (prev.right != null && prev.right != current) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = current;
                    if (!levelMap.containsKey(current.left)) {
                        levelMap.put(current.left, levelMap.get(current)+1);
                    }
                    current = current.left;
                } else {
                    prev.right = null;
                    if (current.right != null && !levelMap.containsKey(current.right)) {
                        levelMap.put(current.right, levelMap.get(current)+1);
                    }
                    current = current.right;
                }
            }
        }
        double currentTotalSize = 0;
        for (int i = 0; i < levelMap.size(); i++) {
            if (levelMap.size() == result.size()) {
                break;
            }
            currentTotalSize += Math.pow(2, i);
            for (Map.Entry<TreeNode, Integer> element : levelMap.entrySet()) {
                if (element.getValue() == i) {
                    result.add(element.getKey().val);
                }
                if (result.size() >= currentTotalSize) {
                    break;
                }
            }
        }
        return result;
    }*/

    //Iterative approach (Morris Traversal) [Time - O(n), Space - O(n)]
    public List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> mapOfLevels = new LinkedHashMap<>();
        TreeNode current  = root;
        int level = 0;
        while (current != null) {
            if (current.left == null) {
                if (mapOfLevels.containsKey(level)) {
                    mapOfLevels.get(level).add(current.val);
                } else {
                    mapOfLevels.put(level, new ArrayList<>(Arrays.asList(current.val)));
                }
                current = current.right;
                if (current != null) {
                    level++;
                } else {
                    level--;
                }
            } else {
                TreeNode prev = current.left;
                int tempLevel = 0;
                while (prev.right != null && prev.right != current) {
                    prev = prev.right;
                    tempLevel++;
                }
                if (prev.right == null) {
                    prev.right = current;
                    if (mapOfLevels.containsKey(level)) {
                        mapOfLevels.get(level).add(current.val);
                    } else {
                        mapOfLevels.put(level, new ArrayList<>(Arrays.asList(current.val)));
                    }
                    current = current.left;
                    level++;
                } else {
                    prev.right = null;
                    current = current.right;
                    level -= tempLevel + 1;
                }
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : mapOfLevels.entrySet()) {
            result.addAll(entry.getValue());
        }
        return result;
    }
}
