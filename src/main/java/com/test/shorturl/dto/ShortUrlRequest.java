package com.test.shorturl.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlRequest {
	@URL
	private String url;
}
