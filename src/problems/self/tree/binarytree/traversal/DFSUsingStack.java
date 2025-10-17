package problems.self.tree.binarytree.traversal;

import ds.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFSUsingStack {
    public static void main(String[] args) {
        DFSUsingStack obj = new DFSUsingStack();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(1,2,3,null,null,5,null,3,null,7,null,10,null,13)), "lo");
        System.out.print("DFS of binary tree (using Stack) : ");
        List<Integer> dfsTree = obj.getDFSNodesUsingStack(tree);
        for (int i = 0; i < dfsTree.size(); i++) {
            System.out.print(dfsTree.get(i));
            if (i != dfsTree.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    private List<Integer> getDFSNodesUsingStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> dfsNodes = new Stack<>();
        dfsNodes.push(root);

        while (!dfsNodes.isEmpty()) {
            TreeNode currentNode = dfsNodes.pop();
            result.add(currentNode.val);
            if (currentNode.right != null) {
                dfsNodes.push(currentNode.right);
            }
            if (currentNode.left != null) {
                dfsNodes.push(currentNode.left);
            }
        }
        return result;
    }
}
