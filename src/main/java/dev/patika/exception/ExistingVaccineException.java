package dev.patika.exception;

public class ExistingVaccineException extends RuntimeException {
    public ExistingVaccineException(String message) {
        super(message);
    }
}
