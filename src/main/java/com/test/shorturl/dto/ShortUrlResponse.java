package com.test.shorturl.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShortUrlResponse {
	private String originUrl;
	private String shortenedUrl;
	private Long requestCount;
	private UrlType urlType;
	private Boolean convertSuccess;
}
