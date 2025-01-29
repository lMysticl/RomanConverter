package com.roman.converter.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * A friendly service that converts between Roman numerals and regular numbers!
 * 
 * Uses pre-made patterns for each digit position for instant conversion.
 * Supports numbers 1-3999, following standard Roman numeral rules.
 * 
 * Examples:
 * 42    -> XLII
 * 1994  -> MCMXCIV
 * 3999  -> MMMCMXCIX
 */
@Service
public class RomanService {
    private final Map<Integer, String> numberToRoman;
    private final Map<String, Integer> romanToNumber;

    // Ready-to-use patterns for each digit position
    private static final String[] UNITS = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private static final String[] TENS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] HUNDREDS = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] THOUSANDS = {"", "M", "MM", "MMM"};

    /**
     * When creating the service, we prepare all possible number combinations.
     * This happens once at startup, so it doesn't affect conversion speed later.
     * Think of it as preparing a cheat sheet before an exam! 
     */
    public RomanService() {
        numberToRoman = new HashMap<>();
        romanToNumber = new HashMap<>();

        // Generate all numbers from 1 to 3999
        for (int i = 1; i <= 3999; i++) {
            String roman = buildRomanNumeral(i);
            numberToRoman.put(i, roman);
            romanToNumber.put(roman, i);
        }
    }

    /**
     * Builds a Roman numeral by combining patterns for each digit position.
     * For example, let's break down 1994:
     * - 1 thousand  -> M
     * - 9 hundreds -> CM
     * - 9 tens     -> XC
     * - 4 units    -> IV
     * Result: MCMXCIV
     *
     */
    private String buildRomanNumeral(int number) {
        int unit = number % 10;
        int ten = (number / 10) % 10;
        int hundred = (number / 100) % 10;
        int thousand = (number / 1000) % 10;

        return THOUSANDS[thousand] + 
               HUNDREDS[hundred] + 
               TENS[ten] + 
               UNITS[unit];
    }

    /**
     * Converts a regular number to its Roman numeral form.
     * @param number any number from 1 to 3999
     * @return the Roman numeral as a string
     * @throws IllegalArgumentException if the number is out of our supported range
     */
    public String toRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999");
        }
        return numberToRoman.get(number);
    }

    /**
     * Converts a Roman numeral back to a regular number.
     * @param roman the Roman numeral to convert
     * @return the regular number
     * @throws IllegalArgumentException if the Roman numeral isn't valid
     */
    public int fromRoman(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Roman numeral cannot be empty");
        }

        Integer result = romanToNumber.get(roman.toUpperCase());
        if (result == null) {
            throw new IllegalArgumentException("Invalid Roman numeral");
        }

        return result;
    }
}
