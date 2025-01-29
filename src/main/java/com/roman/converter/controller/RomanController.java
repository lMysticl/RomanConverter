package com.roman.converter.controller;

import com.roman.converter.service.RomanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling Roman numeral conversion requests.
 * Provides endpoints for converting between Roman and Arabic numerals.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RomanController {

    @Autowired
    private RomanService romanService;

    /**
     * Converts an Arabic number to Roman numerals.
     *
     * @param number The Arabic number to convert (must be between 1 and 10000)
     * @return ResponseEntity containing either the Roman numeral or an error message
     */
    @PostMapping("/toRoman")
    public ResponseEntity<?> toRoman(@RequestParam int number) {
        try {
            String result = romanService.toRoman(number);
            return ResponseEntity.ok().body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Converts a Roman numeral to an Arabic number.
     *
     * @param roman The Roman numeral to convert
     * @return ResponseEntity containing either the Arabic number or an error message
     */
    @PostMapping("/fromRoman")
    public ResponseEntity<?> fromRoman(@RequestParam String roman) {
        try {
            int result = romanService.fromRoman(roman);
            return ResponseEntity.ok().body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
