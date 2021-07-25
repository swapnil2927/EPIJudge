package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {
  private static boolean isHeightBalanced;
  @EpiTest(testDataFile = "is_tree_balanced.tsv")



  public static boolean isBalanced(BinaryTreeNode<Integer> root) {
    isHeightBalanced = true;
    if (root == null) {
      return true;
    }
    int[] heights = getHeights(root);
    if ((Math.abs(heights[1] - heights[0])) >= 2) {
      isHeightBalanced = false;
    }
    return isHeightBalanced;
  }

  private static int[] getHeights(BinaryTreeNode<Integer> tree) {
    if (tree.left == null && tree.right == null) {
      return new int[] {1, 1};
    }

    if (tree.left != null && tree.right == null) {
      int[] leftHeights = getHeights(tree.left);
      int leftSubTreeHeight = Math.max(leftHeights[0], leftHeights[1]);
      if (leftSubTreeHeight >= 2) {
        isHeightBalanced = false;
      }
      return new int[] {leftSubTreeHeight + 1, 1};
    }

    if (tree.left == null && tree.right != null) {
      int[] rightHeights = getHeights(tree.right);
      int rightSubTreeHeight = Math.max(rightHeights[0], rightHeights[1]);
      if (rightSubTreeHeight >= 2) {
        isHeightBalanced = false;
      }
      return new int[] {1, rightSubTreeHeight + 1};
    }
    int[] leftHeights = getHeights(tree.left);
    int[] rightHeights = getHeights(tree.right);
    int leftSubTreeHeight = Math.max(leftHeights[0], leftHeights[1]);
    int rightSubTreeHeight = Math.max(rightHeights[0], rightHeights[1]);
    if ((Math.abs(rightSubTreeHeight - leftSubTreeHeight)) >= 2) {
      isHeightBalanced = false;
    }
    return new int[] {leftSubTreeHeight + 1, rightSubTreeHeight + 1};
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
