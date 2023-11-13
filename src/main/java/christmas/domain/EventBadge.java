package christmas.domain;

import java.util.Arrays;
import java.util.Optional;

public enum EventBadge {
    별(5000),
    트리(10000),
    산타(20000);

    private final int minPurchaseAmount;

    EventBadge(int minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public int getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public static Optional<EventBadge> getBadgeForAmount(int purchaseAmount) {
        return Arrays.stream(values())
                .filter(badge -> purchaseAmount >= badge.getMinPurchaseAmount())
                .reduce((first, second) -> second);
    }
}
