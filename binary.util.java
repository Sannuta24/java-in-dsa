
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {

    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class ExtendedTreeDSA {

    private TreeNode root;

    public ExtendedTreeDSA() {
        this.root = null;
    }

    // Insert a node in the binary search tree
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    // Search for a node in the binary search tree
    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(TreeNode root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }
        if (data < root.data) {
            return searchRec(root.left, data);
        }
        return searchRec(root.right, data);
    }

    // Delete a node in the binary tree
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private TreeNode deleteRec(TreeNode root, int data) {
        if (root == null) {
            return root;
        }
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = findMin(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    // Find the minimum value in the tree
    private int findMin(TreeNode root) {
        int minValue = root.data;
        while (root.left != null) {
            root = root.left;
            minValue = root.data;
        }
        return minValue;
    }

    // Find the maximum value in the tree
    private int findMax(TreeNode root) {
        int maxValue = root.data;
        while (root.right != null) {
            root = root.right;
            maxValue = root.data;
        }
        return maxValue;
    }

    // Inorder Traversal
    public void inorder() {
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Preorder Traversal
    public void preorder() {
        System.out.print("Preorder: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Postorder Traversal
    public void postorder() {
        System.out.print("Postorder: ");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Level Order Traversal
    public void levelOrder() {
        System.out.print("Level Order: ");
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.data + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println();
    }

    // Calculate the height of the tree
    public int height() {
        return heightRec(root);
    }

    private int heightRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(heightRec(root.left), heightRec(root.right)) + 1;
    }

    // Count the total number of nodes in the tree
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }

    // Check if the tree is a valid Binary Search Tree
    public boolean isValidBST() {
        return isValidBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBSTRec(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.data <= min || root.data >= max) {
            return false;
        }
        return isValidBSTRec(root.left, min, root.data) && isValidBSTRec(root.right, root.data, max);
    }

    // Main method for testing
    public static void main(String[] args) {
        ExtendedTreeDSA tree = new ExtendedTreeDSA();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Binary Tree Operations:");
        while (true) {
            System.out.println("\n1. Insert Node");
            System.out.println("2. Delete Node");
            System.out.println("3. Search Node");
            System.out.println("4. Inorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Postorder Traversal");
            System.out.println("7. Level Order Traversal");
            System.out.println("8. Height of Tree");
            System.out.println("9. Count Nodes");
            System.out.println("10. Validate BST");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter value to insert: ");
                    int value = scanner.nextInt();
                    tree.insert(value);
                }
                case 2 -> {
                    System.out.print("Enter value to delete: ");
                    int value = scanner.nextInt();
                    tree.delete(value);
                }
                case 3 -> {
                    System.out.print("Enter value to search: ");
                    int value = scanner.nextInt();
                    System.out.println(tree.search(value) ? "Found" : "Not Found");
                }
                case 4 ->
                    tree.inorder();
                case 5 ->
                    tree.preorder();
                case 6 ->
                    tree.postorder();
                case 7 ->
                    tree.levelOrder();
                case 8 ->
                    System.out.println("Height of tree: " + tree.height());
                case 9 ->
                    System.out.println("Total nodes: " + tree.countNodes());
                case 10 ->
                    System.out.println(tree.isValidBST() ? "Tree is a valid BST." : "Tree is not a valid BST.");
                case 11 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default ->
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
