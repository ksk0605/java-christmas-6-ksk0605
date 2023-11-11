package christmas.domain;

public class VisitDate {
    private final int date;

    public VisitDate(int date) {
        validateDate(date);
        this.date = date;
    }

    private void validateDate(int date) {
        if (isOutOfRangeInteger(date)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isOutOfRangeInteger(int date) {
        return date < 1 || date > 31;
    }
}
