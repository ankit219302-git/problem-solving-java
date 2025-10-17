package problems.self.tree.binarytree;

import ds.TreeNode;
import util.TreeUtil;

import java.util.*;

public class BinaryTreeTopView {
    public static void main(String[] args) {
        BinaryTreeTopView obj = new BinaryTreeTopView();
        TreeNode tree = TreeUtil.createBinaryTree(new ArrayList<>(Arrays.asList(1,2,3,null,null,5,null,3,null,7,null,10,null,13)), "lo");
        System.out.print("Top view of binary tree : ");
        List<Integer> topViewTreeList = obj.treeTopView(tree);
        for (int i = 0; i < topViewTreeList.size(); i++) {
            System.out.print(topViewTreeList.get(i));
            if (i != topViewTreeList.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public List<Integer> treeTopView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, Integer> topViewMap = new HashMap<>();
        Queue<NodeDepthPair> nodesWithDepth = new LinkedList<>();
        int hdMin = 0, hdMax = 0;
        nodesWithDepth.add(new NodeDepthPair(root, 0));

        while (!nodesWithDepth.isEmpty()) {
            NodeDepthPair currentNode = nodesWithDepth.poll();
            if (hdMin > currentNode.horizontalDepth) {
                hdMin = currentNode.horizontalDepth;
            }
            if (hdMax < currentNode.horizontalDepth) {
                hdMax = currentNode.horizontalDepth;
            }
            if (!topViewMap.containsKey(currentNode.horizontalDepth)) {
                topViewMap.put(currentNode.horizontalDepth, currentNode.node.val);
            }
            if (currentNode.node.left != null) {
                nodesWithDepth.add(new NodeDepthPair(currentNode.node.left, currentNode.horizontalDepth-1));
            }
            if (currentNode.node.right != null) {
                nodesWithDepth.add(new NodeDepthPair(currentNode.node.right, currentNode.horizontalDepth+1));
            }
        }
        while (hdMin <= hdMax) {
            result.add(topViewMap.get(hdMin));
            hdMin++;
        }
        return result;
    }
}

class NodeDepthPair {
    TreeNode node;
    int horizontalDepth;

    NodeDepthPair(TreeNode node, int horizontalDepth) {
        this.node = node;
        this.horizontalDepth = horizontalDepth;
    }
}