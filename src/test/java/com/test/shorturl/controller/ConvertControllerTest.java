package com.test.shorturl.controller;

import com.test.shorturl.service.ConvertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:/data.sql")
class ConvertControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private ConvertService convertService;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ConvertController(convertService)).build();
	}

	@Test
	void integrationTest() throws Exception {
		mockMvc.perform(get("/convert")
		.param("url", "https://www.naver.com"))
				.andExpect(model().attribute("resultShortenedUrl", "http://url.test/B"));
	}
}
