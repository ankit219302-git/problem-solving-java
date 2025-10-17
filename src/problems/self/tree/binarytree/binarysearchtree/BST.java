package problems.self.tree.binarytree.binarysearchtree;

import ds.TreeNode;
import util.TreeUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST {
    protected TreeNode node;

    public BST() {
        this(null);
    }

    public BST(List<Integer> nodeValues) {
        this.node = TreeUtil.createBST(nodeValues);
    }

    public void addNode(int nodeVal) {
        System.out.println("----Adding new node with value " + nodeVal + " in BST----");
        if (this.node == null) {
            this.node = new TreeNode(nodeVal);
            System.out.println("!!Node added successfully!!");
            return;
        }
        traverseTreeAndAddNode(nodeVal);
    }

    private void traverseTreeAndAddNode(int nodeVal) {
        boolean nodeAdded = false;
        TreeNode temp = this.node;
        while (!nodeAdded) {
            if (nodeVal == temp.val) {
                System.out.println("!!Node already present in BST. Addition skipped!!");
                return;
            }
            if (nodeVal < temp.val) {
                if (temp.left == null) {
                    temp.left = new TreeNode(nodeVal);
                    nodeAdded = true;
                    continue;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = new TreeNode(nodeVal);
                    nodeAdded = true;
                    continue;
                }
                temp = temp.right;
            }
        }
        System.out.println("!!Node added successfully!!");
    }

    public void print() {
        if (this.node == null) {
            System.out.println("!!BST doesn't exist!!");
            return;
        }
        System.out.println("----Printing BST in level order (with null values)----");
        Queue<TreeNode> bstQueue = new LinkedList<>();
        bstQueue.add(this.node);
        while (!bstQueue.isEmpty()) {
            TreeNode currNode = bstQueue.poll();
            if (currNode == null) {
                System.out.print(", null");
                continue;
            }
            if (currNode != this.node) {
                System.out.print(", ");
            }
            System.out.print(currNode.val);
            bstQueue.add(currNode.left);
            bstQueue.add(currNode.right);
        }
        System.out.println();
    }

    public void deleteNode(int nodeVal) {
        System.out.println("----Deleting node with value " + nodeVal + " from BST----");
        if (this.node == null) {
            System.out.println("!!BST doesn't exist!!");
            return;
        }
        TreeNode currentNode = this.node;
        TreeNode prevToDeletableNode = null;
        char direction = 'a';
        while (true) {
            if (currentNode == null) {
                System.out.println("!!Node not present in BST. Deletion skipped!!");
                return;
            }
            if (nodeVal == currentNode.val) {
                break;
            }
            prevToDeletableNode = currentNode;
            if (nodeVal < currentNode.val) {
                currentNode = currentNode.left;
                direction = 'l';
            } else {
                currentNode = currentNode.right;
                direction = 'r';
            }
        }
        deleteNode(currentNode, prevToDeletableNode, direction);
        System.out.println("!!Node deleted successfully!!");
    }

    private void deleteNode(TreeNode node, TreeNode prevToDeletableNode, char direction) {
        if (TreeUtil.isLeaf(node)) {
            deleteLeafNode(prevToDeletableNode, direction);
            return;
        }
        if (TreeUtil.isSkewed(node)) {
            deleteSkewedNode(node, prevToDeletableNode, direction);
            return;
        }
        //If the node to be deleted has both left and right subtrees,
        //one of two things can be done -
        //Either replace the deletable node val with the smallest value in the node's right subtree and delete that node.
        //Or, replace the deletable node val with the largest value in the node's left subtree and delete that.
        //Here we are replacing with the smallest node in right subtree and deleting that node.
        TreeNode temp = node.right;
        prevToDeletableNode = node;
        direction = 'r';
        while (temp.left != null) {
            prevToDeletableNode = temp;
            temp = temp.left;
        }
        if (prevToDeletableNode != node) {
            direction = 'l';
        }
        node.val = temp.val;
        deleteNode(temp, prevToDeletableNode, direction);
    }

    private void deleteSkewedNode(TreeNode node, TreeNode prevToDeletableNode, char direction) {
        if (prevToDeletableNode == null) {
            if (node.left != null) {
                this.node = node.left;
                node.left = null;
            } else {
                this.node = node.right;
                node.right = null;
            }
            return;
        }
        if (node.left != null) {
            if (direction == 'l') {
                prevToDeletableNode.left = node.left;
            }
            if (direction == 'r') {
                prevToDeletableNode.right = node.left;
            }
            node.left = null;
            return;
        }
        if (node.right != null) {
            if (direction == 'l') {
                prevToDeletableNode.left = node.right;
            }
            if (direction == 'r') {
                prevToDeletableNode.right = node.right;
            }
            node.right = null;
        }
    }

    private void deleteLeafNode(TreeNode prevToDeletableNode, char direction) {
        if (prevToDeletableNode == null) {
            this.node = null;
            return;
        }
        if (direction == 'l') {
            prevToDeletableNode.left = null;
            return;
        }
        if (direction == 'r') {
            prevToDeletableNode.right = null;
        }
    }
}
