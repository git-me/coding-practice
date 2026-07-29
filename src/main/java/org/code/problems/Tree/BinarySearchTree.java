package org.code.problems.Tree;


/**
 * Inserting the First Node:
 *
 * You call insert(root, 10), where root is initially null. This creates a new node:
 * root = new TreeNode(10);  // Creates a node with value 10
 * root now refers to a TreeNode object in memory that holds the value 10.

 * root --> [ val: 10, left: null, right: null ]
 * Inserting the Second Node:
 * You call insert(root, 5). Since 5 < 10, it goes to the left subtree:
 * root.left = insert(root.left, 5);  // Inserts 5 in the left subtree
 * This creates a new TreeNode object for the value 5 and sets root.left to refer to this new node.
 * root --> [ val: 10, left --> [ val: 5, left: null, right: null ], right: null ]
 */
public class BinarySearchTree {

    TreeNode root;

    TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.value)
            root.left = insert(root.left, value);
        else if (value > root.value)
            root.right = insert(root.right, value);

        return root;
    }

    Boolean search(TreeNode root ,int val){
        if(root==null ) return false  ;
        if(root.value==val ) return true ;
        return val < root.value ? search(root.left, val) : search(root.right, val);
    }


    // Print the tree
    public void printTree() {
        printTree(root, "", true, -1);
    }

    // Helper method to recursively print the tree with labels for left and right
    private void printTree(TreeNode node, String prefix, boolean isRoot, int parentValue) {
        // Base case
        if (node == null) {
            return;
        }

        // Print current node value with "L" or "R" prefix
        String direction = parentValue != -1 ? (node.value < parentValue ? " L-- " : " R-- ") : "";
        System.out.println(prefix + (isRoot ? "└── [" + node.value + "]" : direction + node.value));

        // Prepare prefix for children nodes
        String childPrefix = prefix + (isRoot ? "    " : "│   ");

        // Print right child first
        printTree(node.right, childPrefix, false, node.value);

        // Print left child
        printTree(node.left, childPrefix, false, node.value);
    }


    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.root = bst.insert(bst.root, 389);
        bst.root = bst.insert(bst.root, 4);
        bst.root = bst.insert(bst.root, 33);
        bst.root = bst.insert(bst.root, 13);
        bst.root = bst.insert(bst.root, 0);
        Boolean b = bst.search(bst.root, 13);
//        bst.root= bst.insert( bst.root, 5);
//        bst.root = bst.insert(bst.root, 17);
//        bst.root = bst.insert(bst.root, 10);
//        bst.root = bst.insert(bst.root, 19);
//        bst.root = bst.insert(bst.root, 8);
//        bst.root = bst.insert(bst.root, 12);
//        bst.root = bst.insert(bst.root, 18);
//        bst.root = bst.insert(bst.root, 25);
//        bst.root = bst.insert(bst.root, 11);
//        bst.root = bst.insert(bst.root, 13);
        // Print the tree starting from the root

        // Print the tree starting from the root
        bst.printTree();
        System.out.println( "0000000000"+  b);

    }
}
