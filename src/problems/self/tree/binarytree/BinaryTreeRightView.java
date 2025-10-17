package problems.self.tree.binarytree;

import ds.TreeNode;
import util.TreeUtil;

import java.util.*;

public class BinaryTreeRightView {
    public static void main(String[] args) {
        BinaryTreeRightView obj = new BinaryTreeRightView();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(3,9,20,13,null,15,7,null,8,null,11,16,17,19,10,null,null,18,null,null,null,null,null,21)), "lo");
        System.out.print("Right view of binary tree : ");
        List<Integer> rightViewTreeList = obj.treeRightView(tree);
        for (int i = 0; i < rightViewTreeList.size(); i++) {
            System.out.print(rightViewTreeList.get(i));
            if (i != rightViewTreeList.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public List<Integer> treeRightView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> rightViewNodeLevelMap = new LinkedHashMap<>();
        TreeNode curr = root;
        int level = 0;

        while (curr != null) {
            if (curr.left == null) {
                rightViewNodeLevelMap.put(level, curr.val);
                curr = curr.right;
                if (curr != null) {
                    level++;
                } else {
                    level--;
                }
            } else {
                TreeNode prev = curr.left;
                int tempLevel = 0;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                    tempLevel++;
                }
                if (prev.right == null) {
                    prev.right = curr;
                    rightViewNodeLevelMap.put(level, curr.val);
                    curr = curr.left;
                    level++;
                } else {
                    prev.right = null;
                    curr = curr.right;
                    level -= tempLevel + 1;
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : rightViewNodeLevelMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}