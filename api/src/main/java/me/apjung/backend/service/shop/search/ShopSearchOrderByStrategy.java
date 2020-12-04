package me.apjung.backend.service.shop.search;

import java.util.Optional;

public enum ShopSearchOrderByStrategy {
    POPULARITY, RECENTLY, NAME;

    // TODO: 2020-12-01 현재의 기본값(RECENTLY)이 변경될 수 있음
    public static ShopSearchOrderByStrategy from(String strategy) {
        return Optional.ofNullable(strategy)
                .map(ShopSearchOrderByStrategy::convertStrategy)
                .orElse(RECENTLY);
    }

    private static ShopSearchOrderByStrategy convertStrategy(String strategy) {
        switch (strategy.toLowerCase()) {
            case "popularity":
                return POPULARITY;
            case "name":
                return NAME;
            case "recently":
            default:
                return RECENTLY;
        }
    }
}
