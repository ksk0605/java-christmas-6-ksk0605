package christmas.domain.order;

import christmas.domain.VisitDate;

public class Order { // TODO : 함수 네이밍 통일
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

    public int orderItemCounts() {
        return orderItems.getOrderItemCountAmount();
    }

    public int getMainOrderItemCountAmount() {
        return orderItems.getMainOrderItemCountAmount();
    }

    public int getDessertOrderItemCountAmount() {
        return orderItems.getDessertOrderItemCountAmount();
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }
}
