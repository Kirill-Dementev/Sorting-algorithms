package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

public class TreeSort implements SortAlgorithm {
    private long comparisons = 0;
    private long swaps = 0;
    private int index = 0;

    private static final class AVLTreeNode {
        int value;
        int height;
        AVLTreeNode left;
        AVLTreeNode right;

        AVLTreeNode(int item) {
            value = item;
            height = 1;
        }
    }

    @Override
    public InformationSort sort(int[] array) {
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        AVLTreeNode root = null;
        for (int value : array) {
            root = insert(root, value);
        }
        index = 0;
        inorderRec(root, array);

        long endTime = System.nanoTime();
        long memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort(comparisons, swaps, endTime - startTime, memory);
    }

    private AVLTreeNode insert(AVLTreeNode node, int key) {
        if (node == null) {
            return new AVLTreeNode(key);
        }

        comparisons++;
        if (key < node.value) {
            node.left = insert(node.left, key);
        } else if (key > node.value) {
            node.right = insert(node.right, key);
        } else {
            node.right = insert(node.right, key);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1) {
            if (node.left != null && key < node.left.value) {
                return rotateRight(node);
            }
            if (node.left != null && key > node.left.value) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (node.right != null && key > node.right.value) {
                return rotateLeft(node);
            }
            if (node.right != null && key < node.right.value) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private int height(AVLTreeNode node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(AVLTreeNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private AVLTreeNode rotateRight(AVLTreeNode prevRoot) {
        if (prevRoot == null || prevRoot.left == null) {
            return prevRoot;
        }

        swaps++;
        AVLTreeNode newRoot = prevRoot.left;
        AVLTreeNode temp = newRoot.right;

        newRoot.right = prevRoot;
        prevRoot.left = temp;

        prevRoot.height = Math.max(height(prevRoot.left), height(prevRoot.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    private AVLTreeNode rotateLeft(AVLTreeNode prevRoot) {
        if (prevRoot == null || prevRoot.right == null) {
            return prevRoot;
        }

        swaps++;
        AVLTreeNode newRoot = prevRoot.right;
        AVLTreeNode temp = newRoot.left;

        newRoot.left = prevRoot;
        prevRoot.right = temp;

        prevRoot.height = Math.max(height(prevRoot.left), height(prevRoot.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    private void inorderRec(AVLTreeNode node, int[] arr) {
        if (node != null) {
            inorderRec(node.left, arr);
            arr[index++] = node.value;
            inorderRec(node.right, arr);
        }
    }
}