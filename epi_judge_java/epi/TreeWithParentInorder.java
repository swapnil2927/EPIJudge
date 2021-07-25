package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    if (tree == null) return Collections.emptyList();
    List<Integer> ans = new ArrayList<>();
    BinaryTree<Integer> root = tree;
    root = goToLeft(root);
    while (root != null) {
      ans.add(root.data);
      if (root.right != null) {
        root = root.right;
        root= goToLeft(root);
      } else {
        while (root.parent != null && root.parent.right == root) {
          root = root.parent;
        }
        root = root.parent;
      }
    }
    return ans;
  }

  private static BinaryTree<Integer> goToLeft(BinaryTree<Integer> root) {
    while(root.left != null) root = root.left;
    return root;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
