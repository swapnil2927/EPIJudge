package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class EnumerateTrees {

  public static List<BinaryTreeNode<Integer>>
  generateAllBinaryTrees(int n) {
    if (n == 0) {
      List<BinaryTreeNode<Integer>> ans = new ArrayList<>();
      ans.add(null);
      return ans;
    }
    Map<Integer, List<BinaryTreeNode<Integer>>> suBST = new HashMap<>();
    for (int i=1; i<=n; i++) {
      suBST.putIfAbsent(i, new LinkedList<BinaryTreeNode<Integer>>());
      List<BinaryTreeNode<Integer>> tFI = suBST.get(i);
      if (i == 1) {
        tFI.add(new BinaryTreeNode<Integer>(1));
        continue;
      }
      for (int j=0; j<i; j++) {
        int k = i-1-j;
        // Have j nodes on the left and k nodes on the right
        // Values in the left subtree will be 1 ... j
        // Values in the right subtree will be j+2 ... i
        List<BinaryTreeNode<Integer>> pBSTLeft = suBST.get(j);
        List<BinaryTreeNode<Integer>> pBSTRight = suBST.get(k);
        if (pBSTLeft == null) {
          for (BinaryTreeNode<Integer> rightStSt : pBSTRight) {
            BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<Integer>(j+1);
            newRoot.left = null;
            newRoot.right = rightStSt;
            tFI.add(newRoot);
          }
        } else {
          for (BinaryTreeNode<Integer> leftStSt : pBSTLeft) {
            BinaryTreeNode<Integer> leftSubTree = leftStSt;
            if (pBSTRight == null) {
              BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<Integer>(j+1);
              newRoot.left = leftSubTree;
              newRoot.right = null;
              tFI.add(newRoot);
            } else {
              for (BinaryTreeNode<Integer> rightStSt : pBSTRight) {
                BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<Integer>(j+1);
                newRoot.left = leftSubTree;
                newRoot.right = rightStSt;
                tFI.add(newRoot);
              }
            }
          }
        }
      }

    }
    return suBST.get(n);
  }
  public static List<Integer> serializeStructure(BinaryTreeNode<Integer> tree) {
    List<Integer> result = new ArrayList<>();
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    stack.push(tree);
    while (!stack.empty()) {
      BinaryTreeNode<Integer> p = stack.pop();
      result.add(p == null ? 0 : 1);
      if (p != null) {
        stack.push(p.left);
        stack.push(p.right);
      }
    }
    return result;
  }

  @EpiTest(testDataFile = "enumerate_trees.tsv")
  public static List<List<Integer>>
  generateAllBinaryTreesWrapper(TimedExecutor executor, int numNodes)
      throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> generateAllBinaryTrees(numNodes));

    List<List<Integer>> serialized = new ArrayList<>();
    for (BinaryTreeNode<Integer> x : result) {
      serialized.add(serializeStructure(x));
    }
    serialized.sort(new LexicographicalListComparator<>());
    return serialized;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateTrees.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
