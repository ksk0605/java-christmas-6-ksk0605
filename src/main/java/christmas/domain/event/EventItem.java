package christmas.domain.event;

import java.util.Arrays;

public enum EventItem {
    샴페인(1, 25000, 120000),
    없음(0, 0, 0);

    final int itemCount;
    final int itemPrice;
    final int minPurchaseAmount;

    EventItem(int itemCount, int itemPrice, int minPurchaseAmount) {
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public static EventItem from(int orderAmount) {
        return Arrays.stream(values())
                .filter(item -> orderAmount >= item.minPurchaseAmount)
                .findFirst()
                .orElse(없음);
    }
}
