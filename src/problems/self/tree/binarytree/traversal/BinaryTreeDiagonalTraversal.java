package problems.self.tree.binarytree.traversal;

import ds.TreeNode;
import util.TreeUtil;

import java.util.*;

public class BinaryTreeDiagonalTraversal {
    public static void main(String[] args) {
        BinaryTreeDiagonalTraversal obj = new BinaryTreeDiagonalTraversal();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(3,9,20,13,null,15,7,null,8,null,11,16,17,19,10,null,null,18,null,null,19)), "lo");
        List<Integer> diagonalTraversedTreeList = obj.diagonalTraversal(tree);
        System.out.print("Diagonal traversed tree: ");
        for (int i = 0; i < diagonalTraversedTreeList.size(); i++) {
            System.out.print(diagonalTraversedTreeList.get(i));
            if (i != diagonalTraversedTreeList.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public List<Integer> diagonalTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> diagonalTreeLevelMap = new LinkedHashMap<>();
        diagonalRecursiveTraversal(root, 0, diagonalTreeLevelMap);
        for (Map.Entry<Integer, List<Integer>> entry : diagonalTreeLevelMap.entrySet()) {
            result.addAll(entry.getValue());
        }
        return result;
    }

    private void diagonalRecursiveTraversal(TreeNode root, int level, Map<Integer, List<Integer>> diagonalTreeLevelMap) {
        if (root == null) {
            return;
        }
        if (diagonalTreeLevelMap.containsKey(level)) {
            diagonalTreeLevelMap.get(level).add(root.val);
        } else {
            diagonalTreeLevelMap.put(level, new ArrayList<>(Arrays.asList(root.val)));
        }
        diagonalRecursiveTraversal(root.left, level+1, diagonalTreeLevelMap);
        diagonalRecursiveTraversal(root.right, level, diagonalTreeLevelMap);
    }
}
