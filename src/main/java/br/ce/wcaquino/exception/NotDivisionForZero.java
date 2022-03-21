package br.ce.wcaquino.exception;

public class NotDivisionForZero extends RuntimeException {
    public NotDivisionForZero(String message) {
        super(message);
    }

    public NotDivisionForZero(String message, Throwable cause) {
        super(message, cause);
    }
}
