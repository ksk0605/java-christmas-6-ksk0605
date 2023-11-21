package christmas;

import christmas.controller.EventPlanner;
import christmas.domain.discount.Discounts;

public class Application {
    public static void main(String[] args) {
        Discounts discounts = new Discounts();

        EventPlanner eventPlanner = new EventPlanner(discounts);
        eventPlanner.start();
        eventPlanner.finish();
    }
}
