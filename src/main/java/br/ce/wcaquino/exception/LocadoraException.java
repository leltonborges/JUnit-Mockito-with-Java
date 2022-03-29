package br.ce.wcaquino.exception;

public class LocadoraException extends RuntimeException {
    private static final long serialVersionUID = -5721151614532501167L;

    public LocadoraException(String message) {
        super(message);
    }
}
