package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class PickingUpCoins {
  @EpiTest(testDataFile = "picking_up_coins.tsv")

  public static int pickUpCoins(List<Integer> coins) {
    int[][] dp = new int[coins.size()][coins.size()];
    return findMax(coins, dp , 0, coins.size() - 1);
  }

  private static int findMax(List<Integer> coins, int[][] dp, int a, int b) {
    if (a > b) {
      return 0;
    }

    if (a == b) {
      return coins.get(a);
    }

    if (dp[a][b] != 0) {
      return dp[a][b];
    }

    int max1 = coins.get(a) + Math.min(findMax(coins, dp , a+2, b),
            findMax(coins, dp , a+1, b-1));
    int max2 = coins.get(b) + Math.min(findMax(coins, dp , a, b-2),
            findMax(coins, dp , a+1, b-1));
    dp[a][b] = Math.max(max1, max2);
    return dp[a][b];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PickingUpCoins.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
