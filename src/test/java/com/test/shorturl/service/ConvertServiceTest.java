package com.test.shorturl.service;

import com.test.shorturl.domain.ShortUrl;
import com.test.shorturl.domain.ShortUrlRepository;
import com.test.shorturl.dto.ShortUrlResponse;
import com.test.shorturl.dto.UrlType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConvertServiceTest {
	@InjectMocks
	ConvertService convertService;

	@Mock
	ShortUrlRepository shortUrlRepository;

	@Test
	void Service_Test() {
		when(shortUrlRepository.save(any())).thenReturn(ShortUrl.builder().id(1L).originUrl("https://www.naver.com").requestCount(1L).build());

		ShortUrlResponse shortUrlResponse = convertService.convert("https://www.naver.com");

		assertThat(shortUrlResponse.getConvertSuccess()).isEqualTo(true);
		assertThat(shortUrlResponse.getShortenedUrl()).isEqualTo("http://url.test/B");
		assertThat(shortUrlResponse.getUrlType()).isEqualTo(UrlType.ORIGIN);
	}
}
