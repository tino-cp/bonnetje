package nl.tinoc.bonnetje.exception;

public class DatabasePropertiesLoadException extends RuntimeException {
    public DatabasePropertiesLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
