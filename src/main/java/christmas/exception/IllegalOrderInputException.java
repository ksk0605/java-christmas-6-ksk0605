package christmas.exception;

public class IllegalOrderInputException extends IllegalArgumentException {
    public IllegalOrderInputException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
