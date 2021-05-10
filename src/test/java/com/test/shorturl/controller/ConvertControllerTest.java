package com.test.shorturl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.shorturl.dto.ShortUrlRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ConvertControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void integrationTest() throws Exception {
		String content = objectMapper.writeValueAsString(new ShortUrlRequest("https://www.naver.com"));

		mockMvc.perform(post("/convert")
				.param("url", "https://www.naver.com")
				.flashAttr("shortUrlRequest", new ShortUrlRequest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute("resultShortenedUrl", "http://url.test/B"));
	}
}
