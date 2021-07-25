package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    Integer[][] dp = new Integer[2][finalScore+1];
    for (int i=0; i<2; i++) {
      dp[i][0] = 1;
    }

    for (int j=1; j<=finalScore; j++) {
      dp[0][j] = (j % individualPlayScores.get(0) == 0) ? 1 : 0;
    }

    for(int i=1; i<individualPlayScores.size(); i++) {
      for (int j=1; j<=finalScore; j++) {
        dp[i%2][j] = dp[(i-1)%2][j];
        if ((j - individualPlayScores.get(i)) >= 0) {
          dp[i%2][j] += dp[i%2][j - individualPlayScores.get(i)];
        }
      }
    }

    return dp[((individualPlayScores.size() - 1) % 2)][finalScore];
    //return numComRec(finalScore, individualPlayScores, 0, dp);
  }

  private static int numComRec(int score, List<Integer> pScs, int cIn, Integer[][] dp) {
    if (score == 0) {
      return 1;
    }

    if (cIn == pScs.size()) {
      return 0;
    }

    if (dp[cIn][score] != null) {
      return dp[cIn][score];
    }
    int ans = 0;
    int cPlSc = pScs.get(cIn);
    if (cPlSc <= score) {
      ans+= numComRec(score - cPlSc, pScs, cIn, dp);
    }
    ans+= numComRec(score, pScs, cIn + 1, dp);
    dp[cIn][score] = ans;
    return dp[cIn][score];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
