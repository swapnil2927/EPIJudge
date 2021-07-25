package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class NumberOfTraversalsStaircase {
  @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")

  public static int numberOfWaysToTop(int top, int maximumStep) {
    int[] dp = new int[top+1];
    Arrays.fill(dp, -1);
    return countWays(top, maximumStep, dp);
  }

  private static int countWays(int top, int maximumStep, int[] dp) {
    if (top < 0) {
      return 0;
    }

    if (top == 0) {
      return 1;
    }

    if (dp[top] != -1) {
      return dp[top];
    }

    int ans = 0;
    for (int i=1; i<=maximumStep; i++) {
      if (i<=top) {
        ans += countWays(top-i,maximumStep,dp);
      }
    }
    dp[top] = ans;
    return dp[top];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
