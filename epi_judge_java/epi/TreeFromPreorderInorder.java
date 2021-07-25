package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    if (preorder.size() != inorder.size()) {
      throw new IllegalArgumentException();
    }
    if (preorder.size() == 0) {
      return null;
    }
    return getTree(preorder, 0, preorder.size()-1, inorder, 0, inorder.size()-1);
  }

  private static BinaryTreeNode<Integer> getTree
          (List<Integer> pr, int prst, int pren, List<Integer> ino, int inost, int inoen) {
    if ((pren - prst) != (inoen -inost)) {
      throw new IllegalArgumentException();
    }
    if (pren == prst) {
      return new BinaryTreeNode<>(pr.get(prst));
    }

    int rval = pr.get(prst);
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rval);
    int inoior = inost;
    for (int i=inost; i<=inoen; i++) {
      if (ino.get(i) == rval) {
        inoior = i;
        break;
      }
    }
    int numNodeInLeft = inoior - inost;
    int numNodeInRight = inoen - inoior;
    root.left = numNodeInLeft == 0 ? null : getTree(pr, prst+1, prst+numNodeInLeft, ino,
            inost, inoior-1);
    root.right = numNodeInRight == 0 ? null : getTree(pr, prst+numNodeInLeft+1, pren, ino,
            inoior+1, inoen);
    return root;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
