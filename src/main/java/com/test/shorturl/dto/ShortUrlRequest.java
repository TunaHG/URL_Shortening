package com.test.shorturl.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Getter
public class ShortUrlRequest {
	@URL
	@NotNull
	private String url;
}
