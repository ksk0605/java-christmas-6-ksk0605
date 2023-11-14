package christmas.exception;

public class IllegalVisitDateInputException extends IllegalArgumentException {
    public IllegalVisitDateInputException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
