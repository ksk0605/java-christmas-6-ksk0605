package christmas.domain.event;

public enum EventItem {
    샴페인(1),
    없음(0);

    int itemCount;

    EventItem(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public static EventItem from(int orderAmount) {
        if (orderAmount < 120000) {
            return EventItem.없음;
        }
        return EventItem.샴페인;
    }
}
