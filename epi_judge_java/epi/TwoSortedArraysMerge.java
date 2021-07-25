package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    if (n == 0) {
      return;
    }

    int aIn = 0;
    int bIn = 0;
    int[] indexes = new int[m];
    while (aIn < m && bIn < n) {
      if (B.get(bIn) <= A.get(aIn)) {
        bIn++;
      } else {
        indexes[aIn] = bIn;
        aIn++;
      }
    }

    if (aIn < m) {
      indexes[aIn] = aIn;
      aIn++;
    }

    // First redistribute elements in A
    for (int i=m-1; i>=0; i--) {
      A.add(indexes[i], A.get(i));
    }

    int curIn = 0;
    int aPara = 0;
    for (int i=0; i<n; i++) {
      while (aPara < m && curIn == indexes[aPara]) {
        aPara++;
        curIn++;
      }
      A.add(curIn, B.get(i));
      curIn++;
    }

    for (int i=A.size() - (m+n); i>0; i--) {
      A.remove(A.size() - 1);
    }

    return;
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public TwoSortedArraysMerge() {
    super();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
