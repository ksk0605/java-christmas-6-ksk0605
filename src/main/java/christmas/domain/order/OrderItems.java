package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderItems {
    private final List<OrderItem> items;

    public OrderItems(String[] values) {
        items = new ArrayList<>();
        validateAndAddItems(values);
        validateCategory();
    }

    private static boolean isNotAllowedInput(String[] parts) {
        return parts.length != 2;
    }

    private static String getMenuName(String[] parts) {
        return parts[0].trim();
    }

    private static int getMenuCount(String[] parts) {
        return Integer.parseInt(parts[1].trim());
    }

    private static String[] split(String value) {
        return value.split("-");
    }

    private static void validateNotAllowedInput(String[] parts) {
        if (isNotAllowedInput(parts)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDuplicateItem(Set<MenuItem> seenItems, MenuItem menuItem) {
        if (hasDuplicateItems(seenItems, menuItem)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean hasDuplicateItems(Set<MenuItem> seenItems, MenuItem menuItem) {
        return !seenItems.add(menuItem);
    }

    private static OrderItem createOrderItem(MenuItem menuItem, int menuCount) {
        return OrderItem.from(menuItem, menuCount);
    }

    private void validateAndAddItems(String[] values) {
        Set<MenuItem> seenItems = new HashSet<>();

        for (String value : values) {
            String[] parts = split(value);

            validateNotAllowedInput(parts);

            String menuName = getMenuName(parts);
            int menuCount = getMenuCount(parts);

            MenuItem menuItem = MenuItem.fromName(menuName);
            validateDuplicateItem(seenItems, menuItem);

            items.add(createOrderItem(menuItem, menuCount));
        }
    }

    private void validateCategory() {
        Set<MenuCategory> categories = new HashSet<>();
        for (OrderItem item : items) {
            categories.add(item.getMenuItem().getCategory());
        }

        if (categories.size() == 1 && categories.contains(MenuCategory.음료)) {
            throw new IllegalArgumentException();
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
