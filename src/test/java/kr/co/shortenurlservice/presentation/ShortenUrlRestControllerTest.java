package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.application.SimpleShortenUrlService;
import kr.co.shortenurlservice.domain.ShortenUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ShortenUrlRestController.class)
class ShortenUrlRestControllerTest {

    @MockBean
    private SimpleShortenUrlService simpleShortenUrlService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("원래의 URL로 리다이렉트 되어야한다.")
    void redirectTest() throws Exception {
        String expectedOriginalUrl = "https://www.google.com";

        when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any())).thenReturn(expectedOriginalUrl);

        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", expectedOriginalUrl));
    }

    @Test
    @DisplayName("URL 키에 해당하는 정보가 반환되어야 한다.")
    void shortenUrlInformationTest() throws Exception {
        String expectedOriginalUrl = "https://www.google.com";
        String expectedShortenUrlKey = "EXPECTED_KEY";
        Long expectedRedirectCount = 0L;

        ShortenUrl returnShortenUrl = new ShortenUrl(expectedOriginalUrl, expectedShortenUrlKey);
        ShortenUrlInformationDto returnDto = new ShortenUrlInformationDto(returnShortenUrl);

        when(simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(any())).thenReturn(returnDto);

        mockMvc.perform(get("/shorten-url/any-key"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.originalUrl").value(expectedOriginalUrl))
                .andExpect(jsonPath("$.shortenUrlKey").value(expectedShortenUrlKey))
                .andExpect(jsonPath("$.redirectCount").value(expectedRedirectCount));
    }
}