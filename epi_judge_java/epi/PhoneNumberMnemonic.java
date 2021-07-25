package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {
  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")

  public static List<String> phoneMnemonic(String phoneNumber) {
    if (phoneNumber.isEmpty()) {
      return Collections.emptyList();
    }
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    combos(phoneNumber, 0, ans, sb);
    return ans;
  }

  private static void combos(String digits, int curIn, List<String> ans, StringBuilder sb) {
    if (curIn == digits.length()) {
      ans.add(sb.toString());
      return;
    }
    char digit = digits.charAt(curIn);
    switch(digit) {
      case '0': {
        sb.append('0');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '1': {
        sb.append('1');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '2': {
        sb.append('A');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('B');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('C');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '3': {
        sb.append('D');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('E');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('F');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '4': {
        sb.append('G');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('H');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('I');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '5': {
        sb.append('J');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('K');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('L');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '6': {
        sb.append('M');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('N');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('O');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '7': {
        sb.append('P');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('Q');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('R');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('S');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '8': {
        sb.append('T');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('U');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('V');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }
      case '9': {
        sb.append('W');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('X');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('Y');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('Z');
        combos(digits, curIn + 1, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
        break;
      }

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
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
