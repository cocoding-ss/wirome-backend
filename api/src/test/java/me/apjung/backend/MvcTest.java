package me.apjung.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.apjung.backend.component.custommessagesourceresolver.CustomMessageSourceResolver;
import me.apjung.backend.config.SecurityConfig;
import me.apjung.backend.config.WebMvcConfig;
import me.apjung.backend.property.JwtProps;
import me.apjung.backend.property.SecurityProps;
import me.apjung.backend.property.appprops.AppProps;
import me.apjung.backend.service.security.CustomUserDetailsService;
import me.apjung.backend.domain.shop.ShopViewStats;
import me.apjung.backend.domain.shop.ShopSafeLevel;
import me.apjung.backend.mock.MockUser;
import me.apjung.backend.component.randomstringbuilder.RandomStringBuilder;
import me.apjung.backend.domain.file.File;
import me.apjung.backend.domain.shop.Shop;
import me.apjung.backend.domain.user.role.Code;
import me.apjung.backend.domain.user.User;
import me.apjung.backend.domain.user.UserRole;
import me.apjung.backend.repository.file.FileRepository;
import me.apjung.backend.repository.role.RoleRepotisory;
import me.apjung.backend.repository.shop.ShopRepository;
import me.apjung.backend.repository.shopviewstats.ShopViewStatsRepository;
import me.apjung.backend.repository.user.UserRepository;
import me.apjung.backend.service.security.JwtTokenProvider;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@WebMvcTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@Disabled
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.apjung.xyz")
@Import({
        WebMvcConfig.class,
        CustomMessageSourceResolver.class,

        SecurityConfig.class,
        JwtTokenProvider.class,

        SecurityProps.class,
        JwtProps.class
})
public abstract class MvcTest {
    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;

    @MockBean protected CustomUserDetailsService customUserDetailsService;
}
