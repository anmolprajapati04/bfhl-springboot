package com.chitkara.bfhl.service.impl;

import com.chitkara.bfhl.dto.BfhlRequestDto;
import com.chitkara.bfhl.dto.BfhlResponseDto;
import com.chitkara.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${app.user.full-name}")
    private String fullName;

    @Value("${app.user.dob}")
    private String dob;

    @Value("${app.user.email}")
    private String email;

    @Value("${app.user.roll-number}")
    private String rollNumber;

    @Override
    public BfhlResponseDto processData(BfhlRequestDto requestDto) {
        List<String> data = requestDto.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            if (isNumber(item)) {
                long num = Long.parseLong(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabet(item)) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(alphabets);

        String userId = fullName.toLowerCase().replace(" ", "_") + "_" + dob;

        return BfhlResponseDto.builder()
                .isSuccess(true)
                .userId(userId)
                .email(email)
                .rollNumber(rollNumber)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sum))
                .concatString(concatString)
                .build();
    }

    /**
     * Checks if a string is purely numeric (supports multi-digit numbers).
     */
    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    /**
     * Checks if a string is purely alphabetical (supports multi-char strings like "ABCD").
     */
    private boolean isAlphabet(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Builds the concat_string:
     * - Collect all characters from all alphabet items (already uppercased).
     * - Reverse the full character sequence.
     * - Apply alternating caps: index 0 = uppercase, index 1 = lowercase, etc.
     *
     * Example: alphabets = ["A", "Y", "B"]  → chars = [A, Y, B]
     *          reversed = [B, Y, A]
     *          alternating caps = B(upper), y(lower), A(upper) → "ByA"
     */
    private String buildConcatString(List<String> alphabets) {
        // Collect all characters from all alphabet entries
        StringBuilder allChars = new StringBuilder();
        for (String alpha : alphabets) {
            allChars.append(alpha.toUpperCase());
        }

        // Reverse the full character sequence
        String reversed = allChars.reverse().toString();

        // Apply alternating caps
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
