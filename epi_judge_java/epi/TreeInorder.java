package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreeInorder {

  private static class NodeAndState {
    public BinaryTreeNode<Integer> node;
    public Boolean leftSubtreeTraversed;

    public NodeAndState(BinaryTreeNode<Integer> node,
                        Boolean leftSubtreeTraversed) {
      this.node = node;
      this.leftSubtreeTraversed = leftSubtreeTraversed;
    }
  }

  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return Collections.emptyList();
    }
    List<Integer> ans = new ArrayList<>();
    BinaryTreeNode<Integer> start = tree;
    Deque<BinaryTreeNode<Integer>> q = new ArrayDeque<>();
    q.addFirst(start);
    while (start.left != null) {
      start = start.left;
      q.addFirst(start);
    }
    while (!q.isEmpty()) {
      BinaryTreeNode<Integer> cur = q.removeFirst();
      ans.add(cur.data);
      if (cur.right != null) {
        cur = cur.right;
        q.addFirst(cur);
        while (cur.left != null) {
          cur = cur.left;
          q.addFirst(cur);
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
