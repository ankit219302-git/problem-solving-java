package problems.self.tree.binarytree.binarysearchtree.avl;

import problems.self.tree.binarytree.binarysearchtree.BST;

import java.util.List;

public class AVLTree extends BST {
    public AVLTree() {
        super();
    }

    public AVLTree(List<Integer> nodeValues) {
        super(nodeValues);
    }

    @Override
    public void deleteNode(int nodeVal) {
        super.deleteNode(nodeVal);
        rebalanceBSTPostAddition();
    }

    @Override
    public void addNode(int nodeVal) {
        super.addNode(nodeVal);
        rebalanceBSTPostDeletion();
    }

    public void rebalanceBSTPostAddition() {
    }

    private void rebalanceBSTPostDeletion() {
    }
}
