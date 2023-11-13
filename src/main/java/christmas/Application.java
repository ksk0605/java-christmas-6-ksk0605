package christmas;

import christmas.controller.EventPlanner;
import christmas.domain.discount.*;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new ChristmasDiscount());
        discounts.add(new WeekdayDiscount());
        discounts.add(new WeekendDiscount());
        discounts.add(new SpecialDiscount());
        discounts.add(new EventDiscount());

        EventPlanner eventPlanner = new EventPlanner(discounts);
        eventPlanner.start();
    }
}
