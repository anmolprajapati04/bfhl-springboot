package com.chitkara.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BfhlResponseDto {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    @JsonProperty("alphabets")
    private List<String> alphabets;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    @JsonProperty("sum")
    private String sum;

    @JsonProperty("concat_string")
    private String concatString;

    public BfhlResponseDto() {
    }

    public BfhlResponseDto(boolean isSuccess, String userId, String email, String rollNumber,
                           List<String> oddNumbers, List<String> evenNumbers, List<String> alphabets,
                           List<String> specialCharacters, String sum, String concatString) {
        this.isSuccess = isSuccess;
        this.userId = userId;
        this.email = email;
        this.rollNumber = rollNumber;
        this.oddNumbers = oddNumbers;
        this.evenNumbers = evenNumbers;
        this.alphabets = alphabets;
        this.specialCharacters = specialCharacters;
        this.sum = sum;
        this.concatString = concatString;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnore
    public boolean isSuccess() {
        return isSuccess;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public List<String> getOddNumbers() {
        return oddNumbers;
    }

    public List<String> getEvenNumbers() {
        return evenNumbers;
    }

    public List<String> getAlphabets() {
        return alphabets;
    }

    public List<String> getSpecialCharacters() {
        return specialCharacters;
    }

    public String getSum() {
        return sum;
    }

    public String getConcatString() {
        return concatString;
    }

    @Override
    public String toString() {
        return "BfhlResponseDto{"
                + "isSuccess=" + isSuccess
                + ", userId='" + userId + '\''
                + ", email='" + email + '\''
                + ", rollNumber='" + rollNumber + '\''
                + ", oddNumbers=" + oddNumbers
                + ", evenNumbers=" + evenNumbers
                + ", alphabets=" + alphabets
                + ", specialCharacters=" + specialCharacters
                + ", sum='" + sum + '\''
                + ", concatString='" + concatString + '\''
                + '}';
    }

    public static class Builder {
        private boolean isSuccess;
        private String userId;
        private String email;
        private String rollNumber;
        private List<String> oddNumbers;
        private List<String> evenNumbers;
        private List<String> alphabets;
        private List<String> specialCharacters;
        private String sum;
        private String concatString;

        public Builder isSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder rollNumber(String rollNumber) {
            this.rollNumber = rollNumber;
            return this;
        }

        public Builder oddNumbers(List<String> oddNumbers) {
            this.oddNumbers = oddNumbers;
            return this;
        }

        public Builder evenNumbers(List<String> evenNumbers) {
            this.evenNumbers = evenNumbers;
            return this;
        }

        public Builder alphabets(List<String> alphabets) {
            this.alphabets = alphabets;
            return this;
        }

        public Builder specialCharacters(List<String> specialCharacters) {
            this.specialCharacters = specialCharacters;
            return this;
        }

        public Builder sum(String sum) {
            this.sum = sum;
            return this;
        }

        public Builder concatString(String concatString) {
            this.concatString = concatString;
            return this;
        }

        public BfhlResponseDto build() {
            return new BfhlResponseDto(isSuccess, userId, email, rollNumber, oddNumbers,
                    evenNumbers, alphabets, specialCharacters, sum, concatString);
        }
    }
}
