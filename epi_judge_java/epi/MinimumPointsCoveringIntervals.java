package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimumPointsCoveringIntervals {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }

    public int getLeft() {
      return left;
    }
  }

  @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")

  public static Integer findMinimumVisits(List<Interval> intervals) {
    // TODO - you fill in here.
    if (intervals.size() <= 1) {
      return intervals.size();
    }
    Collections.sort(intervals, Comparator.comparingInt(Interval::getLeft));
    int start = intervals.get(0).left;
    int end = intervals.get(0).right;
    int ans = 0;
    for (int i=1; i<intervals.size(); i++) {
      Interval cur = intervals.get(i);
      if (cur.left <= end) {
        start = Math.max(start, cur.left);
        end = Math.min(end, cur.right);
      } else {
        ans++;
        start = cur.left;
        end = cur.right;
      }
    }
    ans++;
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
