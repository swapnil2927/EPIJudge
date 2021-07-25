package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class EnumerateBalancedParentheses {
  @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")

  public static List<String> generateBalancedParentheses(int numPairs) {
    if (numPairs == 0) {
      return Collections.singletonList("");
    }
    char[] tempStr = new char[numPairs * 2];
    tempStr[0] = '(';
    List<String> ans = new ArrayList<>();
    populateAns(numPairs, 1, 0, tempStr, ans);
    return ans;
  }

  private static void populateAns(int numPairs, int numOpBr, int numClBr, char[] tempStr, List<String> ans){
    if (numOpBr == numClBr && numOpBr == numPairs) {
      String posib = new String(tempStr);
      ans.add(posib);
      return;
    }

    int curIn = numOpBr + numClBr - 1;
    if (numOpBr < numPairs) {
      tempStr[curIn + 1] = '(';
      populateAns(numPairs, numOpBr + 1, numClBr, tempStr, ans);
      tempStr[curIn + 1] = '\u0000';
    }
    if (numClBr < numOpBr) {
      tempStr[curIn + 1] = ')';
      populateAns(numPairs, numOpBr, numClBr + 1, tempStr, ans);
      tempStr[curIn + 1] = '\u0000';
    }
  }

  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
