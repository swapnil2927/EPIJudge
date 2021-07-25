package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PathSum {
  @EpiTest(testDataFile = "path_sum.tsv")

  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int rw) {
    return check(tree, 0, rw);
  }

  private static boolean check(BinaryTreeNode<Integer> tree, int cs, int es) {
    if (tree == null) return false;
    if (tree.left == null && tree.right == null && cs+tree.data == es) return true;
    boolean leftr = check(tree.left, cs+tree.data, es);
    if(leftr) return true;
    return check(tree.right, cs+tree.data, es);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
