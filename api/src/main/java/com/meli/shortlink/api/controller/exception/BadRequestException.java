package com.meli.shortlink.api.controller.exception;

import java.net.URISyntaxException;

public class BadRequestException extends URISyntaxException {

    public BadRequestException(String input, String reason, int index) {
        super(input, reason, index);
    }

    public BadRequestException(String input, String reason) {
        super(input, reason);
    }
}
