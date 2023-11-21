package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitDate;
import christmas.domain.order.OrderItems;

import static christmas.view.ViewMessages.ENTER_VISIT_DATE_PROMPT;
import static christmas.view.ViewMessages.ORDER_MENU_PROMPT;

public class InputView {
    public static VisitDate readDate() {
        System.out.println(ENTER_VISIT_DATE_PROMPT);
        String input = createUserInput();
        return new VisitDate(input);
    }

    public static OrderItems readOrderItems() {
        System.out.println(ORDER_MENU_PROMPT);
        String input = createUserInput();
        return new OrderItems(input);
    }

    private static String createUserInput() {
        return Console.readLine();
    }
}
