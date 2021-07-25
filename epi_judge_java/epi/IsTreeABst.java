package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return check(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean check(BinaryTreeNode<Integer> tree, int min, int max) {
    if (tree == null) {
      return true;
    }

    if (tree.data < min || tree.data > max) {
      return false;
    }

    if (!check(tree.left, min, tree.data)) {
      return false;
    }

    if (!check(tree.right, tree.data, max)) {
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
