
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

public class TreeDSA {

    private TreeNode root;

    public TreeDSA() {
        this.root = null;
    }

    // Insert a node in the binary tree
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

    // Search for a node in the binary tree
    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(TreeNode root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        } else if (data < root.data) {
            return searchRec(root.left, data);
        } else {
            return searchRec(root.right, data);
        }
    }

    // Delete a node from the binary tree
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
            // Node with one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Node with two children: Get the inorder successor
            root.data = findMin(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    // Find minimum value in a tree
    private int findMin(TreeNode root) {
        int minValue = root.data;
        while (root.left != null) {
            root = root.left;
            minValue = root.data;
        }
        return minValue;
    }

    // Find maximum value in a tree
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

    // Level Order Traversal (Breadth First)
    public void levelOrder() {
        System.out.print("Level Order: ");
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.data + " ");
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
        System.out.println();
    }

    // Find height of the tree
    public int height() {
        return heightRec(root);
    }

    private int heightRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = heightRec(root.left);
        int rightHeight = heightRec(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Count total nodes in the tree
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }

    // Main method for testing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeDSA tree = new TreeDSA();
        System.out.println("Tree Operations:");

        while (true) {
            System.out.println("\n1. Insert\n2. Delete\n3. Search\n4. Inorder Traversal");
            System.out.println("5. Preorder Traversal\n6. Postorder Traversal\n7. Level Order Traversal");
            System.out.println("8. Height\n9. Count Nodes\n10. Exit");
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
                    System.out.println("Height of the tree: " + tree.height());
                case 9 ->
                    System.out.println("Total nodes in the tree: " + tree.countNodes());
                case 10 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default ->
                    System.out.println("Invalid choice!");
            }
        }
    }
}
