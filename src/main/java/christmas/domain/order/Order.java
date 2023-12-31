package christmas.domain.order;

import christmas.domain.VisitDate;

public class Order {
    private final OrderItems orderItems;
    private final VisitDate visitDate;

    public Order(OrderItems orderItems, VisitDate visitDate) {
        this.orderItems = orderItems;
        this.visitDate = visitDate;
    }

    public int calculateOrderAmount() {
        return orderItems.calculateOrderAmount();
    }

    public int getVisitDate() {
        return visitDate.getDate();
    }

    public int calculateMainOrderItemCountAmount() {
        return orderItems.calculateMainOrderItemCountAmount();
    }

    public int calculateDessertOrderItemCountAmount() {
        return orderItems.calculateDessertOrderItemCountAmount();
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }
}
