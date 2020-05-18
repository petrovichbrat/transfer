package ru.kostyakrasnov.banktransfers.exception;

/**
 * author: kostyakrasnov
 * date: 13.05.2020
 */
public class NegativeBalanceException  extends RuntimeException {

    public NegativeBalanceException() {
        super();
    }

    public NegativeBalanceException(Throwable cause) {
        super(cause);
    }

    public NegativeBalanceException(String message) {
        super(message);
    }

    public NegativeBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
