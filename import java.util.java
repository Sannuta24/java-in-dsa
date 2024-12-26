
import java.util.Scanner;

public class ArrayAndLinkedList {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu for user choice
        System.out.println("Choose the data structure to operate on:");
        System.out.println("1. Array");
        System.out.println("2. Linked List");
        int choice = scanner.nextInt();

        if (choice == 1) {
            arrayOperations(scanner);
        } else if (choice == 2) {
            linkedListOperations(scanner);
        } else {
            System.out.println("Invalid choice!");
        }

        scanner.close();
    }

    // Array operations: Insert and Delete at specific positions
    private static void arrayOperations(Scanner scanner) {
        int[] array = new int[100]; // Array with max size 100
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("\nArray Operations:");
        System.out.println("1. Insert at Position");
        System.out.println("2. Delete at Position");
        int operation = scanner.nextInt();

        if (operation == 1) {
            System.out.print("Enter the position to insert (1 to " + (n + 1) + "): ");
            int pos = scanner.nextInt();
            System.out.print("Enter the value to insert: ");
            int value = scanner.nextInt();

            if (pos > 0 && pos <= n + 1) {
                // Shift elements to the right
                for (int i = n; i >= pos; i--) {
                    array[i] = array[i - 1];
                }
                array[pos - 1] = value; // Insert value
                n++; // Increment size

                System.out.println("Array after insertion:");
                for (int i = 0; i < n; i++) {
                    System.out.print(array[i] + " ");
                }
            } else {
                System.out.println("Invalid position!");
            }

        } else if (operation == 2) {
            System.out.print("Enter the position to delete (1 to " + n + "): ");
            int pos = scanner.nextInt();

            if (pos > 0 && pos <= n) {
                // Shift elements to the left
                for (int i = pos - 1; i < n - 1; i++) {
                    array[i] = array[i + 1];
                }
                n--; // Decrement size

                System.out.println("Array after deletion:");
                for (int i = 0; i < n; i++) {
                    System.out.print(array[i] + " ");
                }
            } else {
                System.out.println("Invalid position!");
            }
        } else {
            System.out.println("Invalid operation!");
        }
    }

    // Linked List operations: Insert and Delete at specific positions
    private static void linkedListOperations(Scanner scanner) {
        LinkedList list = new LinkedList();

        System.out.println("\nLinked List Operations:");
        System.out.println("1. Insert at Position");
        System.out.println("2. Delete at Position");
        int operation = scanner.nextInt();

        if (operation == 1) {
            System.out.print("Enter the position to insert: ");
            int pos = scanner.nextInt();
            System.out.print("Enter the value to insert: ");
            int value = scanner.nextInt();

            list.insertAtPosition(pos, value);
            System.out.println("Linked List after insertion:");
            list.display();

        } else if (operation == 2) {
            System.out.print("Enter the position to delete: ");
            int pos = scanner.nextInt();

            list.deleteAtPosition(pos);
            System.out.println("Linked List after deletion:");
            list.display();

        } else {
            System.out.println("Invalid operation!");
        }
    }
}

// Node class for Linked List
class Node {

    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Linked List class with operations
class LinkedList {

    private Node head;

    // Insert a node at a specific position
    public void insertAtPosition(int position, int value) {
        Node newNode = new Node(value);

        if (position == 1) {
            // Insert at the beginning
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            for (int i = 1; i < position - 1 && temp != null; i++) {
                temp = temp.next;
            }

            if (temp != null) {
                newNode.next = temp.next;
                temp.next = newNode;
            } else {
                System.out.println("Position out of bounds!");
            }
        }
    }

    // Delete a node at a specific position
    public void deleteAtPosition(int position) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        if (position == 1) {
            // Delete the head node
            head = head.next;
        } else {
            Node temp = head;
            for (int i = 1; i < position - 1 && temp != null; i++) {
                temp = temp.next;
            }

            if (temp != null && temp.next != null) {
                temp.next = temp.next.next;
            } else {
                System.out.println("Position out of bounds!");
            }
        }
    }

    // Display the linked list
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
