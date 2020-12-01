package me.apjung.backend.service.shop.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderBySearchShopStrategyTest {
    @Test
    @DisplayName("올바른 정렬 기준 값이 들어왔을 경우 테스트")
    public void stringToOrderBySearchShopStrategyWithCorrectValueTest() {
        String strategy = "popularity";
        OrderBySearchShopStrategy expected = OrderBySearchShopStrategy.POPULARITY;
        OrderBySearchShopStrategy result = OrderBySearchShopStrategy.from(strategy);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("잘못된 정렬 기준 값이 들어왔을 경우 테스트(기본값으로 매핑)")
    public void stringToOrderBySearchShopStrategyWithIncorrectValueTest() {
        String strategy = "incorrect strategy";
        OrderBySearchShopStrategy expected = OrderBySearchShopStrategy.RECENTLY;
        OrderBySearchShopStrategy result = OrderBySearchShopStrategy.from(strategy);

        assertEquals(expected, result);
    }
}