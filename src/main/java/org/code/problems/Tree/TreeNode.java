package org.code.problems.Tree;

public class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        left = right =null;


    }
  // insert value in the treenode...
//  public void insert(int value) {
//      if (value < this.value) {
//          if (left == null) {
//              left = new TreeNode(value);
//          } else {
//              left.insert(value);
//          }
//      } else {
//          if (right == null) {
//              right = new TreeNode(value);
//          } else {
//              right.insert(value);
//          }
//      }
//  }
//  //write a main method to call this class with some random bst values
//    public static void main(String[] args) {
//      TreeNode root = new TreeNode(10);
//      root.insert(5);
//      root.insert(15);
//      root.insert(3);
//      root.insert(7);
//      root.insert(12);
//      root.insert(17);
//
//  }

}

