package com.chitkara.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseDto {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("message")
    private String message;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnore
    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorResponseDto{"
                + "isSuccess=" + isSuccess
                + ", message='" + message + '\''
                + '}';
    }

    public static class Builder {
        private boolean isSuccess;
        private String message;

        public Builder isSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseDto build() {
            return new ErrorResponseDto(isSuccess, message);
        }
    }
}
