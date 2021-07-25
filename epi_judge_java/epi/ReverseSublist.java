package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode fptr = L;
    ListNode sptr = null;
    int numMov = start - 1;
    while (numMov-- > 0 && fptr.next != null) {
      sptr = fptr;
      fptr = fptr.next;
    }
    if (sptr == null) {
      return reverse(fptr, finish - start + 1);
    } else {
      sptr.next = reverse(fptr, finish - start + 1);
      return L;
    }
  }

  private static ListNode reverse(ListNode head, int count) {
    ListNode prev = null;
    ListNode next = head;
    ListNode first = null;
    while (count-- > 0 && head != null) {
      next = head.next;
      head.next = prev;
      if (prev == null) {
        first = head;
      }
      prev = head;
      head = next;
    }
    if (first != null) {
      first.next = next;
    }
    return prev;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
