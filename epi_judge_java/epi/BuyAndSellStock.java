package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    if (prices.size() <= 1) {
      return 0.0;
    }
    Double min, max;
    min = max = prices.get(0);
    Double ans = 0.0;
    for (int i=1; i<prices.size(); i++) {
      if (prices.get(i) < min) {
        min = max = prices.get(i);
      } else if (prices.get(i) > max) {
        max = prices.get(i);
        ans = Math.max(ans, max - min);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
