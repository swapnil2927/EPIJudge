package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MinimumWeightPathInATriangle {
  @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")

  public static int minimumPathTotal(List<List<Integer>> triangle) {
    if (triangle.size() == 0) {
      return 0;
    }
    List<List<Integer>> minWeightPath = new LinkedList<>();
    for(int i=triangle.size() - 1; i>=0; i--) {
      List<Integer> weightCur = triangle.get(i);
      if (i == (triangle.size() - 1)) {
        List<Integer> weightPathCur = new ArrayList<>(weightCur);
        minWeightPath.add(0, weightPathCur);
      } else {
        List<Integer> weightNext = minWeightPath.get(0);
        List<Integer> weightPathCur = new ArrayList<>(weightCur.size());
        for (int j=0; j<weightCur.size();j++) {
          weightPathCur.add(j, weightCur.get(j) + Math.min(weightNext.get(j), weightNext.get(j+1)));
        }
        minWeightPath.add(0, weightPathCur);
      }
    }
    return minWeightPath.get(0).get(0);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWeightPathInATriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
