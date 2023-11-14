package christmas.domain.event;

import static christmas.constants.EventConstants.EVENT_MINIMUM_DISCOUNT_AMOUNT;

public enum EventItem {
    샴페인(1, 25000),
    없음(0, 0);

    final int itemCount;
    final int itemPrice;

    EventItem(int itemCount, int itemPrice) {
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public static EventItem from(int orderAmount) {
        if (orderAmount < EVENT_MINIMUM_DISCOUNT_AMOUNT) {
            return EventItem.없음;
        }
        return EventItem.샴페인;
    }
}
