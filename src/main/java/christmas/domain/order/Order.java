package christmas.domain.order;

import christmas.domain.VisitDate;

public class Order {
    private final OrderItems orderItems;
    private final VisitDate visitDate;

    public Order(OrderItems orderItems, VisitDate visitDate) {
        this.orderItems = orderItems;
        this.visitDate = visitDate;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public int getVisitDate() {
        return visitDate.getDate();
    }
}
