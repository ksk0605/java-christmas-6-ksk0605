package christmas.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayWeekendChecker {
    public static boolean isWeekday(int visitDate) {
        return getDayOfWeek(visitDate) != DayOfWeek.FRIDAY && getDayOfWeek(visitDate) != DayOfWeek.SATURDAY;
    }

    public static boolean isWeekend(int visitDate) {
        return !isWeekday(visitDate);
    }

    private static DayOfWeek getDayOfWeek(int visitDate) {
        LocalDate date = LocalDate.of(2023, 12, visitDate);
        return date.getDayOfWeek();
    }
}
