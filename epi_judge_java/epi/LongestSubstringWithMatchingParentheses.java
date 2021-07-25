package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithMatchingParentheses {
  @EpiTest(testDataFile = "longest_substring_with_matching_parentheses.tsv")

  public static int longestMatchingParentheses(String s) {
    if (s.length() <= 1) {
      return 0;
    }
    int n = s.length();
    int ans = 0;
    Set<String> bco = new HashSet<>();
    for (int i=0; i<n-1; i++) {
      for (int j=i+1; j<n; j++) {
        if (bco.contains(i + "|" + j)) {
          continue;
        }
        if ((j - i + 1) % 2 != 0) {
          continue;
        }
        int opC = 0;
        int clC = 0;
        for (int k=i; k<=j; k++) {
          if (s.charAt(k) == '(') {
            opC++;
          } else if (s.charAt(k) == ')') {
            clC++;
          }
          if (clC > opC) {
            break;
          }
          int ns = k+1;
          if (opC == clC) {
            bco.add(i + "|" + k);
            if (bco.contains(ns + "|" + j)) {
              opC = clC = (j-i+1)/2;
              break;
            }
          }
        }
        if (opC == clC) {
          ans = Math.max(ans, opC + clC);
          bco.add(i + "|" + j);
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, "LongestSubstringWithMatchingParentheses.java",
                        new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
