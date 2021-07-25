package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LongestNondecreasingSubsequence {
    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")

    public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
        if (A.size() <= 1) {
            return A.size();
        }
        // Element at index i in this array, stores the minimum value present in A between indices i ... A.size()-1.
        int[] minf = new int[A.size()];
        // Element at index i in this array, stores the maximum value present in A between indices i ... A.size()-1.
        int[] maxf = new int[A.size()];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = A.size() - 1; i >= 0; i--) {
            int cur = A.get(i);
            min = cur < min ? cur : min;
            max = cur > max ? cur : max;
            minf[i] = min;
            maxf[i] = max;
        }

        // Key: The particular index i in A from which longestNondecreasingSubsequenceLength is to be found.
        // Value: Value-Key -> All elements from i ... A.size()-1 should be greater than or equal to this key k.
        // Value-Value -> the length of the longest non decreasing subsequence under these constraints.
        Map<Integer, TreeMap<Integer, Integer>> dp = new HashMap<>();
        return solve(0, A, minf, maxf, dp, Integer.MIN_VALUE);
    }

    private static int solve(int stIn, List<Integer> A, int[] minf, int[] maxf, Map<Integer, TreeMap<Integer, Integer>> dp, int small) {
        if (stIn == A.size()) {
            return 0;
        }

        if (small > maxf[stIn]) {
            // Each and every element from stIn ... A.size()-1 is smaller than "small".
            return 0;
        }

        if (dp.containsKey(stIn)) {
            TreeMap<Integer, Integer> psols = dp.get(stIn);
            if (psols.containsKey(small)) {
                // Already solved sub problem denoted by co ordinates (stIn, (small, "our ans")).
                return psols.get(small);
            } else {
                // Even if we have not explicitly solved this sub problem, we can use the fact that for particular value of stIn
                // length of largest non decreasing sub sequence will be same for all values of "small" <= minf[stIn]
                if (small <= minf[stIn]) {
                    Map.Entry<Integer, Integer> floorEn = psols.floorEntry(small);
                    if (floorEn == null) {
                        Map.Entry<Integer, Integer> ceilEn = psols.ceilingEntry(small);
                        if (ceilEn != null && ceilEn.getKey() <= minf[stIn]) {
                            return ceilEn.getValue();
                        }
                    } else {
                        return floorEn.getValue();
                    }
                }
            }
        }

        int ans = 0;
        if (A.get(stIn) >= small) {
            // We can consider this element to be added to our largest non decreasing sub sequence.
            ans = Math.max(ans, solve(stIn + 1, A, minf, maxf, dp, A.get(stIn)) + 1);
        }
        ans = Math.max(ans, solve(stIn + 1, A, minf, maxf, dp, small));
        dp.putIfAbsent(stIn, new TreeMap<>());
        dp.get(stIn).put(small, ans);
        return ans;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
