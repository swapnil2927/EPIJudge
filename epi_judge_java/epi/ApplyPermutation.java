package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    if (A.isEmpty() || perm.size() != A.size()) {
      return;
    }
    boolean[] don = new boolean[A.size()];
    int dc = 0;
    while (dc < A.size()) {
      int start = -1;
      for (int i=0; i<A.size(); i++) {
        if (!don[i]) {
          start = i;
          break;
        }
      }
      int temp;
      int ptemp = A.get(start);
      int init = start;
      while (!don[init]) {
        temp = A.get(perm.get(start));
        A.set(perm.get(start), don[start] ? ptemp : A.get(start));
        start = perm.get(start);
        don[start] = true;
        dc++;
        ptemp = temp;
      }
    }
    return;
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
