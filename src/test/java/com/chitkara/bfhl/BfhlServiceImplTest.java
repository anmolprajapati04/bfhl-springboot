package com.chitkara.bfhl;

import com.chitkara.bfhl.dto.BfhlRequestDto;
import com.chitkara.bfhl.dto.BfhlResponseDto;
import com.chitkara.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BfhlServiceImplTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
        ReflectionTestUtils.setField(bfhlService, "fullName", "john_doe");
        ReflectionTestUtils.setField(bfhlService, "dob", "17091999");
        ReflectionTestUtils.setField(bfhlService, "email", "john@xyz.com");
        ReflectionTestUtils.setField(bfhlService, "rollNumber", "ABCD123");
    }

    // ---------------------------------------------------------------
    // Example A: ["a", "1", "334", "4", "R", "$"]
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Example A: mixed input with one odd, two evens, two alphabets, one special char")
    void testExampleA() {
        BfhlRequestDto request = new BfhlRequestDto(
                Arrays.asList("a", "1", "334", "4", "R", "$")
        );

        BfhlResponseDto response = bfhlService.processData(request);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getUserId()).isEqualTo("john_doe_17091999");
        assertThat(response.getEmail()).isEqualTo("john@xyz.com");
        assertThat(response.getRollNumber()).isEqualTo("ABCD123");

        assertThat(response.getOddNumbers()).containsExactly("1");
        assertThat(response.getEvenNumbers()).containsExactly("334", "4");
        assertThat(response.getAlphabets()).containsExactly("A", "R");
        assertThat(response.getSpecialCharacters()).containsExactly("$");
        assertThat(response.getSum()).isEqualTo("339");
        assertThat(response.getConcatString()).isEqualTo("Ra");
    }

    // ---------------------------------------------------------------
    // Example B: ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Example B: mixed input with multiple specials and alphabets")
    void testExampleB() {
        BfhlRequestDto request = new BfhlRequestDto(
                Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b")
        );

        BfhlResponseDto response = bfhlService.processData(request);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getOddNumbers()).containsExactly("5");
        assertThat(response.getEvenNumbers()).containsExactly("2", "4", "92");
        assertThat(response.getAlphabets()).containsExactly("A", "Y", "B");
        assertThat(response.getSpecialCharacters()).containsExactly("&", "-", "*");
        assertThat(response.getSum()).isEqualTo("103");
        assertThat(response.getConcatString()).isEqualTo("ByA");
    }

    // ---------------------------------------------------------------
    // Example C: ["A", "ABCD", "DOE"]
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Example C: only multi-char alphabets, no numbers or specials")
    void testExampleC() {
        BfhlRequestDto request = new BfhlRequestDto(
                Arrays.asList("A", "ABCD", "DOE")
        );

        BfhlResponseDto response = bfhlService.processData(request);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getOddNumbers()).isEmpty();
        assertThat(response.getEvenNumbers()).isEmpty();
        assertThat(response.getAlphabets()).containsExactly("A", "ABCD", "DOE");
        assertThat(response.getSpecialCharacters()).isEmpty();
        assertThat(response.getSum()).isEqualTo("0");
        assertThat(response.getConcatString()).isEqualTo("EoDdCbAa");
    }

    // ---------------------------------------------------------------
    // Edge case: empty data array
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Empty data array returns all empty lists, sum=0, concatString empty")
    void testEmptyData() {
        BfhlRequestDto request = new BfhlRequestDto(List.of());
        BfhlResponseDto response = bfhlService.processData(request);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getOddNumbers()).isEmpty();
        assertThat(response.getEvenNumbers()).isEmpty();
        assertThat(response.getAlphabets()).isEmpty();
        assertThat(response.getSpecialCharacters()).isEmpty();
        assertThat(response.getSum()).isEqualTo("0");
        assertThat(response.getConcatString()).isEmpty();
    }

    // ---------------------------------------------------------------
    // Edge case: only numbers
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Only numbers: correct odd/even split and sum")
    void testOnlyNumbers() {
        BfhlRequestDto request = new BfhlRequestDto(
                Arrays.asList("3", "6", "11", "100")
        );
        BfhlResponseDto response = bfhlService.processData(request);

        assertThat(response.getOddNumbers()).containsExactly("3", "11");
        assertThat(response.getEvenNumbers()).containsExactly("6", "100");
        assertThat(response.getAlphabets()).isEmpty();
        assertThat(response.getSpecialCharacters()).isEmpty();
        assertThat(response.getSum()).isEqualTo("120");
        assertThat(response.getConcatString()).isEmpty();
    }

    // ---------------------------------------------------------------
    // Edge case: only special characters
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Only special characters: all placed in specialCharacters list")
    void testOnlySpecialChars() {
        BfhlRequestDto request = new BfhlRequestDto(
                Arrays.asList("@", "#", "!")
        );
        BfhlResponseDto response = bfhlService.processData(request);

        assertThat(response.getSpecialCharacters()).containsExactly("@", "#", "!");
        assertThat(response.getOddNumbers()).isEmpty();
        assertThat(response.getEvenNumbers()).isEmpty();
        assertThat(response.getAlphabets()).isEmpty();
        assertThat(response.getSum()).isEqualTo("0");
        assertThat(response.getConcatString()).isEmpty();
    }

    // ---------------------------------------------------------------
    // Edge case: single alphabet produces single uppercase char
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Single lowercase alphabet is uppercased in response")
    void testSingleAlphabet() {
        BfhlRequestDto request = new BfhlRequestDto(List.of("z"));
        BfhlResponseDto response = bfhlService.processData(request);

        assertThat(response.getAlphabets()).containsExactly("Z");
        assertThat(response.getConcatString()).isEqualTo("Z");
    }

    // ---------------------------------------------------------------
    // user_id format check
    // ---------------------------------------------------------------
    @Test
    @DisplayName("user_id is always lowercased full name + dob")
    void testUserIdFormat() {
        ReflectionTestUtils.setField(bfhlService, "fullName", "Jane Doe");
        ReflectionTestUtils.setField(bfhlService, "dob", "01011995");

        BfhlRequestDto request = new BfhlRequestDto(List.of("1"));
        BfhlResponseDto response = bfhlService.processData(request);

        assertThat(response.getUserId()).isEqualTo("jane_doe_01011995");
    }
}
