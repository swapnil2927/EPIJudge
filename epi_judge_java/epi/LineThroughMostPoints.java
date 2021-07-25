package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineThroughMostPoints {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Point {
    public int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  @EpiTest(testDataFile = "line_through_most_points.tsv")

  public static int findLineWithMostPoints(List<Point> points) {
    int n = points.size();
    if (n <= 1) {
      return n;
    }
    int ans = 0;
    Map<Integer, Integer> pwzs = new HashMap<>();
    Map<BigDecimal, Integer> slc = new HashMap<>();
    for (int i=0; i<n; i++) {
      for (int j=i+1; j<n; j++) {
        Point p1 = points.get(i);
        Point p2 = points.get(j);
        BigDecimal slope;
        if (p1.x == p2.x) {
          int cur = pwzs.getOrDefault(p1.x, 1);
          pwzs.put(p1.x, cur+1);
        } else {
          if (p1.y > p2.y) {
            slope = BigDecimal.valueOf(p1.y - p2.y).divide(BigDecimal.valueOf(p1.x - p2.x), MathContext.DECIMAL64);
          } else {
            slope = BigDecimal.valueOf(p2.y - p1.y).divide(BigDecimal.valueOf(p2.x - p1.x), MathContext.DECIMAL64);
          }
          int cur = slc.getOrDefault(slope, 1);
          slc.put(slope, cur+1);
        }
      }
    }
    for (Map.Entry<BigDecimal, Integer> slcen: slc.entrySet()) {
      ans = Math.max(ans, slcen.getValue());
    }
    for (Map.Entry<Integer, Integer> pwzsen : pwzs.entrySet()) {
      ans = Math.max(ans, pwzsen.getValue());
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LineThroughMostPoints.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
