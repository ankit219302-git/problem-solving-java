package problems.self.tree.binarytree.traversal;

import ds.TreeNode;
import util.TreeUtil;

import java.util.*;

public class BFSUsingQueue {
    public static void main(String[] args) {
        BFSUsingQueue obj = new BFSUsingQueue();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(1,2,3,null,null,5,null,3,null,7,null,10,null,13)), "lo");
        System.out.print("BFS of binary tree (using Queue) : ");
        List<Integer> bfsTree = obj.getBFSNodesUsingQueue(tree);
        for (int i = 0; i < bfsTree.size(); i++) {
            System.out.print(bfsTree.get(i));
            if (i != bfsTree.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    private List<Integer> getBFSNodesUsingQueue(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> bfsNodes = new LinkedList<>();
        bfsNodes.add(root);

        while (!bfsNodes.isEmpty()) {
            TreeNode currentNode = bfsNodes.poll();
            result.add(currentNode.val);
            if (currentNode.left != null) {
                bfsNodes.add(currentNode.left);
            }
            if (currentNode.right != null) {
                bfsNodes.add(currentNode.right);
            }
        }
        return result;
    }
}
