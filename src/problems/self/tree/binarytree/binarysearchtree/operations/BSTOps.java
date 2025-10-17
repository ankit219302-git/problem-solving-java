package problems.self.tree.binarytree.binarysearchtree.operations;

import problems.self.tree.binarytree.binarysearchtree.BST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BSTOps {
    private final BST binarySearchTree;

    public BSTOps() {
        this.binarySearchTree = createBST();
    }

    public static void main(String[] args) {
        BSTOps ops = new BSTOps();
        ops.printBST();
        int val = new Random().nextInt(10);
        ops.addNodeInBST(val);
        ops.printBST();
        ops.deleteNodeFromBST(val);
        ops.printBST();
    }

    private void deleteNodeFromBST(int nodeVal) {
        System.out.println();
        this.binarySearchTree.deleteNode(nodeVal);
    }

    private void printBST() {
        System.out.println();
        this.binarySearchTree.print();
    }

    private void addNodeInBST(int nodeVal) {
        System.out.println();
        this.binarySearchTree.addNode(nodeVal);
    }

    private BST createBST() {
        System.out.println();
        List<Integer> nodeValues = new ArrayList<>(Arrays.asList(1,2,7,11,null,3,5,6,8,10,99,-3,-15,4,7));
        return new BST(nodeValues);
    }
}
