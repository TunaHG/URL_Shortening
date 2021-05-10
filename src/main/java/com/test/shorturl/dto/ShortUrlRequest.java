package com.test.shorturl.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class ShortUrlRequest {
	@URL
	private String url;
}
