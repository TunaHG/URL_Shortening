package com.test.shorturl.controller;

import com.test.shorturl.dto.ShortUrlRequest;
import com.test.shorturl.dto.ShortUrlResponse;
import com.test.shorturl.dto.UrlType;
import com.test.shorturl.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ConvertController {
	private final ConvertService convertService;

	@PostMapping("/convert")
	public ModelAndView convert(Model model, @Valid @ModelAttribute ShortUrlRequest shortUrlRequest) {
		ModelAndView mv = new ModelAndView();
		ShortUrlResponse shortUrlResponse = convertService.convert(shortUrlRequest.getUrl());

		if(!shortUrlResponse.getConvertSuccess()) {
			model.addAttribute("resultShortenedUrl", "잘못된 URL입니다.");
			mv.setViewName("index");
		}
		else {
			if(shortUrlResponse.getUrlType() == UrlType.SHORT) {
				mv.setViewName("redirect:" + shortUrlResponse.getOriginUrl());
			}
			else {
				model.addAttribute("resultShortenedUrl", shortUrlResponse.getShortenedUrl());
				model.addAttribute("resultRequestCount", shortUrlResponse.getRequestCount());
				mv.setViewName("index");
			}
		}
		return mv;
	}

	@ExceptionHandler(BindException.class)
	public ModelAndView validateException(Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("resultShortenedUrl", "잘못된 URL입니다.");
		mv.setViewName("index");
		return mv;
	}
}
