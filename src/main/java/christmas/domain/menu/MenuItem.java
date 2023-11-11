package christmas.domain.menu;

public enum MenuItem {
    // 애피타이저
    양송이수프(MenuCategory.애피타이저, 6_000),
    타파스(MenuCategory.애피타이저, 5_500),
    시저샐러드(MenuCategory.애피타이저, 8_000),

    // 메인
    티본스테이크(MenuCategory.메인, 55_000),
    바비큐립(MenuCategory.메인, 54_000),
    해산물파스타(MenuCategory.메인, 35_000),
    크리스마스파스타(MenuCategory.메인, 25_000),

    // 디저트
    초코케이크(MenuCategory.디저트, 15_000),
    아이스크림(MenuCategory.디저트, 5_000),

    // 음료
    제로콜라(MenuCategory.음료, 3_000),
    레드와인(MenuCategory.음료, 60_000),
    샴페인(MenuCategory.음료, 25_000);

    private final MenuCategory category;
    private final int price;

    MenuItem(MenuCategory category, int price) {
        this.category = category;
        this.price = price;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
