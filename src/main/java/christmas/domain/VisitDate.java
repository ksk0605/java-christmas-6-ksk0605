package christmas.domain;

public class VisitDate {
    private final int date;

    public VisitDate(String value) {
        validate(value);
        this.date = toInt(value);
    }

    private static boolean isOutOfRangeInteger(int date) {
        return date < 1 || date > 31;
    }

    private void validate(String value) {
        if (isOutOfRangeInteger(toInt(value)) || isNotInteger(value)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
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

    public int getDate() {
        return date;
    }
}
