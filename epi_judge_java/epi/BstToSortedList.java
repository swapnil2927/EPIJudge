package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class BstToSortedList {
  public static BstNode<Integer> bstToDoublyLinkedList(BstNode<Integer> root) {
    if (root == null) return root;
    BstNode<Integer>[] ends = transform(root);
    return ends[0];
  }

  private static BstNode<Integer>[] transform(BstNode<Integer> root) {
    if(root == null) return new BstNode[]{null, null};
    BstNode<Integer>[] leftEnds = transform(root.left);
    if(leftEnds[1] != null) {
      leftEnds[1].right = root;
      root.left = leftEnds[1];
    }
    BstNode<Integer>[] rightEnds = transform(root.right);
    if(rightEnds[0] != null) {
      rightEnds[0].left = root;
      root.right = rightEnds[0];
    }
    return new BstNode[] {leftEnds[0] != null ? leftEnds[0] : root, rightEnds[1] != null ? rightEnds[1] : root};
  }

  @EpiTest(testDataFile = "bst_to_sorted_list.tsv")
  public static List<Integer>
  bstToDoublyLinkedListWrapper(TimedExecutor executor, BstNode<Integer> tree)
      throws Exception {
    BstNode<Integer> list = executor.run(() -> bstToDoublyLinkedList(tree));

    if (list != null && list.left != null)
      throw new TestFailure(
          "Function must return the head of the list. Left link must be null");
    List<Integer> v = new ArrayList<>();
    while (list != null) {
      v.add(list.data);
      if (list.right != null && list.right.left != list)
        throw new RuntimeException("List is ill-formed");
      list = list.right;
    }
    return v;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstToSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
