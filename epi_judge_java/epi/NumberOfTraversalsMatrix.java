package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {
  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

  public static int numberOfWays(int n, int m) {
    Integer[][] dp = new Integer[n][m];

    return findMin(0, 0, n, m, dp);
  }

  private static int findMin(int xIn, int yIn, int n, int m, Integer[][] dp) {
    if (xIn == (n - 1) && yIn == (m - 1)) {
      return 1;
    }

    if (dp[xIn][yIn] != null) {
      return dp[xIn][yIn];
    }

    int numMoves = 0;
    if (xIn < (n - 1)) {
      numMoves += findMin(xIn + 1, yIn, n, m, dp);
    }

    if (yIn < (m - 1)) {
      numMoves += findMin(xIn, yIn + 1, n, m ,dp);
    }

    dp[xIn][yIn] = numMoves;
    return dp[xIn][yIn];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
