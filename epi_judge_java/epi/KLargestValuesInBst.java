package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    List<Integer> kL = new ArrayList<>(k);
    popuKL(tree,k,kL,k);
    return kL;
  }

  private static int popuKL(BstNode<Integer> tree, int k, List<Integer> kL, int size) {
    if (tree == null || k == 0) {
      return k;
    }
    k = popuKL(tree.right, k, kL, size);
    if (k == 0) {
      return k;
    }
    kL.add(size - k, tree.data);
    k--;
    k = popuKL(tree.left, k, kL, size);
    return k;
  }
  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
