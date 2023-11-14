package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuItem;
import christmas.exception.ErrorMessage;
import christmas.exception.IllegalOrderInputException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static christmas.constants.EventConstants.MAXIMUM_ORDER_COUNT;

public class OrderItems {
    private final List<OrderItem> items;

    public OrderItems(String input) {
        items = new ArrayList<>();
        validateInput(input);
        validateAndAddItems(splitByComma(input));
        validateItemCounts();
        validateCategory();
    }

    private void validateItemCounts() {
        if (exceedMaximumCount()) {
            throw new IllegalOrderInputException(ErrorMessage.MAX_ORDER_COUNT_EXCEEDED);
        }
    }

    private void validateInput(String input) {
        String[] values = splitByComma(input);
        if (hasDuplicateComma(input) || isInvalidInputOrder(values)) {
            throw new IllegalOrderInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private boolean hasDuplicateComma(String input) {
        return input.contains(",,");
    }

    private boolean isInvalidInputOrder(String[] values) {
        for (String value : values) {
            if (value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean exceedMaximumCount() {
        return getOrderItemCountAmount() > MAXIMUM_ORDER_COUNT;
    }

    private boolean isInvalidMenuFormat(String[] parts) {
        return parts.length != 2;
    }

    private String getMenuName(String[] parts) {
        return parts[0].trim();
    }

    private int getMenuCount(String[] parts) {
        return Integer.parseInt(parts[1].trim());
    }

    private String[] splitByDash(String value) {
        return value.split("-");
    }

    private String[] splitByComma(String value) {
        return value.split(",");
    }

    private void validateMenuFormat(String[] parts) {
        if (isInvalidMenuFormat(parts)) {
            throw new IllegalOrderInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateDuplicateItem(Set<MenuItem> seenItems, MenuItem menuItem) {
        if (hasDuplicateItems(seenItems, menuItem)) {
            throw new IllegalOrderInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private boolean hasDuplicateItems(Set<MenuItem> seenItems, MenuItem menuItem) {
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

    public int calculateDessertOrderItemCountAmount() {
        int amount = 0;
        for (OrderItem orderItem : items) {
            if (isDessert(orderItem)) {
                amount += orderItem.getMenuCount();
            }
        }
        return amount;
    }

    private static boolean isDessert(OrderItem orderItem) {
        return orderItem.getCategory().equals(MenuCategory.디저트);
    }

    public int calculateMainOrderItemCountAmount() {
        int amount = 0;
        for (OrderItem orderItem : items) {
            if (isMain(orderItem)) {
                amount += orderItem.getMenuCount();
            }
        }
        return amount;
    }

    private static boolean isMain(OrderItem orderItem) {
        return orderItem.getCategory().equals(MenuCategory.메인);
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
            String[] parts = splitByDash(value);
            validateMenuFormat(parts);

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
            throw new IllegalOrderInputException(ErrorMessage.INVALID_ORDER);
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
            throw new IllegalOrderInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
