package com.roman.converter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Roman Numeral Converter Tests ")
class RomanServiceTest {
    
    @Autowired
    private RomanService romanService;

    @DisplayName("Basic number to Roman conversion")
    @ParameterizedTest(name = "{0} should be {1}")
    @CsvSource({
        "1, I",
        "4, IV",
        "5, V",
        "9, IX",
        "10, X",
        "40, XL",
        "50, L",
        "90, XC",
        "100, C",
        "400, CD",
        "500, D",
        "900, CM",
        "1000, M",
        "3999, MMMCMXCIX"
    })
    void toRoman_BasicNumbers_ShouldConvertCorrectly(int number, String expected) {
        assertEquals(expected, romanService.toRoman(number));
    }

    @DisplayName("Basic Roman to number conversion")
    @ParameterizedTest(name = "{0} should be {1}")
    @CsvSource({
        "I, 1",
        "IV, 4",
        "V, 5",
        "IX, 9",
        "X, 10",
        "XL, 40",
        "L, 50",
        "XC, 90",
        "C, 100",
        "CD, 400",
        "D, 500",
        "CM, 900",
        "M, 1000",
        "MMMCMXCIX, 3999"
    })
    void fromRoman_BasicNumerals_ShouldConvertCorrectly(String roman, int expected) {
        assertEquals(expected, romanService.fromRoman(roman));
    }

    @DisplayName("Complex number to Roman conversion")
    @ParameterizedTest(name = "{0} should be {1}")
    @CsvSource({
        "1994, MCMXCIV",
        "2023, MMXXIII",
        "3549, MMMDXLIX",
        "2999, MMCMXCIX"
    })
    void toRoman_ComplexNumbers_ShouldConvertCorrectly(int number, String expected) {
        assertEquals(expected, romanService.toRoman(number));
    }

    @DisplayName("Numbers outside valid range should throw exception")
    @ParameterizedTest(name = "{0} should throw exception")
    @ValueSource(ints = {0, -1, 4000, 5000, Integer.MAX_VALUE})
    void toRoman_InvalidNumbers_ShouldThrowException(int number) {
        assertThrows(IllegalArgumentException.class, () -> romanService.toRoman(number));
    }

    @DisplayName("Invalid Roman numerals should throw exception")
    @ParameterizedTest(name = "{0} should throw exception")
    @ValueSource(strings = {"", "IIII", "VV", "IC", "XM", "MMMM", "MCMXCIIII"})
    void fromRoman_InvalidNumerals_ShouldThrowException(String roman) {
        assertThrows(IllegalArgumentException.class, () -> romanService.fromRoman(roman));
    }

    @Test
    @DisplayName("Null Roman numeral should throw exception")
    void fromRoman_NullInput_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> romanService.fromRoman(null));
    }

    @Test
    @DisplayName("Roman numerals should be case insensitive")
    void fromRoman_CaseInsensitive_ShouldWork() {
        assertEquals(1994, romanService.fromRoman("mcmxciv"));
        assertEquals(1994, romanService.fromRoman("MCMXCIV"));
        assertEquals(1994, romanService.fromRoman("McmXcIv"));
    }

    @DisplayName("Round trip conversion should work")
    @ParameterizedTest(name = "Number {0} should convert back and forth correctly")
    @ValueSource(ints = {1, 4, 9, 49, 99, 499, 999, 3999})
    void roundTripConversion_ShouldWork(int number) {
        String roman = romanService.toRoman(number);
        int result = romanService.fromRoman(roman);
        assertEquals(number, result, 
            String.format("Failed: %d -> %s -> %d", number, roman, result));
    }

    @Test
    @DisplayName("Subtractive combinations should work correctly")
    void fromRoman_SubtractiveCombinations_ShouldWork() {
        // Testing all subtractive combinations
        assertEquals(4, romanService.fromRoman("IV"));
        assertEquals(9, romanService.fromRoman("IX"));
        assertEquals(40, romanService.fromRoman("XL"));
        assertEquals(90, romanService.fromRoman("XC"));
        assertEquals(400, romanService.fromRoman("CD"));
        assertEquals(900, romanService.fromRoman("CM"));
    }
}
