package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> head) {
    if (head == null) {
      return null;
    }

    ListNode soptr = head;
    ListNode septr = head.next;
    ListNode eptr = null;
    while (head.next != null) {
      if (eptr == null) {
        eptr = head.next;
      } else {
        eptr.next = head.next;
        eptr = eptr.next;
      }
      head.next = head.next.next;
      if (head.next != null) {
        head = head.next;
      }
      eptr.next = null;
    }

    head.next = septr;
    return soptr;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
