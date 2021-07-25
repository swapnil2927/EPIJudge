package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return true;
    }
    return checkSym(root.left, root.right);
  }

  private static boolean checkSym(BinaryTreeNode<Integer> leftR, BinaryTreeNode<Integer> rightR) {
    if (leftR == null && rightR == null) {
      return true;
    }

    if (leftR == null || rightR == null) {
      return false;
    }

    if (leftR.data != rightR.data) {
      return false;
    }

    boolean ans1 = checkSym(leftR.right, rightR.left);
    if (!ans1) return false;
    boolean ans2 = checkSym(leftR.left, rightR.right);
    if (!ans2) return false;
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
