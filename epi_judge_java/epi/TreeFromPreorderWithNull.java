package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class TreeFromPreorderWithNull {
  public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preo) {
    int spareNullC = 0;
    Deque<BinaryTreeNode<Integer>> sparCh = new ArrayDeque<>();

    if (preo == null || preo.isEmpty()) {
      return null;
    }

    BinaryTreeNode<Integer> lastRoot = null;
    for (int i=preo.size()-1; i>=0; i--) {
      Integer cur = preo.get(i);
      BinaryTreeNode<Integer> curNode;
      if (cur == null) {
        curNode = new BinaryTreeNode<>(null);
      } else {
        curNode = new BinaryTreeNode<>(cur);
        lastRoot = curNode;
        curNode.left = sparCh.removeFirst();
        curNode.right = sparCh.removeFirst();
      }
      sparCh.addFirst(curNode);
    }

    return lastRoot;
  }
  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
