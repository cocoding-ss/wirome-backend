package me.apjung.backend.dto.request;

import lombok.*;
import me.apjung.backend.domain.shop.ShopSafeLevel;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopRequest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        String name;
        String url;
        String overview;
        MultipartFile thumbnail;
        Set<String> tags;
        ShopSafeLevel safeLevel;
    }

    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Search {
        @Positive
        private Integer pageNum = 1;
        @Positive
        @Max(100) // TODO: 2020-12-03 최대 사이즈는 상황 보고 변경 가능
        private Integer pageSize = 10;
        private String orderType = "RECENTLY";
        @NotNull
        private Filter filter;

        @Setter
        @Getter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Filter {
            private String name;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Safe {
        ShopSafeLevel safeLevel;
    }
}