package com.nefer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LotteryNumberValidator {

    public static boolean checkDuplicate(ArrayList<Integer> lotteryNumbers) {
        Set<Integer> numset = new HashSet<>(lotteryNumbers);
        if(numset.size() != lotteryNumbers.size()) {
            return false;
        }

        return true;
    }
}
