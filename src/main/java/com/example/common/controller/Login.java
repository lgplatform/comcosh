package com.example.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/basic")
@Slf4j
public class Login {

	@GetMapping("/login")
	public String loginPage(Model model){
		log.info("### login start ###");
		return "login";
	}

}
