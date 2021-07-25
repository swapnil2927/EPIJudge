package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LevenshteinDistance {

  private static int lA = 0;
  private static int lB = 0;

  @EpiTest(testDataFile = "levenshtein_distance.tsv")


  public static int levenshteinDistance(String A, String B) {
    lA = A.length();
    lB = B.length();
    Integer[][] dp = new Integer[lA][lB];
    return findMinCount(A, B, 0,0,dp);
  }

  private static int findMinCount(String A, String B, int aIn, int bIn, Integer[][] dp) {
    if (aIn == lA && bIn == lB) {
      return 0;
    }

    if (aIn == lA) {
      return lB - bIn;
    }

    if (bIn == lB) {
      return lA - aIn;
    }

    if (dp[aIn][bIn] != null) {
      return dp[aIn][bIn];
    }

    if (A.charAt(aIn) == B.charAt(bIn)) {
      dp[aIn][bIn] = findMinCount(A,B,aIn+1,bIn+1,dp);
    } else {
      int ans = 1 + findMinCount(A,B,aIn,bIn+1,dp);
      ans = Math.min(ans, 1 + findMinCount(A,B,aIn+1,bIn+1,dp));
      ans = Math.min(ans, 1 + findMinCount(A,B,aIn+1,bIn,dp));
      dp[aIn][bIn] = ans;
    }

    return dp[aIn][bIn];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
