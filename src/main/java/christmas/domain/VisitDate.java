package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.IllegalVisitDateInputException;

import static christmas.constants.EventConstants.MAX_DATE;
import static christmas.constants.EventConstants.MIN_DATE;

public class VisitDate {
    private final int date;

    public VisitDate(String value) {
        validate(value);
        this.date = toInt(value);
    }

    public int getDate() {
        return date;
    }

    private static boolean isOutOfRangeInteger(int date) {
        return date < MIN_DATE || date > MAX_DATE;
    }

    private void validate(String value) {
        if (isNotInteger(value) || isOutOfRangeInteger(toInt(value))) {
            throw new IllegalVisitDateInputException(ErrorMessage.INVALID_DATE);
        }
    }

    private boolean isNotInteger(String input) {
        try {
            toInt(input);
            return false;
        } catch (NumberFormatException exception) {
            return true;
        }
    }

    private int toInt(String value) {
        return Integer.parseInt(value);
    }
}
