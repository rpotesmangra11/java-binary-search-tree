import java.util.Scanner;// We need scanner to read user inputs from the console in Java

/*This represents a node in the binary search tree (going forward referred to as tree). Data holds value of node. Create left/right node then intialize them as 'null' values */
class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

/*This class represents tree itself and contains methods to manipulate the tree, initialize root to 'null' */
class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    /*This will create the tree with the array of values provided from data, when choice 1 this array will be passed to create BST: {1001, 1003, 1005, 1007, 1009, 1011, 1013, 1015, 1017, 1019}. */
    void createBST(int[] data) {
        for (int value : data) {
            insert(value); 
        }
    }

    // Method to insert a node in the tree. (Option 2)
    void insert(int data) {
        Node newNode = new Node(data);
        if (root == null) { //If tree is empty, the new node will be root
            root = newNode;
            return;
        }

        //This will start us from the root and also keep track of the parent node
        Node current = root;
        Node parent = null;

        /*This loop is where we find the correct position to put the new node.*/
        while (true) {
            parent = current;
            if (data < current.data) { //If the value is < current node move to left subtree
                current = current.left;
                if (current == null) { //If that node is empty insert it there
                    parent.left = newNode;
                    return;
                }
            } else { //If it doesn't satisfy those it will end up here. That means it must be > so it will move to the right subtree.
                current = current.right; 
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    // Method to delete a node in the tree
    void delete(int data) {
        root = deleteIterative(root, data);
    }

    Node deleteIterative(Node root, int data) {
        if (root == null) { //If tree empty nothing to delete
            return root;
        }

        //This will start us from the root and also keep track of the parent node
        Node parent = null;
        Node current = root;

        /*This loop is where we find the correct position of the node to be deleted and also its parent. */
        while (current != null && current.data != data) {
            parent = current;
            if (data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return root;  // In case the deleted node is not found
        }

        // This if statement is for nodes that have 1 or no children nodes
        if (current.left == null || current.right == null) {
            Node newCurrent;

            if (current.left == null) { //Left child null means no left child
                newCurrent = current.right;
            } else { // If here it means that there is no right child so use left
                newCurrent = current.left;
            }

            // It could have no children so the root to be deleted could be the parent node
            if (parent == null) {
                return newCurrent;
            }

            if (current == parent.left) {
                parent.left = newCurrent;
            } else {
                parent.right = newCurrent;
            }
        } else {
            // For a node with two children we will get the smallest value in right subtree and then replace that nodes value with next data value
            Node successor = getMinimumKey(current.right);
            int val = successor.data;
            deleteIterative(root, successor.data);
            current.data = val;
        }

        return root;
    }

    // This will let us get the smallest value in a subtree
    Node getMinimumKey(Node curr) {
        // This while loop is used to find the leftmost leaf node
        while (curr.left != null) {
            curr = curr.left;
        }
        // This should return the lowest value
        return curr;
    }

    // Method for printing values of in-order traversal
    void printInOrder() {
        inOrderRec(root); //Call method below recursively for in-order method
        System.out.println();
    }

    /*Recursive method used for in-order traversal. We recursively check the nodes, visiting the subtrees left/right and printing the values */
    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.data + " ");
            inOrderRec(root.right);
        }
    }

    // Method to print nodes by pre-order traversal
    void printPreOrder() {
        preOrderRec(root); //Here we recursively call the pre-order method
        System.out.println();
    }

    /*Recursive method used for pre-order traversal. We recursively check the nodes, visiting the subtrees left/right and printing the values */
    void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Method to print nodes by post-order traversal
    void printPostOrder() {
        postOrderRec(root);
        System.out.println();
    }

    /*Recursive method used for post-order traversal. We recursively check the nodes, visiting the subtrees left/right and printing the values */
    void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.data + " ");
        }
    }
}

// The Main class is where the application is run from
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Read user input
        BinarySearchTree bst = new BinarySearchTree(); //Create a new tree
        int choice = 0; //The users choice variable is intialized to 0

        // Loop until the user chooses option 7 (EXIT)
        while (choice != 7) {
            System.out.println("_-==README==-_");
            System.out.println("Create a new tree by using option 1 or option 2 to add a node. Print your current tree by using options 4, 5, and 6. Caution: using 1 again after creating tree will reset your tree to default BST");
            // Display the menu options to the user
            System.out.println("_-==Menu==-_");
            System.out.println("(1) Create a binary search tree");
            System.out.println("(2) Add node");
            System.out.println("(3) Delete node");
            System.out.println("(4) Print BST by InOrder Traversal");
            System.out.println("(5) Print BST by PreOrder Traversal");
            System.out.println("(6) Print BST by PostOrder Traversal");
            System.out.println("(7) Get me out!");
            System.out.print("Choose wisely: ");

            // Read the user's input choice
            choice = scanner.nextInt();

            if (choice == 1) {
                // Create a binary search tree with predefined values from the study.com assignment
                int[] data = {1001, 1003, 1005, 1007, 1009, 1011, 1013, 1015, 1017, 1019};
                bst.createBST(data);
                System.out.println("Binary search tree created.");
            } else if (choice == 2) {
                // Add a new node to the binary search tree
                System.out.print("Enter value to add: ");
                int valueToAdd = scanner.nextInt();
                bst.insert(valueToAdd);
                System.out.println("Node added.");
            } else if (choice == 3) {
                // Delete a node from the binary search tree
                System.out.print("Enter value to delete: ");
                int valueToDelete = scanner.nextInt();
                bst.delete(valueToDelete);
                System.out.println("Node deleted.");
            } else if (choice == 4) {
                // Print nodes by in-order traversal
                System.out.println("InOrder traversal:");
                bst.printInOrder();
            } else if (choice == 5) {
                // Print nodes by pre-order traversal
                System.out.println("PreOrder traversal:");
                bst.printPreOrder();
            } else if (choice == 6) {
                // Print nodes by post-order traversal
                System.out.println("PostOrder traversal:");
                bst.printPostOrder();
            } else if (choice == 7) {
                // Get me out! (exit program)
                System.out.println("Poof. Gone in puff of smoke");
            } else {
                // This is in case the user doesn't make a choice between 1 - 7. Catch-all. 
                System.out.println("Please select a valid option between 1 and 7.");
            }
        }

        // Close the scanner
        scanner.close();
    }
}
