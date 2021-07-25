package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class MatrixConnectedRegions {
  private static int[] delta = new int[] {0, 1, 0, -1, 0};
  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    flipEntRec(x, y, image, !image.get(x).get(y));
    return;
  }

  private static void flipEntRec(int x, int y, List<List<Boolean>> image, Boolean newColor) {
    if (x < 0 || x >= image.size() || y < 0 || y >= image.get(x).size() || newColor.equals(image.get(x).get(y))) {
      return;
    }
    image.get(x).set(y, newColor);
    for (int i=0;i<4; i++) {
      flipEntRec(x + delta[i], y + delta[i+1], image, newColor);
    }
  }

  @EpiTest(testDataFile = "painting.tsv")
  public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                     int x, int y,
                                                     List<List<Integer>> image)
      throws Exception {
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      B.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
