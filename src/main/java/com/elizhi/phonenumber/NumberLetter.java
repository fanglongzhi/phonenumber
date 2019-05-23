package com.elizhi.phonenumber;

import java.util.*;

/**
 * @program: phonenumber
 * @description:
 * @author: zhifanglong
 * @create: 2019-05-23 20:56
 */
public class NumberLetter {
    public static Map<Integer, String[]> letterMap = new HashMap<Integer, String[]>();

    static {
        letterMap.put(0, new String[0]);
        letterMap.put(1, new String[0]);
        letterMap.put(2, new String[]{"a", "b", "c"});
        letterMap.put(3, new String[]{"d", "e", "f"});
        letterMap.put(4, new String[]{"c", "h", "i"});
        letterMap.put(5, new String[]{"j", "k", "l"});
        letterMap.put(6, new String[]{"m", "n", "o"});
        letterMap.put(7, new String[]{"p", "q", "r", "s"});
        letterMap.put(8, new String[]{"t", "u", "v"});
        letterMap.put(9, new String[]{"w", "x", "y", "z"});
    }

    public static void main(String[] args) {
        int[] digits = new int[]{2, 34};
        System.out.println("Out put:" + convertDigitsToLetters(digits));
    }

    /**
     * Given an integer array containing digits from [0, 99],
     * the task is to print all possible letter
     * combinations that the numbers could represent
     * A mapping of digit to letters is just like
     * on the telephone buttons.A mapping of Two digits to letters is
     * a union of two single digit mapping
     *
     * @param digits
     * @return
     */

    public static String convertDigitsToLetters(int[] digits) {
        if (digits == null || digits.length == 0) {
            return "NO Digits";
        }
        String[][] letterCollection = new String[digits.length][];

        boolean canConvert = true;
        for (int i = 0; i < digits.length; i++) {
            int d = digits[i];
            if (d < 0 || d > 99) {
                canConvert = false;
                break;
            }
            String[] letters = letterMap.get(d);
            if (letters == null) {
                String digitStr = Integer.toString(d);
                int d1 = Integer.parseInt(digitStr.substring(0, 1));
                int d2 = Integer.parseInt(digitStr.substring(1));

                String[] d1Letters = letterMap.get(d1);
                String[] d2Letters = letterMap.get(d2);
                letters = new String[d1Letters.length + d2Letters.length];

                System.arraycopy(d1Letters, 0, letters, 0, d1Letters.length);
                System.arraycopy(d2Letters, 0, letters, d1Letters.length, d2Letters.length);
            }

            letterCollection[i] = letters;
        }

        if (!canConvert) {
            return "Support digits from 0 to 99";
        }

        List<String> result = buildLetterCombinations(letterCollection);
        StringBuilder lettersQueue = new StringBuilder();
        for (String letterCon : result) {
            lettersQueue.append(letterCon);
            lettersQueue.append(" ");
        }

        return lettersQueue.toString().trim();
    }

    private static List<String> buildLetterCombinations(String[]... letters) {
        if (letters == null || letters.length == 0) {
            return Collections.emptyList();
        }
        int total = 1;
        int cIndex = letters.length - 1;
        int[] counterMap = new int[letters.length];
        for (int i = 0; i < letters.length; i++) {
            counterMap[i] = 0;
            total *= (letters[i] == null || letters[i].length == 0 ? 1 : letters[i].length);
        }
        List<String> rt = new ArrayList<String>(total);
        while (cIndex >= 0) {
            StringBuilder element = new StringBuilder();
            for (int j = 0; j < letters.length; j++) {
                String[] set = letters[j];
                if (set != null && set.length > 0) {
                    element.append(set[counterMap[j]]);
                }
                if (j == letters.length - 1) {
                    if (set == null || ++counterMap[j] > set.length - 1) {
                        counterMap[j] = 0;
                        int cidx = j;
                        while (--cidx >= 0) {
                            if (letters[cidx] == null || ++counterMap[cidx] > letters[cidx].length - 1) {
                                counterMap[cidx] = 0;
                                continue;
                            }
                            break;
                        }
                        if (cidx < cIndex) {
                            cIndex = cidx;
                        }
                    }
                }
            }
            if (element.capacity() > 0) {
                rt.add(element.toString());
            }
        }
        return rt;
    }
}
