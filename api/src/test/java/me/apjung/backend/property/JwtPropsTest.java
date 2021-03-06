package me.apjung.backend.property;

import me.apjung.backend.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class JwtPropsTest extends IntegrationTest {
    @Autowired
    private JwtProps jwtProps;

    @Test
    @DisplayName("정상적으로 토큰 설정을 가져오는지 테스트")
    void jwtPropertyTest() {
        System.out.println("JWT PROPS ACCESS_TOKEN ::");
        System.out.println("secret :: " + jwtProps.getAccessTokenProps().getSecret());
        System.out.println("expirationTime :: " + jwtProps.getAccessTokenProps().getExpirationTimeMilliSec());

        System.out.println("JWT PROPS REFRESH_TOKEN ::");
        System.out.println("secret :: " + jwtProps.getRefreshTokenProps().getSecret());
        System.out.println("expirationTime :: " + jwtProps.getRefreshTokenProps().getExpirationTimeMilliSec());
    }
}
