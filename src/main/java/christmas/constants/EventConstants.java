package christmas.constants;

public class EventConstants {
    // 시스템 전체
    public static int EVENT_YEAR = 2023;
    public static int EVENT_MONTH = 12;
    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;

    // 주문
    public static int MINIMUM_ORDER_COUNT = 1;
    public static int MAXIMUM_ORDER_COUNT = 20;

    // 할인 전체
    public static int ZERO_DISCOUNT = 0;
    public static int MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT = 10000;

    // 크리스마스 디데이 할인
    public static int CHRISTMAS_DISCOUNT_DAY_THRESHOLD = 25;
    public static int CHRISTMAS_MINIMUM_DISCOUNT_AMOUNT = 1000;
    public static int CHRISTMAS_DISCOUNT_PER_DAY = 100;
    public static String CHRISTMAS_DISCOUNT_NAME = "크리스마스 디데이 할인";

    // 이벤트 증정
    public static int EVENT_MINIMUM_DISCOUNT_AMOUNT = 120000;
    public static String EVENT_DISCOUNT_NAME = "증정 이벤트";

    // 특별 할인
    public static int SPECIAL_MINIMUM_DISCOUNT_AMOUNT = 1000;
    public static String SPECIAL_DISCOUNT_NAME = "특별 할인";

    // 평일 할인
    public static int WEEKDAY_DISCOUNT_PER_ORDER_ITEM = 2023;
    public static String WEEKDAY_DISCOUNT_NAME = "평일 할인";

    // 주말 할인
    public static int WEEKEND_DISCOUNT_PER_ORDER_ITEM = 2023;
    public static String WEEKEND_DISCOUNT_NAME = "주말 할인";

    private EventConstants() {
    }
}
