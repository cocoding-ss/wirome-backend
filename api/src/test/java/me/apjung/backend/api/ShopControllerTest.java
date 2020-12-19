package me.apjung.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.apjung.backend.domain.file.File;
import me.apjung.backend.domain.shop.ShopSafeLevel;
import me.apjung.backend.dto.response.ShopResponse;
import me.apjung.backend.dto.vo.Thumbnail;
import me.apjung.backend.mock.MockUser;
import me.apjung.backend.mock.WithMockCustomUser;
import me.apjung.backend.MvcTest;
import me.apjung.backend.domain.shop.Shop;
import me.apjung.backend.domain.user.User;
import me.apjung.backend.service.shop.ShopService;
import me.apjung.backend.service.shop.search.ShopSearchOrderByCreatedByService;
import me.apjung.backend.service.shop.search.ShopSearchOrderByNameService;
import me.apjung.backend.service.shop.search.ShopSearchService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static me.apjung.backend.util.ApiDocumentUtils.getDocumentRequest;
import static me.apjung.backend.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShopControllerTest extends MvcTest {
    @MockBean ShopService shopService;

    @Test
    public void shopCreateTest() throws Exception {
        String token = getJwtAccessToken();

        // given
        User user = createNewUser(MockUser.builder().build());
        String accessToken = getJwtAccessToken(user);
        given(shopService.create(any())).willReturn(ShopResponse.Create.builder().id(1L).build());

        InputStream is = new ClassPathResource("mock/images/440x440.jpg").getInputStream();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("thumbnail", "mock_thumbnail.jpg", "image/jpg", is.readAllBytes());

        // when
        ResultActions results = mockMvc.perform(
                fileUpload("/shop")
                        .file(mockMultipartFile)
                        .param("name", "테스트 쇼핑몰")
                        .param("overview", "테스트로만들어본 쇼핑몰입니다")
                        .param("url", "https://www.naver.com")
                        .param("tags", "tag1", "tag2", "tag3")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        );

        //then
        results.andExpect(status().isCreated())
                .andDo(document("shop-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestHeaders(
                                headerWithName("Authorization").description("사용자 Access Token")
                        ),
                        requestParts(
                                partWithName("thumbnail").description("썸네일 이미지 파일")
                        ),
                        requestParameters(
                                parameterWithName("name").description("쇼핑몰 이름"),
                                parameterWithName("overview").description("쇼핑몰 소개"),
                                parameterWithName("url").description("쇼핑몰 Url"),
                                parameterWithName("tags").description("쇼핑몰 태그")

                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("쇼핑몰 아이디")
                        )
                ));
    }

    @Test
    @WithMockCustomUser
    public void shopGetTest() throws Exception {
        // given
        Shop shop = createNewShop();
        String token = getJwtAccessToken();
        given(shopService.get(anyLong(), any(User.class))).willReturn(ShopResponse.GET.builder()
                .id(1L)
                .name("테스트 쇼핑몰")
                .overview("쇼핑몰의 간단한 소개")
                .thumbnail(Thumbnail.from(File.builder()
                        .id(1L)
                        .name("test.jpg")
                        .extension("jpg")
                        .height(440)
                        .width(440)
                        .size(0L)
                        .isImage(true)
                        .originalExtension("jpg")
                        .originalName("test.jpg")
                        .publicUrl("http://loremflickr.com/440/440")
                        .prefix("mock/test")
                        .build()
                ))
                .url("https://www.naver.com")
                .build()
        );

        // when
        ResultActions results = mockMvc.perform(
                get("/shop/{shop_id}", 1L)
                    .header("Authorization", "Bearer " + token)
        );

        // then
        results.andExpect(status().isOk())
                .andDo(document("shop-get",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("shop_id").description("쇼핑몰 아이디")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("쇼핑몰 아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("쇼핑몰 이름"),
                                fieldWithPath("overview").type(JsonFieldType.STRING).description("쇼핑몰 이름"),
                                fieldWithPath("url").type(JsonFieldType.STRING).description("쇼핑몰 이름"),
                                fieldWithPath("thumbnail").type(JsonFieldType.OBJECT).description("쇼핑몰 썸네일"),
                                fieldWithPath("thumbnail.publicUrl").description("쇼핑몰 썸네일 url"),
                                fieldWithPath("thumbnail.prefix").description("쇼핑몰 썸네일 파일 prefix"),
                                fieldWithPath("thumbnail.name").description("쇼핑몰 썸네일 파일 이름"),
                                fieldWithPath("thumbnail.extension").description("쇼핑몰 썸네일 파일 확장자"),
                                fieldWithPath("thumbnail.originalName").description("쇼핑몰 썸네일 파일 원본 이름"),
                                fieldWithPath("thumbnail.originalExtension").description("쇼핑몰 썸네일 파일 원본 확장자"),
                                fieldWithPath("thumbnail.width").description("쇼핑몰 썸네일 이미지 가로 길이"),
                                fieldWithPath("thumbnail.height").description("쇼핑몰 썸네일 이미지 세로 길이"),
                                fieldWithPath("thumbnail.size").description("쇼핑몰 썸네일 이미지 파일 크기")
                        )
                ));
    }

    @Test
    @WithMockCustomUser
    @DisplayName("쇼핑몰 검색 api 테스트")
    public void shopSearchTest() throws Exception {
        // given
        createNewShop();
        String token = getJwtAccessToken();

        // when
        ResultActions results = mockMvc.perform(
                get("/shop/search")
                        .param("filter.name", "테스트")
                        .param("orderType", "name")
                        .param("pageSize", "10")
                        .param("pageNum", "1")
                        .header("Authorization", "Bearer " + token));

        results.andExpect(status().isOk())
                .andDo(document("shop-search",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("pageNum").optional().description("쇼핑몰 리스트 페이지 번호"),
                                parameterWithName("pageSize").optional().description("한 번에 가져올 쇼핑몰 리스트 크기"),
                                parameterWithName("orderType").optional().description("정렬 기준[popularity, name, recently(기본값)]"),
                                parameterWithName("filter.name").description("검색 필터(이름)")),
                        responseFields(
                                fieldWithPath("[]").type(JsonFieldType.ARRAY).description("쇼핑몰 리스트"),
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("쇼핑몰 아이디"),
                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("쇼핑몰 이름"),
                                fieldWithPath("[].overview").type(JsonFieldType.STRING).description("쇼핑몰 이름"),
                                fieldWithPath("[].url").type(JsonFieldType.STRING).description("쇼핑몰 이름"),
                                fieldWithPath("[].pv").type(JsonFieldType.NUMBER).description("쇼핑몰 뷰어수"),
                                fieldWithPath("[].uv").type(JsonFieldType.NUMBER).description("쇼핑몰 단일 뷰어수(1일)"),
                                fieldWithPath("[].thumbnailUrl").optional().type(JsonFieldType.STRING).description("쇼핑몰 썸네일 url")
                        )));
    }

    @Test
    @DisplayName("쇼핑몰 인증 등록")
    public void shopSafeTest() throws Exception {
        // given
        given(shopService.safe(anyLong(), any())).willReturn(
            ShopResponse.Safe.builder()
                .id(1L)
                .safeAt(LocalDateTime.now())
                .safeLevel(ShopSafeLevel.DANGEROUS)
                .build()
        );

        String token = getJwtAccessToken();
        HashMap<String, Object> request = new HashMap<>();
        request.put("safeLevel", "DANGEROUS");

        // when
        ResultActions results = mockMvc.perform(
                put("/shop/{shop_id}/safe", 1L)
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + token)
        );

        // then
        results.andExpect(status().isOk())
                .andDo(document("shop-safe",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("safeLevel").description("설정할 안전 레벨 (SAFE, NORMAL, DANGEROUS, FAKE)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("변경된 쇼핑몰 ID"),
                                fieldWithPath("safeAt").description("안전 레벨 변경 시각"),
                                fieldWithPath("safeLevel").description("변경된 안전 레벨")
                        )
                        ));
    }
}
