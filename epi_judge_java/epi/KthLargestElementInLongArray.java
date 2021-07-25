package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementInLongArray {

  public static int findKthLargestUnknownLength(Iterator<Integer> stream,
                                                int k) {
    PriorityQueue<Integer> minh = new PriorityQueue<>(k);
    while (stream.hasNext()) {
      if (minh.size() < k) {
        minh.offer(stream.next());
      } else {
        Integer cur = stream.next();
        if (cur > minh.peek()) {
          minh.poll();
          minh.offer(cur);
        }
      }
    }
    return minh.peek();
  }
  @EpiTest(testDataFile = "kth_largest_element_in_long_array.tsv")
  public static int findKthLargestUnknownLengthWrapper(List<Integer> stream,
                                                       int k) {
    return findKthLargestUnknownLength(stream.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestElementInLongArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
