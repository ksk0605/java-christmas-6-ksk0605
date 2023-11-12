package christmas.domain;

public class VisitDate {
    private final int date;

    public VisitDate(int date) {
        validateDate(date);
        this.date = date;
    }

    private static boolean isOutOfRangeInteger(int date) {
        return date < 1 || date > 31;
    }

    private void validateDate(int date) {
        if (isOutOfRangeInteger(date)) {
            throw new IllegalArgumentException();
        }
    }

    public int getDate() {
        return date;
    }
}
