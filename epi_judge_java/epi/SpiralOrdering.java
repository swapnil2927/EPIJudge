package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    if (squareMatrix.size() == 0) {
      return Collections.emptyList();
    }
    if (squareMatrix.get(0).size() == 0) {
      return Collections.emptyList();
    }
    List<Integer> ans = new ArrayList<>(squareMatrix.size() * squareMatrix.get(0).size());
    int mini = 0, maxi=squareMatrix.size()-1, minj=0, maxj=squareMatrix.get(0).size()-1;
    while (mini<=maxi && minj<=maxj) {
      for (int p=minj; p<=maxj; p++) {
        ans.add(squareMatrix.get(mini).get(p));
      }
      for (int q=mini+1; q<=maxi; q++) {
        ans.add(squareMatrix.get(q).get(maxj));
      }
      if (maxi > mini) {
        for (int r=maxj-1; r>=minj; r--) {
          ans.add(squareMatrix.get(maxi).get(r));
        }
      }
      if (minj < maxj) {
        for (int s=maxi-1; s>=mini+1; s--) {
          ans.add(squareMatrix.get(s).get(minj));
        }
      }
      maxi--;
      mini++;
      maxj--;
      minj++;
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
