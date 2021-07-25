package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class Combinations {
  @EpiTest(testDataFile = "combinations.tsv")

  public static List<List<Integer>> combinations(int n, int k) {

    if (k==0) {
      return Collections.singletonList(Collections.emptyList());
    }
    List<List<Integer>> ans = new ArrayList<>();
    for (int i=1; i<=n; i++) {
      populateAns(n, i, k, new ArrayList<Integer>(), ans);
    }
    return ans;
  }

  private static void populateAns(int n, int s, int k, List<Integer> cur, List<List<Integer>> ans) {
    cur.add(s);
    if (k == 1) {
      ans.add(new ArrayList<Integer>(cur));
    } else {
      for (int i=s+1; i<=(n - k + 2); i++) {
        populateAns(n, i, k-1, cur, ans);
      }
    }
    cur.remove(cur.size() - 1);
  }
  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Combinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
