package ru.kostyakrasnov.banktransfers.exception;

/**
 * author: kostyakrasnov
 * date: 14.05.2020
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
