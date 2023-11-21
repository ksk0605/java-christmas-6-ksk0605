package christmas.view;

import christmas.domain.VisitDate;

import static christmas.view.ViewMessages.EVENT_PREVIEW_HEADER;
import static christmas.view.ViewMessages.WELCOME_MESSAGE;

public class StaticHeaderView {
    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printEventHeader(VisitDate visitDate) {
        System.out.println(formatDate(visitDate));
    }

    private static String formatDate(VisitDate visitDate) {
        return String.format(EVENT_PREVIEW_HEADER, visitDate.getDate());
    }
}
