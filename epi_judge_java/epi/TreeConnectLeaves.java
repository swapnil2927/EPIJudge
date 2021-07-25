package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeConnectLeaves {

  public static List<BinaryTreeNode<Integer>>
  createListOfLeaves(BinaryTreeNode<Integer> tree) {
    return getLeaves(tree);
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
  @EpiTest(testDataFile = "tree_connect_leaves.tsv")
  public static List<Integer>
  createListOfLeavesWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> createListOfLeaves(tree));

    if (result.stream().anyMatch(x -> x == null || x.data == null)) {
      throw new TestFailure("Result can't contain null");
    }

    List<Integer> extractedRes = new ArrayList<>();
    for (BinaryTreeNode<Integer> x : result) {
      extractedRes.add(x.data);
    }
    return extractedRes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeConnectLeaves.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
