package christmas.domain.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> menuItems;

    public Menu() {
        menuItems = createMenus();
    }

    private List<MenuItem> createMenus() {
        return new ArrayList<>(List.of(MenuItem.values()));
    }
}
