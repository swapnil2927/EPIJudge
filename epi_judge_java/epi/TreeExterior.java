package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeExterior {

  public static List<BinaryTreeNode<Integer>>
  exteriorBinaryTree(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<BinaryTreeNode<Integer>> ans = new ArrayList<>();
    ans.add(root);
    if (root.left == null && root.right == null) {
      return ans;
    }

    List<BinaryTreeNode<Integer>> left = getLeft(root.left);
    List<BinaryTreeNode<Integer>> right = getRight(root.right);
    List<BinaryTreeNode<Integer>> leaf = getLeaves(root);
    ans.addAll(left);
    ans.addAll(leaf);
    Collections.reverse(right);
    ans.addAll(right);
    return ans;
  }

  private static List<BinaryTreeNode<Integer>> getLeft(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return Collections.emptyList();
    }

    List<BinaryTreeNode<Integer>> ans = new ArrayList<>();
    while (root.left != null || root.right != null) {
      if (root.left != null) {
        ans.add(root);
        root = root.left;
      } else {
        ans.add(root);
        root = root.right;
      }
    }
    return ans;
  }
  private static List<BinaryTreeNode<Integer>> getRight(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<BinaryTreeNode<Integer>> ans = new ArrayList<>();
    while (root.left != null || root.right != null) {
      if (root.right != null) {
        ans.add(root);
        root = root.right;
      } else {
        ans.add(root);
        root = root.left;
      }
    }
    return ans;
  }
  private static List<BinaryTreeNode<Integer>> getLeaves(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return Collections.emptyList();
    }

    if (root.left == null && root.right == null) {
      return List.of(root);
    }

    List<BinaryTreeNode<Integer>> leftList = getLeaves(root.left);
    List<BinaryTreeNode<Integer>> rightList = getLeaves(root.right);
    List<BinaryTreeNode<Integer>> ans = new ArrayList<>();
    ans.addAll(leftList);
    ans.addAll(rightList);
    return ans;
  }

  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
