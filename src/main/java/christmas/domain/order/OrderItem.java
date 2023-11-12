package christmas.domain.order;

import christmas.domain.menu.MenuItem;

public class OrderItem {
    private final MenuItem menuItem;
    private final int menuCount;

    private OrderItem(MenuItem menuItem, int menuCount) {
        validateMenuCount(menuCount);
        this.menuItem = menuItem;
        this.menuCount = menuCount;
    }

    private static boolean isOutOfRangeMenuCount(int menuCount) {
        return menuCount < 1;
    }

    public static OrderItem from(MenuItem menuItem, int menuCount) {
        return new OrderItem(menuItem, menuCount);
    }

    private void validateMenuCount(int count) {
        if (isOutOfRangeMenuCount(count)) {
            throw new IllegalArgumentException();
        }
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getMenuCount() {
        return menuCount;
    }
}
