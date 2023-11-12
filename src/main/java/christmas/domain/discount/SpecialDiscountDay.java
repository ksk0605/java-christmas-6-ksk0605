package christmas.domain.discount;

import java.util.Arrays;

public enum SpecialDiscountDay {
    DAY_1(3),
    DAY_2(7),
    DAY_3(17),
    DAY_4(24),
    DAY_5(25),
    DAY_6(31);

    private final int specialDay;

    SpecialDiscountDay(int specialDay) {
        this.specialDay = specialDay;
    }

    public static boolean isSpecialDiscountDay(int date) {
        return Arrays.stream(values())
                .anyMatch(day -> day.getSpecialDay() == date);
    }

    public int getSpecialDay() {
        return specialDay;
    }
}

