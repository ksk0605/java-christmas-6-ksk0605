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
        validateItemCounts();
        validateCategory();
    }

    private void validateItemCounts() {
        if (exceedMaximumCount()) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        }
    }

    private boolean exceedMaximumCount() {
        return getOrderItemCountAmount() > 20;
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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
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

    public int getOrderItemCountAmount() {
        int amount = 0;
        for (OrderItem orderItem : items) {
            amount += orderItem.getMenuCount();
        }
        return amount;
    }

    public int getDessertOrderItemCountAmount() {
        int amount = 0;
        for (OrderItem orderItem : items) {
            if (orderItem.getCategory().equals(MenuCategory.디저트)) { // TODO: OrderItem 에 로직 넣기
                amount += orderItem.getMenuCount();
            }
        }
        return amount;
    }

    public int getMainOrderItemCountAmount() {
        int amount = 0;
        for (OrderItem orderItem : items) {
            if (orderItem.getCategory().equals(MenuCategory.디저트)) {
                amount += orderItem.getMenuCount();
            }
        }
        return amount;
    }

    public int calculateOrderAmount() {
        int amount = 0;
        for (OrderItem orderItem : items) {
            amount += orderItem.getMenuItem().getPrice() * orderItem.getMenuCount();
        }
        return amount;
    }

    private void validateAndAddItems(String[] values) { // TODO : 라인 수 줄이기
        Set<MenuItem> seenItems = new HashSet<>();

        for (String value : values) {
            String[] parts = split(value);

            validateNotAllowedInput(parts);

            String menuName = getMenuName(parts);

            validateMenuCount(parts);
            int menuCount = getMenuCount(parts);

            MenuItem menuItem = MenuItem.fromName(menuName);
            validateDuplicateItem(seenItems, menuItem);

            items.add(createOrderItem(menuItem, menuCount));
        }
    }

    private void validateMenuCount(String[] parts) {
        if (isNotInteger(parts)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isNotInteger(String[] parts) {
        try {
            Integer.parseInt(parts[1].trim());
            return false;
        } catch (NumberFormatException exception) {
            return true;
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
