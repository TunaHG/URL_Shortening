package com.test.shorturl.service;

import com.test.shorturl.domain.ShortUrl;
import com.test.shorturl.domain.ShortUrlRepository;
import com.test.shorturl.dto.ShortUrlResponse;
import com.test.shorturl.dto.UrlType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConvertService {
	private final ShortUrlRepository shortUrlRepository;

	private final String URL_PREFIX = "http://url.test/";
	private final int Base62 = 62;
	private final String Base62String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public ShortUrlResponse convert(String url) {
		if(url.equals("") || url.isEmpty())
			return ShortUrlResponse.builder()
					.convertSuccess(false)
					.build();

		if(url.startsWith(URL_PREFIX)) {
			ShortUrl shortUrl = shortUrlRepository.findShortUrlByShortenedUrl(url);
			if(shortUrl == null)
				return ShortUrlResponse.builder()
						.convertSuccess(false)
						.build();
			return ShortUrlResponse.builder()
					.originUrl(shortUrl.getOriginUrl())
					.urlType(UrlType.SHORT)
					.convertSuccess(true)
					.build();
		}
		else {
			ShortUrl shortUrl = shortUrlRepository.findShortUrlByOriginUrl(url);

			if(shortUrl != null) {
				shortUrl.setRequestCount(shortUrl.getRequestCount() + 1);

				shortUrlRepository.save(shortUrl);

				return ShortUrlResponse.builder()
						.shortenedUrl(shortUrl.getShortenedUrl())
						.requestCount(shortUrl.getRequestCount())
						.urlType(UrlType.ORIGIN)
						.convertSuccess(true)
						.build();
			}

			shortUrl = shortUrlRepository.save(ShortUrl.builder().originUrl(url).requestCount(1L).build());
			String shortenedUrl = Base62Encode(shortUrl.getId());
			System.out.println("New Shortened, Result Shortened URL: " + shortenedUrl);
			shortUrlRepository.save(ShortUrl.builder()
					.id(shortUrl.getId()).originUrl(url).shortenedUrl(URL_PREFIX + shortenedUrl).requestCount(1L).build());
			return ShortUrlResponse.builder()
					.shortenedUrl(URL_PREFIX + shortenedUrl)
					.requestCount(shortUrl.getRequestCount())
					.urlType(UrlType.ORIGIN)
					.convertSuccess(true)
					.build();
		}
	}

	public String Base62Encode(Long input) {
		StringBuilder sb = new StringBuilder();
		while(input > 0) {
			sb.append(Base62String.charAt((int) (input % Base62)));
			input /= Base62;
		}
		return sb.toString();
	}
}
