package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
    BstNode<Integer> start = tree;
    if (start == null) {
      return null;
    }

    BstNode<Integer> prev = null;
    while (start != null) {
      if (start.data < k) {
        start = start.right;
      } else if (start.data > k) {
        prev = start;
        start = start.left;
      } else {
        break;
      }
    }

    if (start == null) {
      return prev;
    }

    if(start.right != null) {
      start = start.right;
      while (start.left != null) {
        start = start.left;
      }
      return start;
    }

    return prev;
  }
  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
