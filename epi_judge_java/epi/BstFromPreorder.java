package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BstFromPreorder {
  @EpiTest(testDataFile = "bst_from_preorder.tsv")

  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    if (preorderSequence == null || preorderSequence.isEmpty()) {
      return null;
    }
    return construct(preorderSequence, 0, preorderSequence.size() - 1);
  }

  private static BstNode<Integer> construct(List<Integer> preorderSequence, int s, int e) {
    if (s > e) {
      return null;
    }
    if (s == e) {
      return new BstNode<>(preorderSequence.get(s));
    }

    int curRoot = preorderSequence.get(s);
    int rtStart;
    for (rtStart=s+1; rtStart<=e; rtStart++) {
      if (curRoot < preorderSequence.get(rtStart)) {
        break;
      }
    }
    BstNode<Integer> curNode = new BstNode<>(curRoot);
    curNode.left = construct(preorderSequence, s+1, rtStart-1);
    curNode.right = construct(preorderSequence, rtStart, e);
    return curNode;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
