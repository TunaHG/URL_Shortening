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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
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
		ShortUrlResponse shortUrlResponse = ShortUrlResponse.builder()
				.originUrl("https://www.naver.com")
				.shortenedUrl("http://url.test/B")
				.requestCount(1L)
				.urlType(UrlType.SHORT)
				.convertSuccess(true)
				.build();

		when(convertService.convert("https://www.naver.com")).thenReturn(shortUrlResponse);

		convertService.convert("https://www.naver.com");

		verify(convertService).convert("https://www.naver.com");
	}
}
