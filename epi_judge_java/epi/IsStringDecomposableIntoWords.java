package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class IsStringDecomposableIntoWords {

  private static int n;
  public static List<String>
  decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
    List<String> ans = new ArrayList<>();
    n = domain.length();
    Map<Integer, Boolean> calcRes = new HashMap<>();
    Deque<Integer> stck = new ArrayDeque<Integer>();
    stck.push(0);
    boolean res = canBeSegmented(domain, dictionary, stck, 1, calcRes, ans);
    return res ? ans : Collections.emptyList();
  }

  private static boolean canBeSegmented(String s, Set<String> wordDict, Deque<Integer> stck, int in,
                                        Map<Integer, Boolean> calcRes, List<String> ans) {
    if (in == n) {
      String subStr = s.substring(stck.peek());
      boolean res = wordDict.contains(subStr);
      if (res) {
        ans.add(subStr);
      }
      return res;
    }

    // Not making a cut
    boolean noCutRes = canBeSegmented(s, wordDict, stck, in + 1, calcRes, ans);
    if (noCutRes) {
      return true;
    }

    // Making a cut at index in
    String curSubStr = s.substring(stck.peek(), in);
    if (wordDict.contains(curSubStr)) {
      if (calcRes.get(in) != null) {
        return calcRes.get(in);
      }
      stck.push(in);
      ans.add(curSubStr);
      boolean resultForIn = canBeSegmented(s, wordDict, stck, in + 1, calcRes, ans);
      calcRes.put(in, resultForIn);
      if (resultForIn) {
        return true;
      } else {
        ans.remove(curSubStr);
        stck.pop();
        return false;
      }
    }
    return false;
  }

  @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
  public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                         String domain,
                                                         Set<String> dictionary,
                                                         Boolean decomposable)
      throws Exception {
    List<String> result =
        executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));

    if (!decomposable) {
      if (!result.isEmpty()) {
        throw new TestFailure("domain is not decomposable");
      }
      return;
    }

    if (result.stream().anyMatch(s -> !dictionary.contains(s))) {
      throw new TestFailure("Result uses words not in dictionary");
    }

    if (!String.join("", result).equals(domain)) {
      throw new TestFailure("Result is not composed into domain");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringDecomposableIntoWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
