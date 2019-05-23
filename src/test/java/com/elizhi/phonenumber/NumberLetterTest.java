package com.elizhi.phonenumber;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: phonenumber
 * @description:
 * @author: zhifanglong
 * @create: 2019-05-23 22:07
 */
public class NumberLetterTest {

    @Test
    public void convertDigitsToLettersTest_2digits_success() {
        String str = "ad ae af bd be bf cd ce cf";

        int[] testDigits = {2, 3};
        String result = NumberLetter.convertDigitsToLetters(testDigits);

        Assert.assertEquals(str, result);

    }

    @Test
    public void convertDigitsToLettersTest_01_success(){
        String str = "";
        int[] testDigits = {0, 1};
        String result = NumberLetter.convertDigitsToLetters(testDigits);

        Assert.assertEquals(str, result);
    }

    @Test
    public void convertDigitsToLettersTest_03_success(){
        String str = "d e f";
        int[] testDigits = {0, 3};
        String result = NumberLetter.convertDigitsToLetters(testDigits);

        Assert.assertEquals(str, result);
    }

    @Test
    public void convertDigitsToLettersTest_2and34_success(){
        String str = "ad ae af ac ah ai bd be bf bc bh bi cd ce cf cc ch ci";
        int[] testDigits = {2, 34};
        String result = NumberLetter.convertDigitsToLetters(testDigits);

        Assert.assertEquals(str, result);
    }

    @Test
    public void convertDigitsToLettersTest_101_fail(){
        String str = "Support digits from 0 to 99";
        int[] testDigits = {101};
        String result = NumberLetter.convertDigitsToLetters(testDigits);

        Assert.assertEquals(str, result);
    }

    @Test
    public void convertDigitsToLettersTest_empty_fail(){
        String str = "NO Digits";
        int[] testDigits = {};
        String result = NumberLetter.convertDigitsToLetters(testDigits);

        Assert.assertEquals(str, result);
    }
}
