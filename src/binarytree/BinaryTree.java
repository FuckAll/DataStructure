package binarytree;

import java.util.*;

/**
 * @author: izgnod.
 * @date: 14/03/2020
 * @description:
 */
public class BinaryTree {

    private TreeNode root;
    private List<Integer> preOrderList = new ArrayList<>();
    private List<Integer> inOrderList = new ArrayList<>();
    private List<Integer> postOrderList = new ArrayList<>();
    private List<Integer> levelOrderList = new ArrayList<>();

    public class TreeNode {
        public int data;
        public TreeNode leftChild;
        public TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public BinaryTree() {
        root = null;
    }

    /**
     * 构建二叉树
     *
     * @param node , data
     * @return
     */
    public void createBinaryTree(TreeNode node, int data) {
        if (root == null) {
            root = new TreeNode(data);
        } else {
            if (data < node.data) {
                if (node.leftChild == null) {
                    node.leftChild = new TreeNode(data);
                } else {
                    createBinaryTree(node.leftChild, data);
                }
            } else {
                if (node.rightChild == null) {
                    node.rightChild = new TreeNode(data);
                } else {
                    createBinaryTree(node.rightChild, data);
                }
            }
        }
    }

    private static boolean towListSame(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 前序遍历(递归)
     *
     * @param node
     */
    public void preOrderRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrderList.add(node.data);
        preOrderRecursion(node.leftChild);
        preOrderRecursion(node.rightChild);
    }

    /**
     * 前序遍历(迭代)
     *
     * @param node
     */
    public void preOrderIteration(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                preOrderList.add(node.data);
                stack.push(node);
                node = node.leftChild;
            }

            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                node = pop.rightChild;
            }
        }
    }

    /**
     * 中序遍历(递归)
     *
     * @param node
     */
    public void inOrderRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderRecursion(node.leftChild);
        inOrderList.add(node.data);
        inOrderRecursion(node.rightChild);
    }

    /**
     * 中序遍历(迭代)
     *
     * @param node
     */
    public void inOrderIteration(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                inOrderList.add(pop.data);
                node = pop.rightChild;
            }
        }
    }

    /**
     * 后序遍历(递归)
     *
     * @param node
     */
    public void postOrderRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderRecursion(node.leftChild);
        postOrderRecursion(node.rightChild);
        postOrderList.add(node.data);
    }

    /**
     * 后序遍历(迭代)
     *
     * @param node
     */
    public void postOrderIteration(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisit = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                if (node.rightChild == null || node.rightChild == lastVisit) {
                    postOrderList.add(node.data);
                    lastVisit = node;
                    node = null;
                } else {
                    stack.push(node);
                    node = node.rightChild;
                }
            }
        }
    }

    /**
     * 层级遍历
     *
     * @param node
     */
    public void levelOrder(TreeNode node) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode pop = queue.pop();
            levelOrderList.add(pop.data);
            if (pop.leftChild != null) {
                queue.add(pop.leftChild);
            }
            if (pop.rightChild != null) {
                queue.add(pop.rightChild);
            }
        }
    }


    public static void main(String[] args) {
        /*   10
            /  \
           7    13
          / \   / \
         6   8  11 14
         */
        int[] a = {10, 7, 6, 8, 13, 11, 14};
        BinaryTree binaryTree = new BinaryTree();
        for (int value : a) {
            binaryTree.createBinaryTree(binaryTree.root, value);
        }

        // 先序遍历 -- 递归
        binaryTree.preOrderRecursion(binaryTree.root);
        List<Integer> expectPreOrderResult = Arrays.asList(10, 7, 6, 8, 13, 11, 14);
        if (!towListSame(expectPreOrderResult, binaryTree.preOrderList)) {
            System.out.printf("先序遍历(递归)失败！, expect result: %s, but got: %s", expectPreOrderResult, binaryTree.preOrderList);
        }
        binaryTree.preOrderList = new ArrayList<>();

        // 先序遍历 -- 迭代
        binaryTree.preOrderIteration(binaryTree.root);
        if (!towListSame(expectPreOrderResult, binaryTree.preOrderList)) {
            System.out.printf("先序遍历(迭代)失败！, expect result: %s, but got: %s", expectPreOrderResult, binaryTree.preOrderList);
        }

        // 中序遍历 -- 递归
        binaryTree.inOrderRecursion(binaryTree.root);
        List<Integer> expectInOrderResult = Arrays.asList(6, 7, 8, 10, 11, 13, 14);
        if (!towListSame(expectInOrderResult, binaryTree.inOrderList)) {
            System.out.printf("中序遍历(递归)失败！, expect result: %s, but got: %s", expectInOrderResult, binaryTree.inOrderList);
        }
        binaryTree.inOrderList = new ArrayList<>();

        // 中序遍历 -- 迭代
        binaryTree.inOrderIteration(binaryTree.root);
        if (!towListSame(expectInOrderResult, binaryTree.inOrderList)) {
            System.out.printf("中序遍历(迭代)失败！, expect result: %s, but got: %s", expectInOrderResult, binaryTree.inOrderList);
        }

        /*   10
            /  \
           7    13
          / \   / \
         6   8  11 14
         */
        // 后序遍历 -- 递归
        binaryTree.postOrderRecursion(binaryTree.root);
        List<Integer> expectPostOrderResult = Arrays.asList(6, 8, 7, 11, 14, 13, 10);
        if (!towListSame(expectPostOrderResult, binaryTree.postOrderList)) {
            System.out.printf("后序遍历(递归)失败！, expect result: %s, but got: %s", expectPostOrderResult, binaryTree.postOrderList);
        }
        binaryTree.postOrderList = new ArrayList<>();

        // 后续遍历 -- 迭代
        binaryTree.postOrderIteration(binaryTree.root);
        if (!towListSame(expectPostOrderResult, binaryTree.postOrderList)) {
            System.out.printf("后序遍历(迭代)失败！, expect result: %s, but got: %s", expectPostOrderResult, binaryTree.postOrderList);
        }

        // 层级遍历
        binaryTree.levelOrder(binaryTree.root);
        List<Integer> expectLevelOrderResult = Arrays.asList(10, 7, 13, 6, 8, 11, 14);
        if (!towListSame(expectLevelOrderResult, binaryTree.levelOrderList)) {
            System.out.printf("层级遍历失败！, expect result: %s, but got: %s", expectLevelOrderResult, binaryTree.levelOrderList);
        }
    }
}