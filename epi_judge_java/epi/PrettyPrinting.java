package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
public class PrettyPrinting {
  @EpiTest(testDataFile = "pretty_printing.tsv")

  public static int minimumMessiness(List<String> ws, int ll) {
    int wz = ws.size();
    int[][] dp = new int[wz+1][wz+1];
    for (int i=1;i<=wz; i++) {
      Arrays.fill(dp[i], -1);
    }
    int mme = ll * ll;

    int twl = 0;
    for (String wsel : ws) {
      twl+= wsel.length();
    }

    int minr = twl/ll + 1;
    int maxr = wz;
    int minme = mme * wz;
    for (int i=minr; i<=maxr; i++) {
      int cmme = getMinMeForThisRowCount(i, ws, wz, ll, dp);
      minme = Math.min(minme, cmme);
    }
    return minme;
  }

  private static int getMinMeForThisRowCount(int numr, List<String> ws, int numw, int ll, int[][] dp) {
    if (numw < numr) {
      return -1;
    }
    if (numw == 0 && numr == 0) {
      return 0;
    }
    if (numr == 0 || numw == 0) {
      return -1;
    }
    if (dp[numw][numr] != -1) {
      return dp[numw][numr];
    }
    if (numw == numr) {
      // Each row will have 1 word. No other way
      int ans = 0;
      for (int i=0; i<numw; i++) {
        ans += (ll - ws.get(i).length())*(ll - ws.get(i).length());
      }
      dp[numw][numr] = ans;
    } else {
      // Try and fit some words in last row
      int lrl = 0;
      int mocl = 0;
      int ans = ll * ll * ws.size();
      for (int i=numw-1; i>=0; i--) {
        lrl += ws.get(i).length();
        if (lrl > ll) {
          // We can't add this word
          break;
        } else if (lrl < ll){
          // We can add this word. Add rem words in rem rows
          mocl =  (ll - lrl) * (ll - lrl);
          int remm = getMinMeForThisRowCount(numr-1, ws, i, ll, dp);
          if (remm != -1) {
            mocl += remm;
            ans = Math.min(ans, mocl);
          }
        } else {
          int remm = getMinMeForThisRowCount(numr-1, ws, i, ll, dp);
          if (remm != -1) {
            ans = Math.min(ans, getMinMeForThisRowCount(numr - 1, ws, i, ll, dp));
          }
          break;
        }
        lrl++;
      }
      dp[numw][numr] = ans;
    }
    return dp[numw][numr];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrettyPrinting.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
