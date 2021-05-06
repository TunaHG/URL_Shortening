package com.test.shorturl.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
	ShortUrl findShortUrlByOriginUrl(String originUrl);
	ShortUrl findShortUrlByShortenedUrl(String shortenedUrl);
}
