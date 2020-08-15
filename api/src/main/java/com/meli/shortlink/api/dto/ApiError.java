package com.meli.shortlink.api.dto;

public class ApiError {

    private Long code;
    private String message;

    public ApiError() {
    }

    public ApiError(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiError apiError = (ApiError) o;

        if (code != null ? !code.equals(apiError.code) : apiError.code != null) return false;
        return message != null ? message.equals(apiError.message) : apiError.message == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
