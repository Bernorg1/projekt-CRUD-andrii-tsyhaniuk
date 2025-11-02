package vistula.andriitsyhaniuk.office.exception;

public class DuplicateLoginException extends RuntimeException {
    public DuplicateLoginException(String message) {
        super(message);
    }
}

