package com.test.shorturl.controller;

import com.test.shorturl.dto.ShortUrlResponse;
import com.test.shorturl.dto.UrlType;
import com.test.shorturl.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class ConvertController {
	private final ConvertService convertService;

	@GetMapping("/convert")
	public ModelAndView convert(Model model, @RequestParam String url) {
		ModelAndView mv = new ModelAndView();
		ShortUrlResponse shortUrlResponse = convertService.convert(url);

		if(shortUrlResponse.getUrlType() == UrlType.SHORT) {
			mv.setViewName("redirect:" + shortUrlResponse.getOriginUrl());
			return mv;
		}
		model.addAttribute("resultShortenedUrl", shortUrlResponse.getShortenedUrl());
		model.addAttribute("resultRequestCount", shortUrlResponse.getRequestCount());
		mv.setViewName("index");
		return mv;
	}
}
