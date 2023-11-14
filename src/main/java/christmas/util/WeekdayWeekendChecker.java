package christmas.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.constants.EventConstants.EVENT_MONTH;
import static christmas.constants.EventConstants.EVENT_YEAR;

public class WeekdayWeekendChecker {
    public static boolean isWeekday(int visitDate) {
        return getDayOfWeek(visitDate) != DayOfWeek.FRIDAY && getDayOfWeek(visitDate) != DayOfWeek.SATURDAY;
    }

    public static boolean isWeekend(int visitDate) {
        return !isWeekday(visitDate);
    }

    private static DayOfWeek getDayOfWeek(int visitDate) {
        LocalDate date = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDate);
        return date.getDayOfWeek();
    }
}
